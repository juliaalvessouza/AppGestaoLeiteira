package com.example.appvivaleite.ui;


import static com.example.appvivaleite.ui.MilkingActivityConstantes.KEY_COW_MILKING;
import static com.example.appvivaleite.ui.MilkingActivityConstantes.REQUEST_CODE_PRODUCTION;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.ui.adapter.MilkingAdapter;
import com.example.appvivaleite.ui.adapter.MilkingItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MilkingActivity extends AppCompatActivity {

    private AutoCompleteTextView autoBrinco;
    ArrayAdapter<Cow> arrayAdapterBrinco;
    Button btnStartMilking;
    private MilkingAdapter adapter;
    private RoomCowDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milking);
        getSupportActionBar().setTitle("Controle de Ordenhas");
        dao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        startInputs();
        listBrinco();
        recyclerCowMilking(new ArrayList<Cow>());
        startListCowMilking();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_cancel:
                Intent ic = new Intent(MilkingActivity.this, MainRegisterActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startListCowMilking() {
        btnStartMilking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = autoBrinco.getText().toString();
                adapter.addMilking(new Cow(s, "", "", ""));
            }
        });
    }
    private void recyclerCowMilking(List<Cow> cowMilkings) {
        RecyclerView listMilking = findViewById(R.id.recycler_cow_milking);
        configurationAdapter(cowMilkings, listMilking);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MilkingItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(listMilking);
    }

    private void configurationAdapter(List<Cow> cowMilkings, RecyclerView listMilking) {
        adapter = new MilkingAdapter(this, cowMilkings);
        listMilking.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Cow milking, int position) {
                Intent openProductionMilk = new Intent(MilkingActivity.this, ProductionMilkingActivity.class);
                openProductionMilk.putExtra(KEY_COW_MILKING, milking);
                startActivityForResult(openProductionMilk, REQUEST_CODE_PRODUCTION);
                adapter.removeMilking(position);
            }
        });
    }

    private void listBrinco() {
        arrayAdapterBrinco = new ArrayAdapter<>(getApplicationContext(),
                R.layout.menu_suspenso,
                dao.allCowLactacao("Sim"));
        autoBrinco.setAdapter(arrayAdapterBrinco);
        autoBrinco.setThreshold(1);
    }

    private void startInputs() {
        autoBrinco = findViewById(R.id.autoCompleteBrinco_cow_milking);
        btnStartMilking = findViewById(R.id.btn_start_milking);
    }
}
