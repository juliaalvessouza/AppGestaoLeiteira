package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.Calf;

import java.util.List;

@Dao
public interface RoomCalfDAO {

    @Insert
    void save(Calf newCalf);

    @Query("SELECT * FROM Calf")
    List<Calf> allCalf();

    @Delete
    void delete(Calf calf);

}
