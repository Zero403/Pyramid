package com.mit.pyramid.task;

import com.mit.pyramid.service.BMessageService;
import com.mit.pyramid.service.FUserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LazyUserTask {
    @Autowired
    private BMessageService bMessageService;
    @Autowired
    private FUserBasicService fUserBasicService;
    @Scheduled(cron="0 0 6 1/1 * ? *")
    public void lazyUser(){
        // 查询所有上次登录时间大于xx的用户
        // 将用户等级降低至指定等级
        // 给降级的用户发送通知消息
    }
}
