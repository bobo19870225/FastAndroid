package com.jinkan.www.fastandroid.model.http;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by Sampson on 2018/4/17.
 * FastAndroid
 */
class NetWorkUtils {
    public static boolean isNetConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {//mNetworkInfo.isAvailable();
                return true;//有网
            }
        }
        return false;//没有网
    }

    public static void OpenNetworkSetting(final Context context) {
        // 检查有没有网络
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        //模拟器是用电脑上网，有可能设置飞行模式activeNetworkInfo！=null
        //在真机测试。真机也要上网
        //用一台笔记本电脑，做wifi连接
        if (activeNetworkInfo == null) {
            // 没网，显示一个dialog,
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("亲，现在你没网");
            // 打开
            dialog.setPositiveButton("打开", new DialogInterface.OnClickListener() {

                @Override


                public void onClick(DialogInterface dialog, int which) {
                    try {
                        //不同的android版本网络设置界面activity中的intent-filetr,action是不一样的
                        //不同的android版本的代码是不一样的。
                        int androidVersion = android.os.Build.VERSION.SDK_INT;
                        //通过代码得到手机厂商名称，
                        //不同厂商的手机的代码是不一样的。
                        //有的手机能得到手机号，大部分手机能得到sim卡中的串号，串号每个手机是唯一的。
                        if (androidVersion >= 10) {
                            // 打开系统自带的网络设置界面
                            Intent intent = new Intent(
                                    android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            context.startActivity(intent);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
            // 取消
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override


                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }

            });
            dialog.show();
        }

    }
}
