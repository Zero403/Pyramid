package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BBackMessage;
import com.mit.pyramid.service.BBackMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ery
 * @Date: 2019/5/3 14:57
 * @Version null
 */
@RestController
@RequestMapping("/bbackmessage")
@Api(value = "后台消息相关", tags = "后台消息")
public class BBackMessageController {

    @Autowired
    private BBackMessageService bBackMessageService;

    @GetMapping("/announcement.do")
    @ApiOperation(value = "查询公告", notes = "结果分页逆序排列 若无分页信息则最后十条")
    public IPage<BBackMessage> announcement(Integer page, Integer limit){
        page = page == null ? 1 : page;
        limit = limit == null ? 10 : limit;
        QueryWrapper<BBackMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("sendid", 0).orderByDesc("id");
        return bBackMessageService.page(new Page<BBackMessage>(page, limit), wrapper);
    }

    @PostMapping("/sendannouncement.do")
    @ApiOperation(value = "发布公告", notes = "发布公告 标题必须填")
    public ResultVO sendAnnouncement(String title, String description){
        BBackMessage message = new BBackMessage();
        message.setSendid(0);
        message.setTitle(title);
        message.setDescription(description);

        boolean flag = bBackMessageService.save(message);
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }



}
