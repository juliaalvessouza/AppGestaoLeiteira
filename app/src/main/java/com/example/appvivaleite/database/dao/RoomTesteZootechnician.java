package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.TesteZootechnician;

import java.util.List;

@Dao
public interface RoomTesteZootechnician {

    @Insert
    void save(TesteZootechnician newTesteZootechnician);

    @Query("SELECT * FROM TesteZootechnician t  WHERE t.dataTeste = :data")
    List<TesteZootechnician> allZootechnicianDate(String data);

    @Delete
    void delete(TesteZootechnician testeZootechnician);
}
