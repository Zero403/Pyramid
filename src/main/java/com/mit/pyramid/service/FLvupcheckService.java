package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.entity.FLvupcheck;
import com.mit.pyramid.entity.FUserStatus;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ery
 * @since 2019-05-02
 */
public interface FLvupcheckService extends IService<FLvupcheck> {

    boolean allotLV5(FUserStatus fUserStatus);

    boolean uptoLV1(FUserStatus fUserStatus);

}
