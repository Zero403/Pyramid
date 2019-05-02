package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.service.BUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login.do")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResultVO login(String no, String password){
        return null;
    }
}
