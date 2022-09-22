package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Weaning implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String brincoCowWeaning;
    private final String genderWeaning;

    public Weaning(String brincoCowWeaning, String genderWeaning){
        this.brincoCowWeaning = brincoCowWeaning;
        this.genderWeaning = genderWeaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrincoCowWeaning() {
        return brincoCowWeaning;
    }

    public String getGenderWeaning() {
        return genderWeaning;
    }

    @Override
    public String toString() {
        return brincoCowWeaning + " " + genderWeaning;
    }
}
