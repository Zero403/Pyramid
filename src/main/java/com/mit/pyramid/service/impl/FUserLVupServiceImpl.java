package com.mit.pyramid.service.impl;

import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FLvupcheckMapper;
import com.mit.pyramid.dao.FUserInvitenubersMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FUserLVupService;
import org.springframework.beans.factory.annotation.Autowired;


public class FUserLVupServiceImpl implements FUserLVupService {

    @Autowired
    private FUserStatusMapper userStatusDao;

    @Autowired
    private FUserInvitenubersMapper inviteEXPDao;

    @Autowired
    private FLvupcheckMapper checkDao;

    @Override
    public ResultVO lvUp(int uid, int sid) {
        if (sid == 100) {
            //TODO 审核的方法0-1
            return ResultUtil.setOK("等待审核");
        } else {
            int exp = inviteEXPDao.selectById(uid).getInvitenumbers();
            if (sid == 105 && exp >= SystemConst.UPNEED.get(104)) {
                //TODO 审核的方法4-5
                return ResultUtil.setOK("等待审核");
            } else if (sid == 109 && exp >= SystemConst.UPNEED.get(108)) {
                //TODO 审核的方法 8-9
                return ResultUtil.setOK("等待审核");
            } else {
                if (exp > SystemConst.UPNEED.get(sid)) {
                    FUserStatus fUserStatus = new FUserStatus();
                    fUserStatus.setUid(uid);
                    fUserStatus.setSid(sid);
                    userStatusDao.updateById(fUserStatus);
                    // TODO 发消息

                    return ResultUtil.setOK("恭喜!升级成功！！");

                }
            }
        }
        return ResultUtil.setERROR("您的条件不足以到达这个等级");
    }

/*
 @Autowired
    private FUserStatusMapper userStatusDao;

    @Autowired
    private FStatusMapper statusDao;

    @Autowired
    private FUserInvitenubersMapper inviteEXPDao;

    @Override
    public ResultVO canUptoLevel(Integer uid) {
        int sid = userStatusDao.selectById(uid).getSid();
        List<FStatus> canUp = new ArrayList<>();
        if (sid == 100) {
            canUp = statusDao.lv1List();
        } else {
            int exp = inviteEXPDao.selectById(uid).getInvitenumbers();
            if (sid < 105) {
                if (exp > SystemConst.UPTO4) {
                    canUp = statusDao.lvStarList(sid + 1, 105);
                } else if (exp > SystemConst.UPTO3) {
                    canUp = statusDao.lvStarList(sid + 1, 103);
                } else if (exp > SystemConst.UPTO2) {
                    canUp = statusDao.lvStarList(sid + 1, 102);
                }
            } else if (sid < 109) {
                if (exp > SystemConst.UPTO8) {
                    canUp = statusDao.lvStarList(sid + 1, 109);
                } else if (exp > SystemConst.UPTO7) {
                    canUp = statusDao.lvStarList(sid + 1, 107);
                } else if (exp > SystemConst.UPTO6) {
                    canUp = statusDao.lvStarList(sid + 1, 106);
                }
            } else if (sid < 113) {
                if (exp > SystemConst.UPTO13) {
                    canUp = statusDao.lvStarList(sid + 1, 113);
                } else if (exp > SystemConst.UPTO12) {
                    canUp = statusDao.lvStarList(sid + 1, 112);
                } else if (exp > SystemConst.UPTO11) {
                    canUp = statusDao.lvStarList(sid + 1, 111);
                } else if (exp > SystemConst.UPTO10) {
                    canUp = statusDao.lvStarList(sid + 1, 110);
                }
            }
        }

        return ResultUtil.exec(true, "可以升级的列表", canUp);
    }
 */
}
