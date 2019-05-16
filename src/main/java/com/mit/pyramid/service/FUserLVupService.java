package com.mit.pyramid.service;

import com.mit.pyramid.common.vo.ResultVO;

public interface FUserLVupService {

    ResultVO lvUp(int uid, int sid);

    ResultVO getEXP(String token);
}
