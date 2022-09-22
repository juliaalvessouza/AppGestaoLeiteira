package com.example.appvivaleite.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cow implements Serializable {

    @NonNull
    @PrimaryKey()
    private String brincoCow;
    private String nomeCow;
    private String dataNascCow;
    private String lactacaoCow;

    public Cow(){}

    public Cow(String nomeCow, String dataNascCow, String brincoCow, String lactacaoCow) {
        this.nomeCow = nomeCow;
        this.dataNascCow = dataNascCow;
        this.brincoCow = brincoCow;
        this.lactacaoCow = lactacaoCow;
    }

    public String getBrincoCow() {
        return brincoCow;
    }

    public void setBrincoCow(String brincoCow) {
        this.brincoCow = brincoCow;
    }

    public String getNomeCow() { return nomeCow;}

    public void setNomeCow(String nomeCow) {
        this.nomeCow = nomeCow;
    }

    public String getDataNascCow() {
        return dataNascCow;
    }

    public void setDataNascCow(String dataNascCow) {
        this.dataNascCow = dataNascCow;
    }

    public String getLactacaoCow() {
        return lactacaoCow;
    }

    public void setLactacaoCow(String lactacaoCow) {
        this.lactacaoCow = lactacaoCow;
    }

    @Override
    public String toString() {
        return brincoCow + " " + nomeCow;
    }

}
