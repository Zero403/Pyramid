package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.RegisterVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.FUserBasic;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface FUserBasicService extends IService<FUserBasic> {

    ResultVO userRegister(RegisterVO user);

}
