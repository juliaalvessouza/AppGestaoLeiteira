package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class ProductionMilk implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String brincoCow;
    private String qtadeProductionMilk;
    private String momentInsert;
    private String period;

    public ProductionMilk(String brincoCow, String qtadeProductionMilk, String period, String momentInsert) {
        this.brincoCow = brincoCow;
        this.qtadeProductionMilk = qtadeProductionMilk;
        this.period = period;
        this.momentInsert = momentInsert;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getMomentInsert() {
        return momentInsert;
    }

    public void setMomentInsert(String momentInsert) {
        this.momentInsert = momentInsert;
    }

    public String getBrincoCow() {
        return brincoCow;
    }

    public void setBrincoCow(String brincoCow) {
        this.brincoCow = brincoCow;
    }

    public String getQtadeProductionMilk() {
        return qtadeProductionMilk;
    }

    public void setQtadeProductionMilk(String qtadeProductionMilk) {
        this.qtadeProductionMilk = qtadeProductionMilk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return brincoCow + " " + qtadeProductionMilk;
    }

}
