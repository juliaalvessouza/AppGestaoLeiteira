package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Medication implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String brincoCowMedication;
    private String carenciaDias;
    private String medication;
    private String dateMedication;

    public Medication(String brincoCowMedication, String carenciaDias, String medication, String dateMedication) {
        this.brincoCowMedication = brincoCowMedication;
        this.carenciaDias = carenciaDias;
        this.medication = medication;
        this.dateMedication = dateMedication;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrincoCowMedication() {
        return brincoCowMedication;
    }

    public void setBrincoCowMedication(String brincoCowMedication) {
        this.brincoCowMedication = brincoCowMedication;    }

    public String getCarenciaDias() {
        return carenciaDias;
    }

    public void setCarenciaDias(String carenciaDias) {
        this.carenciaDias = carenciaDias;
    }


    public String getDateMedication() {
        return dateMedication;
    }

    public void setDateMedication(String dateMedication) {
        this.dateMedication = dateMedication;
    }

    @Override
    public String toString() {
        return brincoCowMedication;
    }
}
