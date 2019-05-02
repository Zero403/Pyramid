package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.dao.BMessageMapper;
import com.mit.pyramid.dao.BUserLevelDownMapper;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.entity.BUserLevelDown;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.BMessageService;
import com.mit.pyramid.service.BUserLevelDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@Service
public class BUserLevelDownServiceImpl extends ServiceImpl<BUserLevelDownMapper, BUserLevelDown> implements BUserLevelDownService {


    @Override
    public List<UserLevelDownVO> findAll(Page<UserLevelDownVO> page) {
        return baseMapper.volist(page);
    }
}
