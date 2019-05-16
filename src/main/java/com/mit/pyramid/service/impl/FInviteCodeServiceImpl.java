package com.mit.pyramid.service.impl;

import com.mit.pyramid.common.util.InviteCodeUtils;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.service.FInviteCodeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FInviteCodeServiceImpl implements FInviteCodeService {

    @Override
    public ResultVO getInviteCode(String token) {
        int uid = TokenUtil.parseToken(token).getUid();
        String inviteCode = InviteCodeUtils.createInviteCode(uid);
        Map<String, String> m = new HashMap<>();
        m.put("invitecode", inviteCode);
        return ResultUtil.exec(inviteCode.length() > 0, "生成完毕", m);
    }

    @Override
    public ResultVO checkInviteCode(String inviteCode) {
        Integer uid = InviteCodeUtils.checkInviteCode(inviteCode);
        return uid > 0 ? ResultUtil.setOK("邀请码正确") : ResultUtil.setERROR("邀请码错误");
    }
}
