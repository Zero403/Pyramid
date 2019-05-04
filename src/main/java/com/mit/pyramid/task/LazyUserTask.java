package com.mit.pyramid.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.dao.FStatusMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.*;
import com.mit.pyramid.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LazyUserTask {
    @Autowired
    private BMessageService bMessageService;
    @Autowired
    private FUserBasicService fUserBasicService;
    @Autowired
    private BRecordService recordService;
    @Autowired
    private FUserStatusService  fUserStatusService;

    @Autowired
    private FUserInvitenubersService fUserInvitenubers;

    @Scheduled(cron="0 0 6 1/1 * ? *")
    public void lazyUser(){

        // 查询所有上次登录时间大于xx的用户
        List<FUserBasic> userList = fUserBasicService.userLazy(SystemConst.LAZYDAYS);
        // 将用户等级降低至指定等级并且保存记录
        List<BRecord> list = new ArrayList<>();
        for (FUserBasic user:userList) {
            FUserStatus fUserStatus = fUserStatusService.getById(user.getId());
            int id = fUserStatus.getSid();

            // 当前等级为特殊等级时
            if (SystemConst.SPECIALLEVEL.contains(id)){
                fUserStatus.setSid(id - 1);
                fUserStatusService.updateById(fUserStatus);
            // 非特殊等级时
            } else if (id > 100) {
                fUserStatus.setSid(id - 1);
                QueryWrapper<FUserInvitenubers> uid = new QueryWrapper<FUserInvitenubers>().eq("uid", user.getId());
                FUserInvitenubers fUserInvitenubers = this.fUserInvitenubers.list(uid).get(0);
                fUserInvitenubers.setInvitenumbers((Integer) SystemConst.EXP.get(id - 1));
                this.fUserInvitenubers.updateById(fUserInvitenubers);
                fUserStatusService.updateById(fUserStatus);
            }
            // 给降级的用户发送消息，保存降级记录
            if (id > 100) {
                BMessage message = new BMessage();
                message.setCreatetime(new Date());
                message.setDiscription("由于您长时间未登录，等级降低至" + (id - 1));
                message.setOrderid(user.getId());
                message.setSendid(0);
                message.setTitle("系统消息");
                message.setType(1);
                BRecord bRecord = new BRecord();
                bRecord.setContent("长时间未登录被降级至" + (id -1));
                bRecord.setCreatetime(new Date());
                bRecord.setType(1);
                bRecord.setCuid(0);
                bRecord.setUid(user.getId());
                bMessageService.save(message);
                recordService.save(bRecord);
            }
        }

    }

    // 每天早上8点刷新等级排名榜只显示前50
    @Scheduled(cron = "0 0 8 ? * * *")
    public void rankList(){

    }

    // 每天早上8点刷新邀请榜只显示前50
    @Scheduled(cron = "0 0 8 ? * * *")
    public void inviteList(){

    }
}
