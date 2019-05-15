package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.CheckVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.entity.FLvupcheck;
import com.mit.pyramid.dao.FLvupcheckMapper;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.BMessageService;
import com.mit.pyramid.service.FLvupcheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-02
 */
@Service
public class FLvupcheckServiceImpl extends ServiceImpl<FLvupcheckMapper, FLvupcheck> implements FLvupcheckService {

    @Autowired
    private FLvupcheckMapper checkDao;
    @Autowired
    private BMessageService messageService;


    @Override
    public boolean allotLV5(FUserStatus fUserStatus) {
        return false;
    }

    @Override
    public boolean uptoLV1(FUserStatus fUserStatus) {
        return false;
    }

    @Override
    public ResultVO checkList(Integer uid) {
        List<CheckVO> checkList = checkDao.checkList(uid);
        if(checkList == null) {
            return ResultUtil.setERROR("没有消息");
        } else {
            return ResultUtil.exec(true,"等待您审批的列表", checkList);
        }
    }

    @Override
    public ResultVO myCheck(Integer uid) {
        CheckVO mycheck = checkDao.myCheck(uid);
        if (mycheck.getId() == null) {
            return ResultUtil.setERROR("不需要审核");
        } else {
            return ResultUtil.exec(true,"您需要联系此人审核",mycheck);
        }
    }

    @Override
    public ResultVO checkById(int id) {
        CheckVO checkVO = baseMapper.checkOne(id);
        return ResultUtil.exec(checkVO != null, "该用户是否符合条件，请判断", checkVO);
    }

    @Autowired
    private FUserStatusMapper userStatusDao;

    @Override
    public ResultVO checkOK(int id, int flag){
        checkDao.checkResult(id, flag);
        FLvupcheck check = checkDao.selectById(id);
        BMessage bMessage = new BMessage();
        if(check.getStatus() == 1) {
            bMessage.setTitle("审批通过");
            bMessage.setDiscription("恭喜你，升级成功！");
            bMessage.setOrderid(check.getLowuid());
            bMessage.setSendid(1);
            bMessage.setType(1);
            messageService.sendMessage(bMessage);
            int uid = check.getLowuid();
            FUserStatus fUserStatus = new FUserStatus();
            fUserStatus.setSid(check.getSid());
            fUserStatus.setUid(uid);
            userStatusDao.updateById(fUserStatus);
            return ResultUtil.setOK("审批通过");
        } else if(check.getStatus() == 2){
            bMessage.setTitle("审批不通过");
            bMessage.setDiscription("很抱歉，您的审批未经过审批人的同意。请重新申请。如果有异议，请及时举报");
            bMessage.setOrderid(check.getLowuid());
            bMessage.setSendid(1);
            bMessage.setType(1);
            messageService.sendMessage(bMessage);
            return ResultUtil.setOK("审核不批准");
        } else {
            return ResultUtil.setERROR("无操作");
        }
    }
}
