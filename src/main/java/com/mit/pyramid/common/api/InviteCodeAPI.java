package com.mit.pyramid.common.api;

import com.mit.pyramid.common.util.AESUtil;

public class InviteCodeAPI {

    public static String createInviteCode(int uid) {
        String uidString = String.valueOf(uid);
        String invite = AESUtil.ecodes(uidString, "invite");
        return invite;
    }

    public static Integer checkInviteCode(String inviteCode) {
        String uidString = AESUtil.dcodes(inviteCode, "invite");

       try {
           Integer uid = Integer.parseInt(uidString);
           return uid;
       } catch (Exception e) {
           return -1;
       }

    }

}