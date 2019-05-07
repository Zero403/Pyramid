package com.mit.pyramid.controller;

import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.entity.FUserTrue;
import com.mit.pyramid.service.FUserTrueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */

@RestController
@Api(value = "实名认证相关",tags = "实名认证")
public class FUserTrueController {

    @Autowired
    private FUserTrueService fUserTrueService;

    @ApiOperation(value = "实名添加",notes = "添加用户的实名信息")
    @PostMapping("usertrue/add.do")
    public ResultVO add(FUserTrue fUserTrue, String token){
        TokenVO tokenVO = TokenUtil.parseToken(token);
        fUserTrue.setUid(tokenVO.getUid());


        return ResultUtil.exec(fUserTrueService.save(fUserTrue),"添加",null);
    }

    @ApiOperation(value = "是否已实名认证")
    @PutMapping("usertrue/check.do")
    public ResultVO check(String token){
        int uid = TokenUtil.parseToken(token).getUid();
        return fUserTrueService.getById(uid) != null ? ResultUtil.setOK():ResultUtil.setERROR();
    }
}
