package com.iucproject.mobilenews.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iucproject.mobilenews.roomDB.Datas;

import java.util.List;

@Dao
public interface DatasDao {

    @Insert
    void insert(Datas data);

    @Update
    void update(Datas data);

    @Delete
    void delete(Datas data);

    @Query("DELETE FROM 'Values'")
    void deleteAll();
    @Query("SELECT * FROM 'Values' Order By did DESC")
    LiveData<List<Datas>> getDatas();
}
