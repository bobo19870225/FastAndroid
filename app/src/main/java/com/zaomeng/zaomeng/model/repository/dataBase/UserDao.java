package com.zaomeng.zaomeng.model.repository.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;

import java.util.List;

/**
 * Created by Sampson on 2019-05-01.
 * FastAndroid
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<LoginBean>> getAllUser();

    @Query("SELECT * FROM user WHERE loginName = :phone")
    LiveData<List<LoginBean>> getUserByPhone(String phone);
    //不能插入
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int upDateUser(LoginBean loginBean);

    //返回值要是void
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertDateUser(LoginBean loginBean);
}
