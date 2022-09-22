package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appvivaleite.model.Cow;

import java.util.List;

@Dao
public interface RoomCowDAO {

    @Insert
    void save(Cow newCow);

    @Query("SELECT * FROM Cow")
    List<Cow> allCow();

    @Query("SELECT * FROM Cow c WHERE c.lactacaoCow = :lactacao")
    List<Cow> allCowLactacao(String lactacao);

    @Query("SELECT * FROM Cow c  WHERE c.brincoCow LIKE :brinco")
    List<Cow> allCowBrinco(String brinco);

    @Delete
    void delete(Cow cow);

    @Update
    void edit(Cow cow);
}
