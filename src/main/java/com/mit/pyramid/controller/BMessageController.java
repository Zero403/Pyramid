package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.service.BMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/message")
@Api(value = "消息相关", tags = "管理消息")
public class BMessageController {

    @Autowired
    private BMessageService messageService;

    @GetMapping("/mymessage.do")
    @ApiOperation(value = "查询消息", notes = "uid查询者ID系统为0 type查询类型0全部1未读2已读3发送的消息规定类型外会出错")
    public ResultVO myMessage(int uid, int type){
        return messageService.selectMyMessage(uid, type);
    }

    @PostMapping("/sendmessage.do")
    @ApiOperation(value = "发送消息", notes = "发送消息 sendId 发件人ID 系统为0, orderID收件人Id title标题 discription内容")
    public ResultVO sendMessage(BMessage message){

        return messageService.sendMessage(message);
    }
}
