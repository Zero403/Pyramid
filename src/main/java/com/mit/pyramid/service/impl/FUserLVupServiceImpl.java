package com.mit.pyramid.service.impl;

import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ResultUtil;
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
            sendCheck(uid,sid);
            return ResultUtil.setOK("等待审核");
        } else {
            int exp = inviteEXPDao.selectById(uid).getInvitenumbers();
            int maxnum = userDao.selectCount(null);
            Map<Integer ,Integer > proptton = bProportionService.findProporton();
            int constLV = proptton.get(5) + proptton.get(9) + proptton.get(13);
            int lv5 = maxnum * (proptton.get(5) / constLV);
            int lv9 = maxnum * (proptton.get(9) / constLV);
            int lv13 = maxnum * (proptton.get(13) / constLV);

            if (sid == 105 && exp >= SystemConst.UPNEED.get(104)) {
               if(userStatusDao.lv5num() > lv5) {
                   return ResultUtil.setERROR("抱歉，当前等级人数较多。");
               }
                sendCheck(uid,sid);
                return ResultUtil.setOK("等待审核");
            } else if (sid == 109 && exp >= SystemConst.UPNEED.get(108)) {
                if(userStatusDao.lv9num() > lv9) {
                    return ResultUtil.setERROR("抱歉，当前等级人数较多。");
                }
                sendCheck(uid,sid);
                return ResultUtil.setOK("等待审核");
            } else if(sid == 113 && userStatusDao.lv13num() > lv13) {
                return ResultUtil.setERROR("抱歉，当前等级人数较多。");
            } else {
                if (exp > SystemConst.UPNEED.get(sid)) {
                    lvUpOpe(uid,sid);

                    return ResultUtil.setOK("恭喜!升级成功！！");
                }
            }
        }
        return ResultUtil.setERROR("您的条件不足以到达这个等级");
    }

    private void lvUpOpe(int uid, int sid) {
        FUserStatus fUserStatus = new FUserStatus();
        fUserStatus.setUid(uid);
        fUserStatus.setSid(sid);
        userStatusDao.updateById(fUserStatus);
    }

    private void sendCheck(int uid, int sid) {
        int specId = sid == 100 ? 105:(sid == 105? 109 : 113);
        FUserStatus spec = userStatusDao.rand1SpecialUser(specId);
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
}
