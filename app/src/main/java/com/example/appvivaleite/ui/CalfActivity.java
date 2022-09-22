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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCalfDAO;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.model.Calf;
import com.example.appvivaleite.model.Cow;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class CalfActivity extends AppCompatActivity {

    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private Button dateCalf;
    private RadioGroup radioGroupCalf;
    private RadioButton radioButtonCalf;
    private RoomCalfDAO dao;
    private RoomCowDAO roomCowDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calf);
        getSupportActionBar().setTitle("Cadastro de Bezerro");
        dao = VivaLeiteDatabase.getInstance(this).getRoomCalfDAO();
        roomCowDao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
        listBrinco();
        configInputDate();
    }

    private void startInputs(){
        autoBrinco = findViewById(R.id.autoCompleteBrinco_cow_calf);
        dateCalf = findViewById(R.id.btn_date_calf);
        radioGroupCalf = findViewById(R.id.rGcalf);
    }

    private void configInputDate() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Nascimento Bezerro");
        MaterialDatePicker materialDatePicker = buider.build();
        dateCalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateCalf.setText("Nascimento:" + " " + materialDatePicker.getHeaderText());
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
                Calf newCalf = createCalf();
                dao.save(newCalf);
                finish();

            case R.id.menu_item_cancel:
                Intent ic = new Intent(CalfActivity.this, ListCalfActivity.class);
                startActivity(ic);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @NonNull
    private Calf createCalf() {
        int radioId = radioGroupCalf.getCheckedRadioButtonId();
        radioButtonCalf = findViewById(radioId);
        String brincoCowCalf = autoBrinco.getText().toString();
        String nascCalf = dateCalf.getText().toString();
        String sexoCalf = radioButtonCalf.getText().toString();

        Calf newCalf = new Calf(brincoCowCalf, nascCalf, sexoCalf);
        return newCalf;
    }
}

