package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.model.Cow;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

public class CowActivity extends AppCompatActivity {

    private TextInputLayout inputNameCow;
    private Button inputDateNascCow;
    private TextInputLayout inputNumberEarring;
    private RadioGroup radioGroupCow;
    private RadioButton radioButtonCow;
    private RoomCowDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);
        getSupportActionBar().setTitle("Cadastro de Vaca");
        dao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
        configInputDateCow();
    }

    private void configInputDateCow() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Nascimento Vaca");
        MaterialDatePicker materialDatePicker = buider.build();


        inputDateNascCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                inputDateNascCow.setText("Nascimento:" + " " + materialDatePicker.getHeaderText());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                try {
                    Cow newCow = createCow();
                    dao.save(newCow);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e) {
                    // preciso colocar alguma coisa para preencher os campos sendo possível salvar...e manter na página atual.
                }
            case R.id.menu_item_cancel:
                Intent ic = new Intent(CowActivity.this, ListCowActivity.class);
                startActivity(ic);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Cow createCow() {
        int radioId = radioGroupCow.getCheckedRadioButtonId();
        radioButtonCow = findViewById(radioId);
        String nomeCow = inputNameCow.getEditText().getText().toString();
        String nascCow = inputDateNascCow.getText().toString();
        String brincoCow = inputNumberEarring.getEditText().getText().toString();
        String lactacaoCow = radioButtonCow.getText().toString();

        Cow newCow = new Cow(nomeCow, nascCow,brincoCow, lactacaoCow);
        return newCow;
    }

    private void startInputs() {
        inputNameCow = findViewById(R.id.input_name_cow);
        inputDateNascCow = findViewById(R.id.btn_date_cow);
        inputNumberEarring = findViewById(R.id.input_brinco_cow);
        radioGroupCow = findViewById(R.id.rGlactacaoCow);
    }
}
