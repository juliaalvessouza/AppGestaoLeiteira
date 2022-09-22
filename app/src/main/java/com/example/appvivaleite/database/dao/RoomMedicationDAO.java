package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.Medication;

import java.util.List;

@Dao
public interface RoomMedicationDAO {

    @Insert
    void save(Medication newMedication);

    @Delete
    void delete(Medication medication);

    @Query("SELECT * FROM Medication")
    List<Medication> allMedication();

    @Query("SELECT * FROM Medication m  WHERE m.brincoCowMedication = :s")
    List<Medication> allCowMedication(String s);
}
