package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Calf implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String brincoCowCalf;
    private String dataNascCalf;
    private String genderCalf;

    public Calf(String brincoCowCalf, String dataNascCalf, String genderCalf) {
        this.brincoCowCalf = brincoCowCalf;
        this.dataNascCalf = dataNascCalf;
        this.genderCalf = genderCalf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrincoCowCalf() {
        return brincoCowCalf;
    }

    public void setBrincoCowCalf(String brincoCowCalf) {
        this.brincoCowCalf = brincoCowCalf;
    }

    public String getDataNascCalf() {
        return dataNascCalf;
    }

    public void setDataNascCalf(String dataNascCalf) {
        this.dataNascCalf = dataNascCalf;
    }

    public String getGenderCalf() {
        return genderCalf;
    }

    public void setGenderCalf(String genderCalf) {
        this.genderCalf = genderCalf;
    }

    @Override
    public String toString() {
        return "Calf{" +
                "id=" + id +
                ", brincoCowCalf='" + brincoCowCalf + '\'' +
                ", dataNascCalf='" + dataNascCalf + '\'' +
                ", genderCalf='" + genderCalf + '\'' +
                '}';
    }
}
