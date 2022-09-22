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
import com.example.appvivaleite.database.dao.RoomReproductionDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.model.Reproduction;
import com.example.appvivaleite.ui.adapter.ListReproductionAdapter;
import com.example.appvivaleite.ui.adapter.ListReproductionItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListReproduction;

import java.util.List;

public class ListReproductionActivity extends AppCompatActivity {

    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    private RoomReproductionDAO dao;
    private RoomCowDAO daoC;
    private ListReproductionAdapter adapter;
    private Button btnListReproduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reproduction);
        getSupportActionBar().setTitle("Listagem de Reprodução");
        dao = VivaLeiteDatabase.getInstance(this).getRoomReproductionDAO();
        daoC = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInput();
        listBrinco();
        btnListCowReproduction();
    }

    private void listBrinco() {
        arrayAdapterBrinco = new ArrayAdapter<>(getApplicationContext(),
                R.layout.menu_suspenso,
                daoC.allCow());
        autoBrinco.setAdapter(arrayAdapterBrinco);
        autoBrinco.setThreshold(1);
    }

    private void btnListCowReproduction() {
        btnListReproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = autoBrinco.getText().toString();
                configRecyclerListReproduction(dao.allCowReproduction(s)) ;
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
                Intent icc = new Intent(ListReproductionActivity.this, ReproductionActivity.class);
                startActivity(icc);
                finish();
            case R.id.menu_new_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configRecyclerListReproduction(List<Reproduction> reproductions) {
        RecyclerView listReproduction = findViewById(R.id.recycler_list_reproduction);
        configurationAdapter(reproductions, listReproduction);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListReproductionItemTouchHelperCallback(adapter, dao, reproductions, ListReproductionActivity.this));
        itemTouchHelper.attachToRecyclerView(listReproduction);
    }

    private void configurationAdapter(List<Reproduction> reproductions, RecyclerView listReproduction) {
        adapter = new ListReproductionAdapter(this, reproductions);
        listReproduction.setAdapter(adapter);
        adapter.setOnItemClickListReproduction(new onItemClickListReproduction() {
            @Override
            public void onItemClickListReproduction(Reproduction reproduction, int position) {
                Toast.makeText(getApplicationContext(), "Arraste para o lado para excluir", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startInput(){
        btnListReproduction = findViewById(R.id.btn_start_list_reproduction);
        autoBrinco = findViewById(R.id.autoCompleteBrinco_cow_list_reproduction);
    }
}

