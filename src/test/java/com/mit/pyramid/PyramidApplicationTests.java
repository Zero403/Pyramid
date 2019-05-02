package com.mit.pyramid;

import com.mit.pyramid.common.api.InviteCodeAPI;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.AESUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PyramidApplicationTests {

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

}
