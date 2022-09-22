package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TesteZootechnician implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String dataTeste;
    private String brincoCowTeste;
    private String testeAD;
    private String testeAE;
    private String testePD;
    private String testePE;

    public TesteZootechnician(String dataTeste, String brincoCowTeste, String testeAD, String testeAE, String testePD, String testePE){
        this.dataTeste = dataTeste;
        this.brincoCowTeste = brincoCowTeste;
        this.testeAD = testeAD;
        this.testeAE = testeAE;
        this.testePD = testePD;
        this.testePE = testePE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataTeste() {
        return dataTeste;
    }

    public void setDataTeste(String dataTeste) {
        this.dataTeste = dataTeste;
    }

    public String getBrincoCowTeste() {
        return brincoCowTeste;
    }

    public void setBrincoCowTeste(String brincoCowTeste) {
        this.brincoCowTeste = brincoCowTeste;
    }

    public String getTesteAD() {
        return testeAD;
    }

    public void setTesteAD(String testeAD) {
        this.testeAD = testeAD;
    }

    public String getTesteAE() {
        return testeAE;
    }

    public void setTesteAE(String testeAE) {
        this.testeAE = testeAE;
    }

    public String getTestePD() {
        return testePD;
    }

    public void setTestePD(String testePD) {
        this.testePD = testePD;
    }

    public String getTestePE() {
        return testePE;
    }

    public void setTestePE(String testePE) {
        this.testePE = testePE;
    }

    @Override
    public String toString() {
        return "TesteZootechnician{" +
                "id=" + id +
                ", dataTeste='" + dataTeste + '\'' +
                ", brincoCowTeste='" + brincoCowTeste + '\'' +
                ", testeAD='" + testeAD + '\'' +
                ", testeAE='" + testeAE + '\'' +
                ", testePD='" + testePD + '\'' +
                ", testePE='" + testePE + '\'' +
                '}';
    }
}
