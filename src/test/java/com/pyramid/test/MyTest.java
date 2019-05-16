package com.pyramid.test;

import com.mit.pyramid.PyramidApplication;
import com.mit.pyramid.common.util.InviteCodeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PyramidApplication.class)
public class MyTest {

    @Test
    public void utilTest(){
        String s = InviteCodeUtils.createInviteCode(13);
        System.out.println(s);
        int s1 = InviteCodeUtils.checkInviteCode(s);
        System.out.println(s1);
    }
}
