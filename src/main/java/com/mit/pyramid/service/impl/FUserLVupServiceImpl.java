package com.mit.pyramid.service.impl;

import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.*;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.entity.FLvupcheck;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.BMessageService;
import com.mit.pyramid.service.BProportionService;
import com.mit.pyramid.service.FUserLVupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FUserLVupServiceImpl implements FUserLVupService {

    @Autowired
    private FUserBasicMapper userDao;

    @Autowired
    private FUserStatusMapper userStatusDao;

    @Autowired
    private FUserInvitenubersMapper inviteEXPDao;

    @Autowired
    private FLvupcheckMapper checkDao;

    @Autowired
    private BProportionService bProportionService;

    @Autowired
    private BMessageService messageService;

    @Override
    public ResultVO lvUp(int uid, int sid) {
        if (sid == 101) {
            sendCheck(uid, sid);
            return ResultUtil.setOK("等待审核");
        } else {
            int exp = inviteEXPDao.selectById(uid).getInvitenumbers();
            int maxnum = userDao.selectCount(null);
            Map<Integer, Integer> proptton = bProportionService.findProporton();
            int constLV = proptton.get(5) + proptton.get(9) + proptton.get(13);
            int lv5 = maxnum * (proptton.get(5) / constLV);
            int lv9 = maxnum * (proptton.get(9) / constLV);
            int lv13 = maxnum * (proptton.get(13) / constLV);

            if (sid == 105 && exp >= SystemConst.UPNEED.get(104)) {
                if (userStatusDao.lv5num() > lv5) {
                    return ResultUtil.setERROR("抱歉，当前等级人数较多。");
                }
                if (!sendCheck(uid, sid)) {
                    return ResultUtil.setERROR("您已经提交过您的审核了");
                }
                return ResultUtil.setOK("等待审核");
            } else if (sid == 109 && exp >= SystemConst.UPNEED.get(108)) {
                if (userStatusDao.lv9num() > lv9) {
                    return ResultUtil.setERROR("抱歉，当前等级人数较多。");
                }
                if (!sendCheck(uid, sid)) {
                    return ResultUtil.setERROR("您已经提交过您的审核了");
                }
                return ResultUtil.setOK("等待审核");
            } else if (sid == 113 && userStatusDao.lv13num() > lv13) {
                return ResultUtil.setERROR("抱歉，当前等级人数较多。");
            } else {
                if (exp > SystemConst.UPNEED.get(sid)) {
                    if (! sendCheck(uid, sid)) {
                        return ResultUtil.setERROR("您已经提交过您的审核了");
                    }
                    return ResultUtil.setOK("等待审核");
                }
            }
        }
        return ResultUtil.setERROR("您的条件不足以到达这个等级");
    }

    @Override
    public ResultVO getEXP(String token) {
        int uid = TokenUtil.parseToken(token).getUid();
        int exp = inviteEXPDao.selectById(uid).getInvitenumbers() * 10;
        Map<String, Integer> expMap = new HashMap<>();
        expMap.put("exp", exp);
        return ResultUtil.exec(true, "积分", expMap);

    }

    private void lvUpOpe(int uid, int sid) {
        FUserStatus fUserStatus = new FUserStatus();
        fUserStatus.setUid(uid);
        fUserStatus.setSid(sid);
        userStatusDao.updateById(fUserStatus);
        BMessage bMessage = new BMessage();
        bMessage.setType(1);
        bMessage.setSendid(1);
        bMessage.setOrderid(uid);
        bMessage.setTitle("恭喜你，本次升级直接升级成功！！");
    }

    private boolean sendCheck(int uid, int sid) {
        int specId = sid < 105 ? 105 : (sid < 109 ? 109 : 113);
        FUserStatus spec = userStatusDao.rand1SpecialUser(specId);
        if (spec == null) {
            lvUpOpe(uid, sid);
        } else {
            if (checkDao.myCheck(uid) != null) {
                return false;
            }
            FLvupcheck check = new FLvupcheck();
            check.setLowuid(uid);
            check.setHeightuid(spec.getUid());
            check.setStatus(0);
            check.setSid(sid);
            checkDao.insert(check);
            BMessage bMessage = new BMessage();
            bMessage.setType(1);
            bMessage.setSendid(1);
            bMessage.setOrderid(uid);
            bMessage.setTitle("您的升级需要审核");
            bMessage.setDiscription("您此次升级需要联系此人进行审核：" + checkDao.myCheck(uid).checkingToString());
            messageService.sendMessage(bMessage);
        }
        return true;
    }

}
