package com.zaomeng.zaomeng.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sampson on 2019-04-26.
 * FastAndroid
 */
public class SharedPreerencesUtils {
    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context, String username, String password) {
        // 获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        // 获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        // 设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        // 提交
        editor.apply();
    }

    /**
     * 使用SharedPreferences保存sessionID
     */
    public static void saveSessionID(Context context, String sessionID) {
        // 获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("sessionID", MODE_PRIVATE);
        // 获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        // 设置参数
        editor.putString("sessionID", sessionID);
        // 提交
        editor.apply();
    }

    /**
     * 使用SharedPreferences保存sessionID
     */
    public static String getSessionID(Context context) {

        SharedPreferences sharedPre = context.getSharedPreferences("sessionID", MODE_PRIVATE);
        if (sharedPre != null)
            return sharedPre.getString("sessionID", null);
        return null;
    }
}
