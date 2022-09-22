package com.example.appvivaleite.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomCalfDAO;
import com.example.appvivaleite.database.dao.RoomWeaningDAO;
import com.example.appvivaleite.model.Calf;
import com.example.appvivaleite.model.Weaning;
import com.example.appvivaleite.ui.adapter.ListCalfAdapter;
import com.example.appvivaleite.ui.adapter.ListCalfItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListCalf;

import java.util.List;

public class ListCalfActivity extends AppCompatActivity {

    private RoomCalfDAO dao;
    private RoomWeaningDAO daoWeaning;
    private ListCalfAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calf);
        dao = VivaLeiteDatabase.getInstance(this).getRoomCalfDAO();
        daoWeaning = VivaLeiteDatabase.getInstance(this).getRoomWeaningDAO();
        getSupportActionBar().setTitle("Bezerros");
        configRecyclerListCalf(dao.allCalf());

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
                Intent icc = new Intent(ListCalfActivity.this, CalfActivity.class);
                startActivity(icc);
                finish();
            case R.id.menu_new_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void configRecyclerListCalf(List<Calf> calfs) {
        RecyclerView listCalf = findViewById(R.id.recycler_list_calf);
        configurationAdapter(calfs, listCalf);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListCalfItemTouchHelperCallback(adapter, dao, calfs, ListCalfActivity.this));
        itemTouchHelper.attachToRecyclerView(listCalf);
    }

    private void configurationAdapter(List<Calf> calfs, RecyclerView listCalf) {
        adapter = new ListCalfAdapter(this, calfs);
        listCalf.setAdapter(adapter);
        adapter.setOnItemClickListener(new onItemClickListCalf() {
            @Override
            public void onItemClickListCalf(Calf calf, int position) {
                new AlertDialog.Builder(ListCalfActivity.this)
                        .setTitle("Salvar Desmame")
                        .setMessage("Tem certeza que deseja salvar bezerro desmame?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                daoWeaning.save(new Weaning(calf.getBrincoCowCalf(), calf.getGenderCalf()));
                                dao.delete(calf);
                                adapter.removeCalf(position);
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
    }
}

