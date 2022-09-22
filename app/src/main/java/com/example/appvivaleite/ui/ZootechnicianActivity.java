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
import com.example.appvivaleite.database.dao.RoomTesteZootechnician;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.TesteZootechnician;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class ZootechnicianActivity extends AppCompatActivity {

    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private Button inputDateAnimalCMT;
    private RadioGroup radioGroupTesteAnteriorDir, radioGroupTesteAnteriorEsq, radioGroupTestePosteriorDir, radioGroupTestePosteriorEsq;
    private RadioButton radioButtonTesteAnteriorDir, radioButtonTesteAnteriorEsq, radioButtonTestePosteriorDir, radioButtonTestePosteriorEsq;
    private RoomTesteZootechnician dao;
    private RoomCowDAO roomCowDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zootechnician);
        getSupportActionBar().setTitle("Cadastro Zootécnico");
        dao = VivaLeiteDatabase.getInstance(this).getRoomTesteZootechnicianDAO();
        roomCowDao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
       listBrinco();
        configInputDateAnimalZootechnician();
    }

    private void configInputDateAnimalZootechnician() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Data Teste Zootécnico");
        MaterialDatePicker materialDatePicker = buider.build();


        inputDateAnimalCMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                inputDateAnimalCMT.setText("Data Teste:" + " " + materialDatePicker.getHeaderText());
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
                    TesteZootechnician newTesteZootechnician = createTestZootecnician();
                    dao.save(newTesteZootechnician);
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e) {
                    // preciso colocar alguma coisa para preencher os campos sendo possível salvar...e manter na página atual.
                }
            case R.id.menu_item_cancel:
                Intent ic = new Intent(ZootechnicianActivity.this, ListZootechnicianActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private TesteZootechnician createTestZootecnician() {
        int radioIdAD = radioGroupTesteAnteriorDir.getCheckedRadioButtonId();
        radioButtonTesteAnteriorDir = findViewById(radioIdAD);
        int radioIdAE = radioGroupTesteAnteriorEsq.getCheckedRadioButtonId();
        radioButtonTesteAnteriorEsq = findViewById(radioIdAE);
        int radioIdPD = radioGroupTestePosteriorDir.getCheckedRadioButtonId();
        radioButtonTestePosteriorDir = findViewById(radioIdPD);
        int radioIdPE = radioGroupTestePosteriorEsq.getCheckedRadioButtonId();
        radioButtonTestePosteriorEsq = findViewById(radioIdPE);
        String dataAnimalCMT = inputDateAnimalCMT.getText().toString();
        String brincoAnimalCMT = autoBrinco.getText().toString();
        String testeAD = radioButtonTesteAnteriorDir.getText().toString();
        String testeAE = radioButtonTesteAnteriorEsq.getText().toString();
        String testePD = radioButtonTestePosteriorDir.getText().toString();
        String testePE = radioButtonTestePosteriorEsq.getText().toString();

        TesteZootechnician newTeste = new TesteZootechnician(dataAnimalCMT, brincoAnimalCMT, testeAD, testeAE, testePD, testePE);
        return newTeste;
    }

    private void startInputs() {
        inputDateAnimalCMT = findViewById(R.id.input_data_cmt);
        autoBrinco = findViewById(R.id.autoCompleteBrinco_cow_cmt);
        radioGroupTestePosteriorDir = findViewById(R.id.rGTestePosteriorDir);
        radioGroupTestePosteriorEsq = findViewById(R.id.rGTestePosteriorEsq);
        radioGroupTesteAnteriorDir = findViewById(R.id.rGTesteAnteriorDir);
        radioGroupTesteAnteriorEsq = findViewById(R.id.rGTesteAnteriorEsq);
    }
}
