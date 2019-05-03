package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BRole;
import com.mit.pyramid.entity.BRolePermission;
import com.mit.pyramid.service.BRolePermissionService;
import com.mit.pyramid.service.BRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/mit.pyramid/bRole")
@Api(value = "角色相关", tags = "后台角色")
public class BRoleController {
    @Autowired
    private BRoleService roleService;

    @GetMapping("/rolelist.do")
    @ApiOperation(value = "角色列表", notes = "返回角色列表及对应权限的列表")
    public ResultVO roleList(){
        return roleService.findroleList();
    }

    @PostMapping("/rolelist.do")
    @ApiOperation(value = "增加角色", notes = "仅提供增加角色名与备注功能 角色权限方面到角色权限修改操作")
    public ResultVO addRole(@RequestBody BRole role){
        role.setId(null);
        boolean flag = roleService.save(role);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

    @PutMapping("/updaterole.do")
    @ApiOperation(value = "修改角色基本信息", notes = "角色ID无法修改, 角色拥有权限另外修改")
    public ResultVO updateRole(@RequestBody BRole role){
        UpdateWrapper<BRole> wrapper = new UpdateWrapper<>();
        wrapper.set(null != role.getInfo(), "info", role.getInfo()).set(null != role.getName(), "name", role.getName()).eq("id", role.getId());
        boolean flag = roleService.update(wrapper);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

    @Autowired
    private BRolePermissionService rpService;
    @PutMapping("/rolepermission.do")
    @ApiOperation(value = "修改角色的权限", notes = "id为修改的角色id  pids为新权限id的数组")
    public ResultVO updateRolePermission(Integer id, Integer[] pids){
        rpService.remove(new QueryWrapper<BRolePermission>().eq("rid", id));
        List<BRolePermission> rps = new ArrayList<>();
        for(int i = 0; i < pids.length; i++){
            BRolePermission rp = new BRolePermission();
            rp.setRid(id);
            rp.setRid(pids[i]);
            rps.add(rp);
        }
        boolean flag = rpService.saveBatch(rps);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

	
}
