package com.mit.pyramid.common.util;

import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.entity.FUserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InviteCodeUtils {

    @Autowired
    private FUserBasicMapper userDao;

    private static InviteCodeUtils userMapper;

    @PostConstruct
    public void init() {
        userMapper = this;
    }

    public static String createInviteCode(int uid) {
        String uidString = String.valueOf(uid);
        StringBuilder stringBuffer = new StringBuilder();
        Integer outsider = userMapper.userDao.selectById(uid).getOutsider();
        if (outsider == 0) {
            if (uid % 2 == 0) {
                stringBuffer.append('A');
            } else {
                stringBuffer.append('B');
            }
        } else {
            if (uid % 2 == 0) {
                stringBuffer.append('C');
            } else {
                stringBuffer.append('D');
            }
        }
        int size = uidString.length();
        for (int i = 0; i < 7 - size; i++) {
            stringBuffer.append('0');
        }
        stringBuffer.append(uidString);
        return stringBuffer.toString();

    }

    public static Integer checkInviteCode(String inviteCode) {

        Integer uid = Integer.parseInt(inviteCode.substring(1));
        try {
            FUserBasic user = userMapper.userDao.selectById(uid);
            if (user != null) {
                boolean ut = (user.getOutsider() == 0 && (inviteCode.startsWith("A") || inviteCode.startsWith("B")))
                        || (user.getOutsider() == 1 && (inviteCode.startsWith("C") || inviteCode.startsWith("D")));
                if (ut) {
                    return uid;
                }
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

}
