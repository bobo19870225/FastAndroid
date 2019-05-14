package com.zaomeng.zaomeng.model.repository.dataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2019-05-14.
 * FastAndroid
 */
@Entity(tableName = "search")
public class HistorySearchKey {
    @NonNull
    @PrimaryKey
    public String key = "";
    public String memberID;
}
