package com.mit.pyramid.service;

import com.mit.pyramid.common.vo.ResultVO;


public interface FLVRequestService {

    /**
     * 获取可以升级的等级的方法。
     * @param uid
     *      传入申请升级的用户的id
     * @return
     *      可以申请的等级结果集
     */
    ResultVO canUptoLevel(Integer uid);

}
