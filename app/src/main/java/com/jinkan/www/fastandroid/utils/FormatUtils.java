package com.jinkan.www.fastandroid.utils;

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

}
