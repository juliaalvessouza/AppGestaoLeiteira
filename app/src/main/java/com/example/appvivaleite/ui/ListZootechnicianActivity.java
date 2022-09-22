package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomTesteZootechnician;
import com.example.appvivaleite.model.TesteZootechnician;
import com.example.appvivaleite.ui.adapter.ListZootechnicianAdapter;
import com.example.appvivaleite.ui.adapter.ListZootechnicianItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListZootechnician;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.List;

public class ListZootechnicianActivity extends AppCompatActivity {

    private RoomTesteZootechnician dao;
    private ListZootechnicianAdapter adapter;
    private Button listDateZootechnician;
    private Button dateZoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_zootechnician);
        dao = VivaLeiteDatabase.getInstance(this).getRoomTesteZootechnicianDAO();
        getSupportActionBar().setTitle("Exames");
        startInputs();
        configInputDate();
        btnListDateTeste();
    }

    private void btnListDateTeste() {
        listDateZootechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = dateZoo.getText().toString();
                configRecyclerListZootechnican(dao.allZootechnicianDate(s));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_add:
                Intent icc = new Intent(ListZootechnicianActivity.this, ZootechnicianActivity.class);
                startActivity(icc);
                finish();
            case R.id.menu_new_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void configRecyclerListZootechnican(List<TesteZootechnician> testeZootechnicians) {
        RecyclerView listZootechnician = findViewById(R.id.recycler_list_zootechnician);
        configurationAdapter(testeZootechnicians, listZootechnician);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListZootechnicianItemTouchHelperCallback(adapter, dao, testeZootechnicians, ListZootechnicianActivity.this));
        itemTouchHelper.attachToRecyclerView(listZootechnician);
    }

    private void configurationAdapter(List<TesteZootechnician> testeZootechnicians, RecyclerView listZootechnician) {
        adapter = new ListZootechnicianAdapter(this, testeZootechnicians);
        listZootechnician.setAdapter(adapter);
        adapter.setOnItemClickListZootechnician(new onItemClickListZootechnician() {
            @Override
            public void onItemClickListZootechnician(TesteZootechnician testeZootechnician, int position) {
                Toast.makeText(getApplicationContext(), "Arraste para o lado para excluir o teste", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configInputDate() {
        MaterialDatePicker.Builder buider = MaterialDatePicker.Builder.datePicker();
        buider.setTitleText("Data Exame Zootécnico");
        MaterialDatePicker materialDatePicker = buider.build();


        dateZoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateZoo.setText("Data Teste:" + " " + materialDatePicker.getHeaderText());
            }
        });
    }

    private void startInputs() {
        dateZoo = findViewById(R.id.btn_date_list_zootechnician);
        listDateZootechnician = findViewById(R.id.btn_start_list_prod_milk);
    }
}
