package com.mit.pyramid.service;

import com.mit.pyramid.common.vo.ResultVO;

public interface FInviteCodeService {

    ResultVO getInviteCode(String token);

    ResultVO checkInviteCode(String inviteCode);

}
