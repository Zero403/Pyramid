package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
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

    ResultVO checkList(Integer uid);

    ResultVO myCheck(Integer uid);

    ResultVO checkById(int id);

    ResultVO checkOK(int id, int flag);
}
