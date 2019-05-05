package com.mit.pyramid.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.*;
import com.mit.pyramid.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/userLevel")
@Api(value = "降级消息相关", tags = "降级消息")
public class BUserLevelDownController {
    @Autowired
    private BUserLevelDownService bUserLevelDownService;

    @Autowired
    private FUserStatusService fUserStatusService;

    @Autowired
    private BMessageService bMessageService;

    @Autowired
    private BRecordService recordService;

    @Autowired
    private FUserInvitenubersService fUserInvitenubers;

    @PostMapping("/pagelist.do")
    @ApiOperation(value = "分页展示所有降级消息")
    public ResultVO pagelist(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count){
        Page<UserLevelDownVO> list = new Page<>(page, count);
        list.setRecords(bUserLevelDownService.findAll(list));

        return ResultUtil.exec(list.getRecords().size() > 0,"",list);
    }

    @PostMapping("/down.do")
    @ApiOperation(value = "手动降低用户等级",notes = "参数需要：目标用户sid，调整后的等级uid，需要登录后才能进行操作")
    public ResultVO down(FUserStatus fUserStatus){
        BUser user = (BUser)SecurityUtils.getSubject().getPrincipal();
        int lid = fUserStatus.getSid();
        // 降级并且重置邀请人数
        // 特殊等级
        if (SystemConst.SPECIALLEVEL.contains(lid)){
            fUserStatus.setSid(lid);
            QueryWrapper<FUserInvitenubers> uid = new QueryWrapper<FUserInvitenubers>().eq("uid", user.getId());
            FUserInvitenubers fUserInvitenubers = this.fUserInvitenubers.list(uid).get(0);
            fUserInvitenubers.setInvitenumbers(SystemConst.EXP.get(lid - 1));
            this.fUserInvitenubers.updateById(fUserInvitenubers);
            fUserStatusService.updateById(fUserStatus);
            // 非特殊等级时
        } else if (lid > 100) {
            fUserStatus.setSid(lid);
            QueryWrapper<FUserInvitenubers> uid = new QueryWrapper<FUserInvitenubers>().eq("uid", user.getId());
            FUserInvitenubers fUserInvitenubers = this.fUserInvitenubers.list(uid).get(0);
            fUserInvitenubers.setInvitenumbers(SystemConst.EXP.get(lid));
            this.fUserInvitenubers.updateById(fUserInvitenubers);
            fUserStatusService.updateById(fUserStatus);
        }

        // 发送降级通知
        BMessage message = new BMessage();
        message.setCreatetime(new Date());
        message.setDiscription("您的等级被管理员降低至" + (lid - 1));
        message.setOrderid(fUserStatus.getUid());
        message.setSendid(0);
        message.setTitle("系统消息");
        message.setType(1);
        // 保存降级记录
        BRecord bRecord = new BRecord();
        bRecord.setContent("长时间未登录被降级至" + (lid -1));
        bRecord.setCreatetime(new Date());
        bRecord.setType(1);
        bRecord.setCuid(user.getId());
        bRecord.setUid(user.getId());

        bMessageService.save(message);
        recordService.save(bRecord);
        fUserStatusService.updateById(fUserStatus);

        return ResultUtil.setOK("调整成功");
    }
}
