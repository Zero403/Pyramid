package com.mit.pyramid.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.entity.BAgent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chen
 * @since 2019-05-02
 */
public interface BAgentService extends IService<BAgent> {

    int addAgent(BAgent agent);


	
}
