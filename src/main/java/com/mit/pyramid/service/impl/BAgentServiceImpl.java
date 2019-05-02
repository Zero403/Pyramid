package com.mit.pyramid.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.dao.BAgentMapper;
import com.mit.pyramid.entity.BAgent;
import com.mit.pyramid.service.BAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.font.ShapeGraphicAttribute;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chen
 * @since 2019-05-02
 */
@Service
public class BAgentServiceImpl extends ServiceImpl<BAgentMapper, BAgent> implements BAgentService {

    @Autowired
    private BAgentMapper agentDao;
    @Override
    public int addAgent(BAgent agent) {
//        agent.setSettleingtime(new Date());
        return agentDao.insertAgent(agent);
    }
}
