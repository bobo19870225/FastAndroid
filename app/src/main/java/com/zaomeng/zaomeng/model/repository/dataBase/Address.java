package com.zaomeng.zaomeng.model.repository.dataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2019-05-01.
 * FastAndroid
 */
@Entity(tableName = "address")
public class Address {
    @PrimaryKey
    @NonNull
    public String ID = "";
    public String NAME;
    public String PID;
    public Integer TYPE;
    public Integer ISCHILD;
}
