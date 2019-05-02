package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.dao.BUserMapper;
import com.mit.pyramid.service.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BUserServiceImpl extends ServiceImpl<BUserMapper, BUser> implements BUserService {

    @Autowired
    private BUserMapper bUserDao;

    @Override
    public ResultVO addUser(BUser user) {
        user.setId(null);
        user.setFlag(1);
        boolean flag = bUserDao.insert(user) > 0;
        return ResultUtil.exec(flag, flag?"新增成功":"新增失败", null);
    }

}
