package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reproduction implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String dataReproduction;
    private String brincoCowReproduction;
    private String typeReproduction;

    public Reproduction(String dataReproduction, String brincoCowReproduction, String typeReproduction){

        this.dataReproduction = dataReproduction;
        this.brincoCowReproduction = brincoCowReproduction;
        this.typeReproduction = typeReproduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataReproduction() {
        return dataReproduction;
    }

    public void setDataReproduction(String dataReproduction) {
        this.dataReproduction = dataReproduction;
    }

    public String getBrincoCowReproduction() {
        return brincoCowReproduction;
    }

    public void setBrincoCowReproduction(String brincoCowReproduction) {
        this.brincoCowReproduction = brincoCowReproduction;
    }

    public String getTypeReproduction() {
        return typeReproduction;
    }

    public void setTypeReproduction(String typeReproduction) {
        this.typeReproduction = typeReproduction;
    }

    @Override
    public String toString() {
        return brincoCowReproduction;
    }
}
