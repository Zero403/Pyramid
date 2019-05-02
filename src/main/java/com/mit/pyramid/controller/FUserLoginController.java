package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.LoginInfoVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.service.FUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "登录退出进行的操作", tags = "登录退出")
public class FUserLoginController {

    @Autowired
    private FUserLoginService loginService;

    //@ApiOperation(value = "注册用户" , notes = "实现用户注册")

    @PostMapping("user/login.do")
    @ApiOperation(value = "用户登录", notes = "实现用户通过输入用户名或手机号登录")
    public ResultVO login(HttpServletResponse response, @RequestBody @ApiParam(name = "infos", value = "用户登录所需输入的信息，后台判断输入是否为手机号") LoginInfoVO infos) {
        return loginService.login(infos, response);
    }

    @GetMapping("user/checklogin.do")
    public ResultVO checkLogin(HttpServletRequest request) {
        return loginService.checkLogin(request);
    }

    @GetMapping("user/userexit.do")
    public ResultVO exit(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }


}
