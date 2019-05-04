package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface BUserService extends IService<BUser> {

    ResultVO addUser(BUser user);

    void login(String userName, String password, boolean rememberMe);


}
