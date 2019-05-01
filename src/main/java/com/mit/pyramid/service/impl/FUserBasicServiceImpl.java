package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.api.InviteCodeAPI;
import com.mit.pyramid.common.util.AESUtil;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.RegisterVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.service.FUserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public ResultVO userRegister(RegisterVO user) {

        FUserBasic fUserBasic = new FUserBasic();

        Integer inviteId = InviteCodeAPI.checkInviteCode(user.getInvitecode());

        if(dao.selectByName(user.getUsername()) != null || dao.selectById(inviteId) == null) {
            return ResultUtil.setERROR();
        }

        fUserBasic.setUsername(user.getUsername());
        fUserBasic.setPhone(user.getPhone());
        fUserBasic.setInviterid(inviteId);
        fUserBasic.setPassword(AESUtil.dcodes(user.getPassword(), "4484AD3CBA81DB0978D699139CB973B8"));
        fUserBasic.setCreatedate(new Date());
        fUserBasic.setFlag(0);
        dao.insert(fUserBasic);

        return ResultUtil.setOK("注册成功！");
    }


}
