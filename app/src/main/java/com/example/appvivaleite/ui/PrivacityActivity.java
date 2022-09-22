package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;

public class PrivacityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacity);
        getSupportActionBar().setTitle("Políticas de Privacidade");
        configBtnConcordo();
    }

    private void configBtnConcordo() {
        Button btnLogar = findViewById(R.id.btn_condordo);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(PrivacityActivity.this, MainRegisterActivity.class));
                startActivity(i);
                finish();
            }
        });
    }
}
