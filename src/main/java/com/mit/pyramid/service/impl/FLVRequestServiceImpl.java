package com.mit.pyramid.service.impl;

import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FStatusMapper;
import com.mit.pyramid.dao.FUserInvitenubersMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.FStatus;
import com.mit.pyramid.service.FLVRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FLVRequestServiceImpl implements FLVRequestService {

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
}
