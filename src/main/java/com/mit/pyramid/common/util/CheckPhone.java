package com.mit.pyramid.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";
        // "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (mobiles.isEmpty()) {
            return false;
        } else
            return mobiles.matches(telRegex);
    }

    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
