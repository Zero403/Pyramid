package com.mit.pyramid;

import com.mit.pyramid.common.api.InviteCodeAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PyramidApplicationTests {

    @Test
    public void contextLoads() {
        String inviteCode = InviteCodeAPI.createInviteCode(2);
        System.out.println(inviteCode);
    }

}
