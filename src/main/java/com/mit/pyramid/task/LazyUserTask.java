package com.mit.pyramid.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.dao.FStatusMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.BRecord;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserInvitenubers;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.ArrayList;
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
    private FUserStatusMapper userStatusDao;

    @Autowired
    private FUserInvitenubersService fUserInvitenubers;

    @Scheduled(cron="0 0 6 1/1 * ? *")
    public void lazyUser(){

        // 查询所有上次登录时间大于xx的用户
        List<FUserBasic> userList = fUserBasicService.userLazy(SystemConst.LAZYDAYS);
        // 将用户等级降低至指定等级
        for (FUserBasic user:userList) {
            FUserStatus fUserStatus = userStatusDao.selectById(user.getId());
            int id = fUserStatus.getSid();

            // 当前等级为特殊等级时
            if (SystemConst.SPECIALLEVEL.contains(id)){
                fUserStatus.setSid(id - 1);
                userStatusDao.updateById(fUserStatus);
            // 非特殊等级时
            } else if (id > 100) {
                fUserStatus.setSid(id - 1);
                QueryWrapper<FUserInvitenubers> uid = new QueryWrapper<FUserInvitenubers>().eq("uid", user.getId());
                FUserInvitenubers fUserInvitenubers = this.fUserInvitenubers.list(uid).get(0);
                fUserInvitenubers.setInvitenumbers((Integer) SystemConst.EXP.get(id - 1));
                this.fUserInvitenubers.updateById(fUserInvitenubers);
                userStatusDao.updateById(fUserStatus);
            }
        }
        // 保存处理记录
        List<BRecord> list = new ArrayList<>();

        recordService.saveBatch(list);
        // 给降级的用户发送通知消息

    }
}
