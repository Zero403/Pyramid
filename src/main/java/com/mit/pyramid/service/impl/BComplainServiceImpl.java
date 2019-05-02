package com.mit.pyramid.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.dao.BComplainMapper;
import com.mit.pyramid.entity.BComplain;
import com.mit.pyramid.service.BComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
@Service
public class BComplainServiceImpl extends ServiceImpl<BComplainMapper, BComplain> implements BComplainService {

    @Autowired
    private BComplainMapper bComplainDao;

    @Override
    public List<BComplain> listByStatus(int status) {

        return bComplainDao.listComplainByStatus(status);
    }


//    @Override
//    public BComplain listAllByPage(int page, int limit) {
//    }
}
