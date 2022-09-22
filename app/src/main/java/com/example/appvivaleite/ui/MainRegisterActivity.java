package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;

public class MainRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        getSupportActionBar().setTitle("VivaLeite");
        configBtnMedication();
        configBtnReproduction();
        configBtnMilking();
        configBtnReminder();
        configBtnZootechnician();
        configBtnCalf();
        configBtnCow();
        configBtnListWeaning();
        configBtnProductionMilk();
    }

    private void configBtnZootechnician() {
        Button btnZootechnician = findViewById(R.id.btn_zootechnician);
        btnZootechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iZ = new Intent(MainRegisterActivity.this, ListZootechnicianActivity.class);
                startActivity(iZ);
            }
        });
    }

    private void configBtnReminder() {
        Button btnReminder = findViewById(R.id.btn_reminder);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iR = new Intent(MainRegisterActivity.this, ReminderActivity.class);
                startActivity(iR);
            }
        });
    }

    private void configBtnMilking() {
        Button btnMilking = findViewById(R.id.btn_milking);
        btnMilking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iM = new Intent(MainRegisterActivity.this, MilkingActivity.class);
                startActivity(iM);
            }
        });
    }

    private void configBtnReproduction() {
        Button btnReproduction = findViewById(R.id.btn_reproduction);
        btnReproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iRe = new Intent(MainRegisterActivity.this, ListReproductionActivity.class);
                startActivity(iRe);
            }
        });
    }

    private void configBtnMedication() {
        Button btnMedication = findViewById(R.id.btn_medication);
        btnMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iMe = new Intent(MainRegisterActivity.this, ListMedicationActivity.class);
                startActivity(iMe);
            }
        });
    }

    private void configBtnCow() {
        Button cow = findViewById(R.id.btn_cow);
        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iC = new Intent(MainRegisterActivity.this, ListCowActivity.class);
                startActivity(iC);
            }
        });
    }

    private void configBtnCalf() {
        Button calf = findViewById(R.id.btn_calf);
        calf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCalfCad = new Intent(MainRegisterActivity.this, ListCalfActivity.class);
                startActivity(iCalfCad);
            }
        });
    }

    private void configBtnListWeaning() {
        Button weaning = findViewById(R.id.btn_list_weaning);
        weaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iWe = new Intent(MainRegisterActivity.this, ListWeaningActivity.class);
                startActivity(iWe);
            }
        });
    }

    private void configBtnProductionMilk(){
        Button medicationCow = findViewById(R.id.btn_list_production_milk);
        medicationCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iProdMilk = new Intent(MainRegisterActivity.this, ListProductionMilkingActivity.class);
                startActivity(iProdMilk);
            }
        });
    }
}