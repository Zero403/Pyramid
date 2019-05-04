package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.CheckResultVO;
import com.mit.pyramid.common.vo.LvUpRequestVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.service.FLVRequestService;
import com.mit.pyramid.service.FLvupcheckService;
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

    @Autowired
    private FLvupcheckService checkService;

    @PostMapping("user/level/list.do")
    @ApiOperation(value = "用户可升级的列表", notes = "用户可升级的列表，建议使用<select>标签实现")
    public ResultVO getCanUpLevel(@ApiParam(name = "token", value = "用户的token") String token){
        TokenVO tokenVO = TokenUtil.parseToken(token);
        int uid = tokenVO.getUid();
        ResultVO resultVO = lvRequestService.canUptoLevel(uid);
        return resultVO;
    }


    @PostMapping("user/level/up.do")
    @ApiOperation(value = "升级用户",notes = "等级提升，特殊等级有审核")
    public ResultVO lvUp(@ApiParam(name = "upRequest", value = "升级请求，sid代表等级对应的id")@RequestBody LvUpRequestVO upRequest) {
        int uid = TokenUtil.parseToken(upRequest.getToken()).getUid();
        int sid = upRequest.getSid();
        return lVupService.lvUp(uid,sid);
    }

    @PostMapping("user/level/spec.do")
    @ApiOperation(value = "特殊等级审核", notes = "查看我被谁审核")
    public ResultVO spec(@ApiParam(name = "token", value = "用户的token")String token){
        int uid = TokenUtil.parseToken(token).getUid();
        return checkService.myCheck(uid);
    }

    @PostMapping("user/level/checklist.do")
    @ApiOperation(value = "审核列表", notes = "审核列表")
    public ResultVO checkList(@ApiParam(name = "token", value = "用户的token")String token){
        int uid = TokenUtil.parseToken(token).getUid();
        return checkService.checkList(uid);
    }

    @GetMapping("user/level/checkok.do")
    @ApiOperation(value = "审核结果", notes = "是否同意审核")
    public ResultVO checkFlag(@ApiParam(name = "checkResultVO", value = "审核结果，id:审批人提供的表id, flag:1--同意，2--不同意")CheckResultVO checkResultVO) {
        int cid = checkResultVO.getId();
        int flag = checkResultVO.getFlag();
        return checkService.checkOK(cid,flag);
    }

}
