package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.dao.BUserMapper;
import com.mit.pyramid.service.BUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@Service
public class BUserServiceImpl extends ServiceImpl<BUserMapper, BUser> implements BUserService {
	
}
