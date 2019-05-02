package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.service.BMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO myMessage(@RequestParam("uid")@ApiParam(name = "uid",value = "用户id") Integer uid, @RequestParam("type") @ApiParam(name = "type",value = "类型")Integer type){
        return messageService.selectMyMessage(uid, type);
    }

    @PostMapping("/sendmessage.do")
    @ApiOperation(value = "发送消息", notes = "发送消息 sendId 发件人ID 系统为0, orderID收件人Id title标题 discription内容 其他内容不要有")
    public ResultVO sendMessage(@RequestBody BMessage message){
        return messageService.sendMessage(message);
    }

    @PostMapping("/batchmessage.do")
    @ApiOperation(value = "发送系统消息", notes = "type:1全体发送2对应星发送3单人发送  value:全体可以为任意值对应星则是星单人则是Id title标题description内容")
    public ResultVO batchMessage(@RequestParam("type")@ApiParam(name = "type",value = "类型") Integer type, @RequestParam("value")@ApiParam(name = "value",value = "值") Integer value, @RequestParam("title")@ApiParam(name = "title",value = "标题") String title, @RequestParam("description")@ApiParam(name = "description",value = "内容") String description){
        return messageService.batchMessage(type, value, title, description);
    }
}
