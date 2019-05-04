package com.mit.pyramid.task;

import com.mit.pyramid.service.FUserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RankTask {

    @Autowired
    private FUserBasicService fUserBasicService;
    // 每天早上8点刷新等级排名榜只显示前50
    @Scheduled(cron = "0 0 8 ? * * *")
    public void rankList(){

        fUserBasicService.rankList();
    }

    // 每天早上8点刷新邀请榜只显示前50
    @Scheduled(cron = "0 0 8 ? * * *")
    public void inviteList(){

        fUserBasicService.inviteList();

    }


}




