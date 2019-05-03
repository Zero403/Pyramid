package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.entity.FLvupcheck;
import com.mit.pyramid.dao.FLvupcheckMapper;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FLvupcheckService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-02
 */
@Service
public class FLvupcheckServiceImpl extends ServiceImpl<FLvupcheckMapper, FLvupcheck> implements FLvupcheckService {

    @Override
    public boolean allotLV5(FUserStatus fUserStatus) {
        return false;
    }

    @Override
    public boolean uptoLV1(FUserStatus fUserStatus) {
        return false;
    }
}
