package com.zaomeng.zaomeng.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class FormatUtils {
    public static String numberFormatMoney(double money) {
        NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.CHINA); //建立货币格式化引用
        return CURRENCY_FORMAT.format(new BigDecimal(money));
    }

    public static boolean isMobileNO(String mobileNumber) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNumber))
            return false;
        else
            return mobileNumber.matches(telRegex);
    }

}
