package com.mit.pyramid;

import com.mit.pyramid.common.api.InviteCodeAPI;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.AESUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.CheckVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.dao.FLvupcheckMapper;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FLVRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PyramidApplicationTests {
    @Autowired
    private FUserStatusMapper dao;

    @Autowired
    private FLVRequestService service;
    /**
     * 测试邀请码用，邀请码DD6264CB5540DF3126AD1F5E9FF5D0F1(uid == 2)
     */
    @Test
    public void contextLoads() {
        String inviteCode = InviteCodeAPI.createInviteCode(2);
        System.out.println(inviteCode);
        String passwordAES = AESUtil.dcodes("4876C677635A6B85CD332B3B73F6F2AD", SystemConst.PASS);
        System.out.println(passwordAES);

    }


    @Test
    public void MPtest() {
        Map<String,Object> map = new HashMap<>();
        map.put("sid", 105);
        List<FUserStatus> fUserStatuses = dao.selectByMap(map);
        for (FUserStatus fUserStatus : fUserStatuses) {
            System.out.println(fUserStatus);
        }
    }

    @Test
    public void upListTest() {
        ResultVO vo = service.canUptoLevel(2);
        System.out.println(vo);
    }

    @Test
    public void TestToken() {
        String token = TokenUtil.createToken(2, "123456789");
        System.out.println(token);
        TokenVO tokenVO = TokenUtil.parseToken(token);
        System.out.println(tokenVO.getContent());
    }

    @Autowired
    private FLvupcheckMapper checkDao;

    @Test
    public void TestCheckVO() {
        CheckVO checkList = checkDao.myCheck(5);
        System.out.println(checkList);
    }
}
