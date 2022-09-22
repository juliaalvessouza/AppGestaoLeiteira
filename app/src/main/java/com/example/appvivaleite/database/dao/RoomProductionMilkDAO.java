package com.example.appvivaleite.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appvivaleite.model.ProductionMilk;

import java.util.List;

@Dao
public interface RoomProductionMilkDAO {

    @Insert
    void save(ProductionMilk newProductionMilk);

    @Query("SELECT * FROM ProductionMilk p WHERE p.momentInsert = :s ")
    List<ProductionMilk> allProductionMilk(String s);

    @Delete
    void delete(ProductionMilk productionMilk);
}
