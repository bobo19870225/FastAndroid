package com.zaomeng.zaomeng.model.repository.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Sampson on 2019-05-01.
 * FastAndroid
 */
@Dao
public interface AddressDao {
    @Query("SELECT * FROM address WHERE PID=0")
    LiveData<List<Address>> getAllProvinces();

    @Query("SELECT * FROM address WHERE PID = :PID")
    LiveData<List<Address>> getArea(String PID);

}
