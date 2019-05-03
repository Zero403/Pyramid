package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BPermission;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.service.BPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@RequestMapping("/permission")
@Api(value = "后台权限", tags = "角色权限")
public class BPermissionController {

    @Autowired
    private BPermissionService permissionService;

    @GetMapping("/menu.do")
    @ApiOperation(value = "菜单", notes = "菜单 分一级跟二级菜单 一级菜单下有二级菜单列表")
    public ResultVO menu(){
        return permissionService.menu(new BUser());
    }

    @GetMapping("/permissionlist.do")
    @ApiOperation(value = "权限列表", notes = "权限列表")
    public ResultVO permissionList(){
        List<BPermission> list = permissionService.list();
        return ResultUtil.exec(true, "", list);
    }

    @GetMapping("/permissiontop.do")
    @ApiOperation(value = "顶级权限", notes = "可以用于权限新增时查询父级权限")
    public ResultVO permissionTop(){
        List<BPermission> parentids = permissionService.list(new QueryWrapper<BPermission>().eq("parentid", 0));
        return ResultUtil.exec(true, "", parentids);
    }

    @PostMapping("/addpermission.do")
    @ApiOperation(value = "增加权限", notes = "增加权限, 权限的ID不用写, 若是顶级权限则parentID可以不写或为零")
    public ResultVO addPermission(@RequestBody BPermission permission){
        permission.setId(null);
        permission.setParentid(permission.getParentid() == null ? 0 : permission.getParentid());
        boolean flag = permissionService.save(permission);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

    @DeleteMapping("/delpermission.do")
    @ApiOperation(value = "删除权限", notes = "若有子权限存在则无法删除父权限")
    public ResultVO delPermission(Integer id){
        return permissionService.delPermission(id);
    }

    @PutMapping("/updatepermission")
    @ApiOperation(value = "修改权限", notes = "修改权限的名字url父权限 父权限无法修改为子权限")
    public ResultVO updatePermission(@RequestBody BPermission permission){
        Integer id = permission.getId();
        Integer parentid = permission.getParentid();
        String name = permission.getPermission();
        String url = permission.getUrl();
        BPermission bPermission = permissionService.getById(id);
        if(bPermission.getParentid() == 0)permission.setParentid(0);
        boolean flag = permissionService.update(permission, new UpdateWrapper<BPermission>().set(null != name, "permission", name).set(null != parentid, "parentid", parentid).set(null != url, "url", url).eq("id", id));
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

}
