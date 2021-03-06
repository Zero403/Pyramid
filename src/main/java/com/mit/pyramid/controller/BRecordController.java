package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BRecord;
import com.mit.pyramid.service.BRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/record"))
@Api(value = "用户奖励惩罚记录",tags = "我的奖励/处罚")
public class BRecordController {
    @Autowired
    private BRecordService bRecordService;

    @PostMapping("/reward.do")
    @ApiOperation(value = "分页展示所有奖励消息")
    public ResultVO reward(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count, String token){

        Page<BRecord> list = new Page<>(page, count);
        IPage<BRecord> ipage = bRecordService.page(list, new QueryWrapper<BRecord>().eq("type", 0).eq("uid",TokenUtil.parseToken(token).getUid()));

        return ResultUtil.exec(ipage.getRecords().size() > 0,"",ipage);
    }

    @PostMapping("/punish.do")
    @ApiOperation(value = "分页展示所有惩罚消息")
    public ResultVO punish(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count, String token){
        Page<BRecord> list = new Page<>(page, count);
        IPage<BRecord> ipage = bRecordService.page(list, new QueryWrapper<BRecord>().eq("type", 1).eq("uid",TokenUtil.parseToken(token).getUid()));

        return ResultUtil.exec(ipage.getRecords().size() > 0,"",ipage);
    }
}
