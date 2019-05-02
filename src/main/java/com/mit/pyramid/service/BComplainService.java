package com.mit.pyramid.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.entity.BComplain;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
 
public interface BComplainService extends IService<BComplain> {

//    public BComplain listAllByPage(int page,int limit);

    public List<BComplain> listByStatus(int status);
	
}
