package com.example.appvivaleite.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.appvivaleite.database.dao.RoomCalfDAO;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.database.dao.RoomMedicationDAO;
import com.example.appvivaleite.database.dao.RoomProductionMilkDAO;
import com.example.appvivaleite.database.dao.RoomReminderDAO;
import com.example.appvivaleite.database.dao.RoomReproductionDAO;
import com.example.appvivaleite.database.dao.RoomTesteZootechnician;
import com.example.appvivaleite.database.dao.RoomWeaningDAO;
import com.example.appvivaleite.model.Calf;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.Medication;
import com.example.appvivaleite.model.ProductionMilk;
import com.example.appvivaleite.model.Reminder;
import com.example.appvivaleite.model.Reproduction;
import com.example.appvivaleite.model.TesteZootechnician;
import com.example.appvivaleite.model.Weaning;

@Database(entities = {Calf.class, Cow.class, Medication.class, ProductionMilk.class,
        Reminder.class, Reproduction.class, TesteZootechnician.class, Weaning.class},
        version = 1, exportSchema = false)
public abstract class VivaLeiteDatabase extends RoomDatabase {

    private static final String NOME_BANCO_VIVALEITE_DB = "agenda.db";

    public abstract RoomCalfDAO getRoomCalfDAO();
    public abstract RoomCowDAO getRoomCowDAO();
    public abstract RoomMedicationDAO getRoomMedicationDAO();
    public abstract RoomReminderDAO getRoomReminderDAO();
    public abstract RoomReproductionDAO getRoomReproductionDAO();
    public abstract RoomWeaningDAO getRoomWeaningDAO();
    public abstract RoomTesteZootechnician getRoomTesteZootechnicianDAO();
    public abstract RoomProductionMilkDAO getRoomProductionMilkDAO();

    public static VivaLeiteDatabase getInstance(Context context){
        return Room.databaseBuilder(context, VivaLeiteDatabase.class, NOME_BANCO_VIVALEITE_DB)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}

