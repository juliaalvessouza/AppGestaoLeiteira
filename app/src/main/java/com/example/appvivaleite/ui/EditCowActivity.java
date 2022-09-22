package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.model.Cow;

public class EditCowActivity extends AppCompatActivity {

    private TextView inputNameCow;
    private TextView inputDateNascCow;
    private TextView inputNumberEarring;
    private RadioGroup radioGroupCow;
    private RadioButton radioButtonCow;
    private RoomCowDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cow);
        startInputs();
        dao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        Intent dataCowMilking = getIntent();
        if(dataCowMilking.hasExtra("EditVaca")){
            Cow cowMilking = (Cow) dataCowMilking.getSerializableExtra("EditVaca");
            inputNameCow.setText(cowMilking.getNomeCow());
            inputDateNascCow.setText(cowMilking.getDataNascCow());
            inputNumberEarring.setText(cowMilking.getBrincoCow());
        }
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
                Cow newCow = createCow();
                dao.edit(newCow);
                Toast.makeText(getApplicationContext(), "Edição com sucesso", Toast.LENGTH_SHORT).show();
                finish();

            case R.id.menu_item_cancel:
                Intent ic = new Intent(EditCowActivity.this, ListCowActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @NonNull
    private Cow createCow() {
        int radioId = radioGroupCow.getCheckedRadioButtonId();
        radioButtonCow = findViewById(radioId);
        String nomeCow = inputNameCow.getText().toString();
        String nascCow = inputDateNascCow.getText().toString();
        String brincoCow = inputNumberEarring.getText().toString();
        String lactacaoCow = radioButtonCow.getText().toString();

        Cow newCow = new Cow(nomeCow, nascCow,brincoCow, lactacaoCow);
        return newCow;
    }

    private void startInputs() {
        inputNameCow = findViewById(R.id.input_name_cow_edit);
        inputDateNascCow = findViewById(R.id.input_data_edit_cow);
        inputNumberEarring = findViewById(R.id.input_brinco_cow_edit);
        radioGroupCow = findViewById(R.id.rGlactacaoCow_edit);
        int radioId = radioGroupCow.getCheckedRadioButtonId();
        radioButtonCow = findViewById(radioId);
    }
}