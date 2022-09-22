package com.example.appvivaleite.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reminder implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String contextReminder;

    public Reminder(String contextReminder){
        this.contextReminder = contextReminder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContextReminder() {
        return contextReminder;
    }

    public void setContextReminder(String contextReminder) {
        this.contextReminder = contextReminder;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", contextReminder='" + contextReminder + '\'' +
                '}';
    }
}

