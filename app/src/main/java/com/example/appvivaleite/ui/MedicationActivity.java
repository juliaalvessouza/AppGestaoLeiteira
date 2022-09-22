package com.example.appvivaleite.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.database.dao.RoomMedicationDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.Medication;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

public class MedicationActivity extends AppCompatActivity {

    private Button btnDateMedication;
    private TextInputLayout nameMedication;
    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private TextInputLayout diasCarenciaMedication;
    private RoomMedicationDAO dao;
    private RoomCowDAO roomCowDao;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        getSupportActionBar().setTitle("Cadastro de Medicações");
        dao = VivaLeiteDatabase.getInstance(this).getRoomMedicationDAO();
        roomCowDao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
        listBrinco();
        configInputDate();
    }

    private void listBrinco() {
        arrayAdapterBrinco = new ArrayAdapter<>(getApplicationContext(),
                R.layout.menu_suspenso,
                roomCowDao.allCow());
        autoBrinco.setAdapter(arrayAdapterBrinco);
        autoBrinco.setThreshold(1);
    }

    private void configInputDate() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Data Medicamento");
        MaterialDatePicker materialDatePicker = buider.build();


        btnDateMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                btnDateMedication.setText("Medicamento aplicado data:" + " " + materialDatePicker.getHeaderText());
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
                    Medication newMedication = createMedication();
                    dao.save(newMedication);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e) {
                    // preciso colocar alguma coisa para preencher os campos sendo possível salvar...e manter na página atual.
                }

            case R.id.menu_item_cancel:
                Intent ic = new Intent(MedicationActivity.this, ListMedicationActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Medication createMedication() {
        String brincoMedication = autoBrinco.getText().toString();
        String carenciaMedication = diasCarenciaMedication.getEditText().getText().toString();
        String medication = nameMedication.getEditText().getText().toString();
        String dateMedication = btnDateMedication.getText().toString();
        Medication newMedication = new Medication(brincoMedication, carenciaMedication, medication, dateMedication);

        return newMedication;
    }

    private void startInputs() {
        btnDateMedication = findViewById(R.id.btn_date_medication);
        autoBrinco = findViewById(R.id.autoComplete_brinco_medication);
        diasCarenciaMedication = findViewById(R.id.input_dias_carencia);
        nameMedication = findViewById(R.id.input_medication_cow);
    }
}

