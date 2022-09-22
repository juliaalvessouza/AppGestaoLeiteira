package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.database.dao.RoomReproductionDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.Reproduction;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class ReproductionActivity extends AppCompatActivity {

    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private Button btnDateAnimalReproduction;
    private RadioGroup radioGroupReproduction;
    private RadioButton radioButtonReproduction;
    private RoomReproductionDAO dao;
    private RoomCowDAO roomCowDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduction);
        getSupportActionBar().setTitle("Cadastro de Reprodução");
        dao = VivaLeiteDatabase.getInstance(this).getRoomReproductionDAO();
        roomCowDao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
        configInputDateCowReproduction();
        listBrinco();
    }

    private void configInputDateCowReproduction() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Data Reprodução");
        MaterialDatePicker materialDatePicker = buider.build();


        btnDateAnimalReproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                btnDateAnimalReproduction.setText("Reprodução:" + " " + materialDatePicker.getHeaderText());
            }
        });
    }

    private void listBrinco() {
        arrayAdapterBrinco = new ArrayAdapter<>(getApplicationContext(),
                R.layout.menu_suspenso,
                roomCowDao.allCow());
        autoBrinco.setAdapter(arrayAdapterBrinco);
        autoBrinco.setThreshold(1);
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
                    Reproduction newReproduction = createAnimalReproduction();
                    dao.save(newReproduction);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e) {
                    // preciso colocar alguma coisa para preencher os campos sendo possível salvar...e manter na página atual.
                }
            case R.id.menu_item_cancel:
                Intent ic = new Intent(ReproductionActivity.this, ListReproductionActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Reproduction createAnimalReproduction() {
        int radioId = radioGroupReproduction.getCheckedRadioButtonId();
        radioButtonReproduction = findViewById(radioId);
        String dataReproduction = btnDateAnimalReproduction.getText().toString();
        String brincoReproduction = autoBrinco.getText().toString();
        String typeReproduction = radioButtonReproduction.getText().toString();

        Reproduction newReproduction = new Reproduction(dataReproduction, brincoReproduction, typeReproduction);
        return newReproduction;
    }

    private void startInputs() {
        btnDateAnimalReproduction = findViewById(R.id.btn_date_cow_reproduction);
        autoBrinco = findViewById(R.id.input_brinco_cow_reproduction);
        radioGroupReproduction = findViewById(R.id.rGReproduction);
    }
}
