package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.Weaning;

import java.util.List;

@Dao
public interface RoomWeaningDAO {

    @Insert
    void save(Weaning newWeaning);

    @Query("SELECT * FROM Weaning")
    List<Weaning> allWeanings();

    @Delete
    void delete(Weaning weaning);
}
