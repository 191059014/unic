package com.hb.unic.util.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ========== 基本校验辅助类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.tool.VerifyHelper.java, v1.0
 * @date 2019年07月22日 02时07分
 */
public class VerifyHelper {

    /**
     * ########## 判断是不是手机号码 ##########
     *
     * @param mobile 手机号
     * @return true-是手机号码
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() != 11) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        boolean isMatch = m.matches();
        return isMatch;
    }

}
