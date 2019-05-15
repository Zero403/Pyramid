package com.mit.pyramid.controller;

import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.service.FInviteCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "邀请码相关", tags = "邀请码操作")
public class FInviteCodeController {

    @Autowired
    private FInviteCodeService service;

    @PostMapping("icode/geticode.do")
    @ApiOperation(value = "获取邀请码", notes = "获取邀请码")
    public ResultVO getInviteCode(@ApiParam("用户token,键值对传递") @RequestBody String token) {
        return service.getInviteCode(token);
    }

    @GetMapping("icode/checkicode.do")
    @ApiOperation(value = "验证邀请码", notes = "验证邀请码，非必须，已在注册方法集成")
    public ResultVO checkInviteCode(@ApiParam("邀请码，键值对传递") @RequestBody String inviteCode) {
        return service.checkInviteCode(inviteCode);
    }
}
