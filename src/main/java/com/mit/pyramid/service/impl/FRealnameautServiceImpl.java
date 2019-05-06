package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FRealnameautMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.FRealnameaut;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FRealnameautService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRealnameautServiceImpl extends ServiceImpl<FRealnameautMapper, FRealnameaut> implements FRealnameautService {

    @Autowired
    private FRealnameautMapper realnameDao;

    @Autowired
    private FUserStatusMapper userStatusDao;

    @Override
    public ResultVO selectUserBasicById(int uid) {
        FUserBasic fUserBasic = realnameDao.selectUserBasicById(uid);
        if (fUserBasic != null) {
            return ResultUtil.exec(true, "", fUserBasic);
        }
        return ResultUtil.setERROR("认证用户不存在");
    }

    @Override
    public ResultVO selectRealnameByUid(int uid) {

        FRealnameaut fRealnameaut = realnameDao.selectFRealnameautByUid(uid);

        if (fRealnameaut != null) {
            return ResultUtil.setERROR("您已完成实名认证，请勿重复操作");
        }
        return ResultUtil.setOK("您未进行实名认证，请完成认证");
    }

    @Override
    public ResultVO insertRealname(FRealnameaut fRealnameaut) {

        Integer uid = fRealnameaut.getUid();
        FUserStatus fUserStatus = userStatusDao.selectById(uid);
        FUserBasic fUserBasic = realnameDao.selectUserBasicById(uid);
        FRealnameaut fRealnameaut1 = realnameDao.selectFRealnameautByUid(uid);
        if (fUserBasic == null) {
            return ResultUtil.setERROR("认证用户不存在");
        } else if (fRealnameaut1 != null) {
            return ResultUtil.setERROR("该用户已完成认证");
        } else if (fUserStatus != null && fUserStatus.getSid() == 1) {

            int i = realnameDao.insert(fRealnameaut);
            fUserStatus.setSid(100);
            userStatusDao.updateById(fUserStatus);
            return ResultUtil.setOK(i + "实名认证信息保存成功");

        } else {
            return ResultUtil.setERROR("您不符合认证资格，请查阅等级规则后再来");
        }
    }
}



