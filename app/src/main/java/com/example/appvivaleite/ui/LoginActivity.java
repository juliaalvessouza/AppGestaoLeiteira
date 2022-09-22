package com.example.appvivaleite.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appvivaleite.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configBtnLogin();
        configBtnCadastro();
        configBtnConta();
        configBtnPrivacity();
    }

    private void configBtnLogin() {
        Button btnLogar = findViewById(R.id.btn_login);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar os campos e entrar....

                Intent i = (new Intent(LoginActivity.this, MainRegisterActivity.class));
                startActivity(i);
                finish();
            }
        });
    }

    private void configBtnCadastro() {
        Button btnLogar = findViewById(R.id.btn_cadastro);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validar os campos e salvar
            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configBtnConta() {
        TextView btnConfig = findViewById(R.id.btn_config_conta);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // trazer os dados e fazer update.
                Toast.makeText(getApplicationContext(), "trocar senha.....alterar informações", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configBtnPrivacity() {
        TextView btnPrivacity = findViewById(R.id.btn_politicas);
        btnPrivacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar os campos e entrar....

                Intent i = (new Intent(LoginActivity.this, PrivacityActivity.class));
                startActivity(i);
                finish();
            }
        });
    }
}