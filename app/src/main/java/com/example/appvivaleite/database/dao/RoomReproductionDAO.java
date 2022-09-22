package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.Reproduction;

import java.util.List;

@Dao
public interface RoomReproductionDAO {

    @Insert
    void save(Reproduction newReproduction);

    @Query("SELECT * FROM Reproduction")
    List<Reproduction> allReproduction();

    @Delete
    void delete(Reproduction reproduction);

    @Query("SELECT * FROM Reproduction r  WHERE r.brincoCowReproduction = :s")
    List<Reproduction> allCowReproduction(String s);

}
