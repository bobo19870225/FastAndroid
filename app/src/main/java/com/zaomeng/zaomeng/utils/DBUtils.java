package com.zaomeng.zaomeng.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zaomeng.zaomeng.model.repository.dataBase.Address;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sampson on 2019-05-01.
 * FastAndroid
 */
@Singleton
public class DBUtils {
    private String DBPath;

    @Inject
    public DBUtils(Context context) {
        DBPath = copyDBFileToDatabases(context);
    }

    private String copyDBFileToDatabases(Context context) {

        // 第一次运行应用程序时，加载数据库到data/data/当前包的名称/database/<db_name>

        File dir = new File("data/data/" + context.getPackageName() + "/databases");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        String DBFileName = "dz_np.db";
        File file = new File(dir, DBFileName);
        InputStream inputStream = null;
        OutputStream outputStream = null;

        //通过IO流的方式，将assets目录下的数据库文件，写入到SD卡中。
        if (!file.exists()) {
            try {
                file.createNewFile();
                inputStream = Objects.requireNonNull(context.getClass().getClassLoader()).getResourceAsStream("assets/" + DBFileName);
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return file.getPath();

    }

    /**
     * 获取全国的省份
     */
    public List<Address> getProvincesFromDB() {
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(DBPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from address WHERE PID='0'", null);
        List<Address> addressList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.ID = cursor.getString(cursor.getColumnIndex("ID"));
                address.NAME = cursor.getString(cursor.getColumnIndex("NAME"));
                address.PID = cursor.getString(cursor.getColumnIndex("PID"));
                address.TYPE = cursor.getInt(cursor.getColumnIndex("TYPE"));
                address.ISCHILD = cursor.getInt(cursor.getColumnIndex("ISCHILD"));
                addressList.add(address);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return addressList;
    }

    /**
     * 获取全国的省份
     */
    public List<Address> getProvincesFromDBByPID(String PID) {
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(DBPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from address WHERE PID=?", new String[]{PID});
        List<Address> addressList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.ID = cursor.getString(cursor.getColumnIndex("ID"));
                address.NAME = cursor.getString(cursor.getColumnIndex("NAME"));
                address.PID = cursor.getString(cursor.getColumnIndex("PID"));
                address.TYPE = cursor.getInt(cursor.getColumnIndex("TYPE"));
                address.ISCHILD = cursor.getInt(cursor.getColumnIndex("ISCHILD"));
                addressList.add(address);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return addressList;
    }

}
