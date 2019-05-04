package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.LvUpRequestVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.service.FLVRequestService;
import com.mit.pyramid.service.FUserLVupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "升级操作", tags = "等级提升")
public class FUserLVUPController {

    @Autowired
    private FLVRequestService lvRequestService;

    @Autowired
    private FUserLVupService lVupService;

    @PostMapping("user/level/list.do")
    @ApiOperation(value = "用户可升级的列表", notes = "用户可升级的列表，建议使用<select>标签实现")
    public ResultVO getCanUpLevel(@ApiParam(name = "token", value = "用户的token") String token){
        TokenVO tokenVO = TokenUtil.parseToken(token);
        int uid = tokenVO.getUid();
        ResultVO resultVO = lvRequestService.canUptoLevel(uid);
        return resultVO;
    }


    @PostMapping("user/level/up.do")
    @ApiOperation(value = "升级用户",notes = "等级提升，特殊等级有")
    public ResultVO lvUp(LvUpRequestVO upRequest) {
        int uid = TokenUtil.parseToken(upRequest.getToken()).getUid();
        int sid = upRequest.getSid();
        return lVupService.lvUp(uid,sid);
    }

}
