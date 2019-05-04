package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.api.InviteCodeAPI;
import com.mit.pyramid.common.util.AESUtil;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.BUserBasicVO;
import com.mit.pyramid.common.vo.RegisterVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FUserInvitenubersMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.entity.FUserInvitenubers;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FUserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@Service
public class FUserBasicServiceImpl extends ServiceImpl<FUserBasicMapper, FUserBasic> implements FUserBasicService {


    @Autowired
    private FUserBasicMapper dao;

    @Autowired
    private FUserInvitenubersMapper numberdao;

    @Autowired
    private FUserStatusMapper statusdao;

    @Override
    public ResultVO userRegister(RegisterVO user) {

        FUserBasic fUserBasic = new FUserBasic();

        Integer inviteId = InviteCodeAPI.checkInviteCode(user.getInvitecode());

        if(dao.selectByName(user.getUsername()) != null) {
            return ResultUtil.setERROR("注册失败，用户名重复");
        }

        if(dao.selectById(inviteId) == null) {
            return ResultUtil.setERROR("注册失败，邀请码不存在");
        }
        fUserBasic.setUsername(user.getUsername());
        fUserBasic.setPhone(user.getPhone());
        fUserBasic.setInviterid(inviteId);
        fUserBasic.setPassword(AESUtil.ecodes(user.getPassword(), "4484AD3CBA81DB0978D699139CB973B8"));
        fUserBasic.setCreatedate(new Date());
        fUserBasic.setFlag(0);
        dao.insertKey(fUserBasic);
        Integer uid = fUserBasic.getId();
        FUserStatus fUserStatus = new FUserStatus();
        fUserStatus.setSid(1);
        fUserStatus.setUid(uid);
        statusdao.insert(fUserStatus);
        FUserInvitenubers fUserInvitenubers = numberdao.selectById(inviteId);
        fUserInvitenubers.setInvitenumbers(fUserInvitenubers.getInvitenumbers() + 1);
        numberdao.updateById(fUserInvitenubers);

        return ResultUtil.setOK("注册成功！");
    }

    @Override
    public List<FUserBasic> userIllegal(Page<FUserBasic> page) {
        return baseMapper.selectIllgal(page);
    }

    @Override
    public List<FUserBasic> userLazy(int days) {
        return baseMapper.selectLazy(days);
    }

    @Override
    public List<BUserBasicVO> listByPage(Page<BUserBasicVO> page) {
        return dao.selectAllByPage(page);
    }

    @Override
    public int insertUser(FUserBasic userBasic, int status) {
        dao.insert(userBasic);

        FUserStatus userStatus = new FUserStatus();
        userStatus.setUid(userBasic.getId());
        userStatus.setSid(status);
        statusdao.insert(userStatus);
        return 1;
    }

//    @Override
//    public int addBatch(List<BUserBasicVO> list) {
//
//        List<FUserBasic> list1 = null;
//
//        for(BUserBasicVO bUserBasicVO : list){
//            FUserBasic userBasic = bUserBasicVO.getUserBasic();
//            list1.add(userBasic);
//        }
//
//
//
//
//        return 0;
//    }

}
