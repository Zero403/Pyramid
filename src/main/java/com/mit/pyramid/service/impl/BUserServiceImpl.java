package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.dao.BUserMapper;
import com.mit.pyramid.service.BUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BUserMapper bUserDao;

    @Override
    public ResultVO addUser(BUser user) {
        user.setId(null);
        user.setFlag(1);
        boolean flag = bUserDao.insert(user) > 0;
        return ResultUtil.exec(flag, flag?"新增成功":"新增失败", null);
    }

    @Override
    public void login(String userName, String password, boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        if(rememberMe){token.setRememberMe(true);}
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

}

