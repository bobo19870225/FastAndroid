package com.zaomeng.zaomeng.model.repository.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by Sampson on 2019-05-01.
 * FastAndroid
 */
@Dao
public interface SearchDao {
    @Query("SELECT * FROM search WHERE memberID = :memberID")
    LiveData<List<HistorySearchKey>> getSearchKeyByMemberID(String memberID);

    //不能插入
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int upDateUser(HistorySearchKey historySearchKey);

    //返回值要是void
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDate(HistorySearchKey historySearchKey);

    @Query("DELETE FROM search")
    void clean();
}
