package com.example.appvivaleite.ui;

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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.database.dao.RoomMedicationDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.Medication;
import com.example.appvivaleite.ui.adapter.ListMedicationAdapter;
import com.example.appvivaleite.ui.adapter.ListMedicationItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListMedication;

import java.util.List;

public class ListMedicationActivity extends AppCompatActivity {

    private RoomMedicationDAO dao;
    private RoomCowDAO daoC;
    private ListMedicationAdapter adapter;
    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private Button btnListMedication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medication);
        dao = VivaLeiteDatabase.getInstance(this).getRoomMedicationDAO();
        daoC = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        getSupportActionBar().setTitle("Medicações");
        startInput();
        listBrinco();
        btnListCowMedication();
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
                Intent icc = new Intent(ListMedicationActivity.this, MedicationActivity.class);
                startActivity(icc);
                finish();
            case R.id.menu_new_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void listBrinco() {
        arrayAdapterBrinco = new ArrayAdapter<>(getApplicationContext(),
                R.layout.menu_suspenso,
                daoC.allCow());
        autoBrinco.setAdapter(arrayAdapterBrinco);
        autoBrinco.setThreshold(1);
    }

    private void btnListCowMedication() {
        btnListMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = autoBrinco.getText().toString();
                configRecyclerListMedication(dao.allCowMedication(s)) ;
            }
        });
    }


    private void configRecyclerListMedication(List<Medication> medications) {
        RecyclerView listMedication = findViewById(R.id.recycler_list_medication);
        configurationAdapter(medications, listMedication);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListMedicationItemTouchHelperCallback(adapter, dao, medications, ListMedicationActivity.this));
        itemTouchHelper.attachToRecyclerView(listMedication);
    }

    private void configurationAdapter(List<Medication> medications, RecyclerView listMedication) {
        adapter = new ListMedicationAdapter(this, medications);
        listMedication.setAdapter(adapter);
        adapter.setOnItemClickListMedication(new onItemClickListMedication() {
            @Override
            public void onItemClickListMedication(Medication medication, int position) {
                Toast.makeText(getApplicationContext(), "Para excluir arraste para o lado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startInput(){
        btnListMedication = findViewById(R.id.btn_start_list_medication);
        autoBrinco = findViewById(R.id.autoCompleteBrinco_cow_list_medication);
    }
}
