package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.service.FUserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@Api(value = "前台用户相关操作", tags = "操作前台用户")
public class FUserBasicController {

    @Autowired
    private FUserBasicService fUserBasicService;

    @PostMapping("user/register.do")
    @ApiOperation(value = "注册用户" , notes = "实现用户注册")
    public ResultVO save(@RequestParam("user") @ApiParam(name = "user" ,value = "用户相关键值对") FUserBasic user, @RequestParam("invitecode") @ApiParam(name = "invitecode", value = "邀请码") String inviteCode){
        return fUserBasicService.userRegister(user, inviteCode);
    }

}
