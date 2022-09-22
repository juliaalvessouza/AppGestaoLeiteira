package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.Reminder;

import java.util.List;

@Dao
public interface RoomReminderDAO {

    @Insert
    void save(Reminder newReminder);

    @Query("SELECT * FROM Reminder")
    List<Reminder> allReminder();

    @Delete
    void delete(Reminder reminder);

}
