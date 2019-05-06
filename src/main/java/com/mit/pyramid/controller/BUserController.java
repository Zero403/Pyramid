package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.service.BUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@RequestMapping("/mit.pyramid/bUser")
@Api(value = "后台用户相关", tags = "后台用户")
public class BUserController {

    @Autowired
    private BUserService bUserService;

    @PostMapping("/blogin.do")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResultVO login(String username, String password, boolean rememberMe){
        boolean flag = true;
        try {
            //password = new SimpleHash("MD5", password, null, 5).toString();
            bUserService.login(username, password, rememberMe);
        }catch (Exception e){
            flag = false;
        }
         return ResultUtil.exec(flag, flag ? "登录成功" : "用户或密码错误", null);
    }

    @GetMapping("/buserlist.do")
    @ApiOperation(value = "查询后台用户列表", notes = "用户列表")
    public IPage<BUser> userList(@ApiParam(name = "页码",value = "type") @RequestParam("page") Integer page, @ApiParam(name = "每页条数", value = "limit") @RequestParam("limit") Integer limit){
        page = page == null ? 1 : page;
        limit = limit == null ? 20 : limit;
        Page<BUser> pages = new Page<>(page, limit);
        return bUserService.page(pages, null);
    }

    @PostMapping("/buseadd.do")
    @ApiOperation(value = "后台用户新增", notes = "新增用户 id和flag不用填")
    public ResultVO buserAdd(@RequestBody BUser user){
        return bUserService.addUser(user);

    }

    @GetMapping("/findbuser.do")
    @ApiOperation(value = "用户查找", notes = "参数可以不用全部传完密码不能查")
    public IPage<BUser> findUser(BUser user, Integer page, Integer limit){
        page = page == null ? 1 : page;
        limit = limit == null ? 20 : limit;
        QueryWrapper<BUser> wrapper = new QueryWrapper<>();
        wrapper.eq(null !=user.getId(), "id", user.getId()).eq(null != user.getRoleid(), "roleid", user.getRoleid()).eq(null != user.getFlag(), "flag", user.getFlag() ).like(null != user.getNo(), "no", user.getNo()).like(null != user.getName(), "name", user.getName());
        return bUserService.page(new Page<BUser>(page, limit), wrapper);
    }

    @GetMapping("/findbuserbyid.do")
    @ApiOperation(value = "根据ID查询单个用户", notes = "查询单个用户")
    public BUser findBuserByid(Integer id){
        return bUserService.getById(id);
    }

    @PutMapping("/updatebuer.do")
    @ApiOperation(value = "修改后台用户信息", notes = "修改除用户ID以外的其他信息")
    public ResultVO updateBuser(@RequestBody BUser user){
        UpdateWrapper<BUser> wrapper = new UpdateWrapper<>();
        String password = user.getPassword();
        wrapper.set(null != user.getNo(),"no", user.getNo()).set(null != user.getName(), "name",user.getName()).set(null != password, "password", password).set(null != user.getRoleid(), "roleid", user.getRoleid()).set(null != user.getFlag(), "flag", user.getFlag()).eq("id", user.getId());
        boolean flag = bUserService.update(user, wrapper);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

    @DeleteMapping("/deletebuser.do")
    @ApiOperation(value = "删除用户", notes = "只用传用户ID即可")
    public ResultVO delBuser(Integer id){
        BUser user = new BUser();
        user.setId(id);
        user.setFlag(2);
        return updateBuser(user);

    }
}
