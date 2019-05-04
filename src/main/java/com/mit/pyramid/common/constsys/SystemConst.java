package com.mit.pyramid.common.constsys;

import io.swagger.models.auth.In;
import java.util.*;

/**
 * @Author feri
 * @Date Created in 2019/4/28 17:30
 */
public class SystemConst {
    //返回码
    public static final int OK = 1000;
    public static final int ERROR = 1001;

    //短信模板
    public static final String DTMB = "SMS_164470243";
    public static final String YZMMB = "SMS_114390520";

    //秘钥
    public static final String TOKENKEY = "PTeW11QpXc+IKUAcO8e8FA==";

    /**
     * 密码解密用key
     */
    public static final String PASS = "4484AD3CBA81DB0978D699139CB973B8";

    public static final int UPTO2 = 3;
    public static final int UPTO3 = UPTO2 + 3;
    public static final int UPTO4 = UPTO3 + 3;
    public static final int UPTO6 = UPTO4 + 3;
    public static final int UPTO7 = UPTO6 + 3;
    public static final int UPTO8 = UPTO7 + 3;
    public static final int UPTO10 = UPTO8 + 3;
    public static final int UPTO11 = UPTO10 + 3 * 3;
    public static final int UPTO12 = UPTO11 + 3 * 3 * 3;
    public static final int UPTO13 = UPTO12 + 3 * 3 * 3;

    /**
     * 懒惰用户判断天数
     */
    public static final int LAZYDAYS = 7;

    public static final List<Integer> SPECIALLEVEL = Arrays.asList(105,109,113);

    public static Map<Integer,Integer> EXP = new HashMap<>();
    static {
        EXP.put(101,0);
        EXP.put(102,UPTO2);
        EXP.put(103,UPTO3);
        EXP.put(104,UPTO4);
        EXP.put(106,UPTO6);
        EXP.put(107,UPTO7);
        EXP.put(108,UPTO8);
        EXP.put(110,UPTO10);
        EXP.put(111,UPTO11);
        EXP.put(112,UPTO12);
        EXP.put(113,UPTO13);

    }
    /**
     * JDK1.8新增特性，MAP常量的初始化
     */
    public static final Map<Integer,Integer> UPNEED = EXP;
//
//    static {
//        UPNEED = new HashMap<>();
//        UPNEED.put(102,UPTO2);
//        UPNEED.put(103,UPTO3);
//        UPNEED.put(104,UPTO4);
//        UPNEED.put(106,UPTO6);
//        UPNEED.put(107,UPTO7);
//        UPNEED.put(108,UPTO8);
//        UPNEED.put(110,UPTO10);
//        UPNEED.put(111,UPTO11);
//        UPNEED.put(112,UPTO12);
//        UPNEED.put(113,UPTO13);
//    }

}
