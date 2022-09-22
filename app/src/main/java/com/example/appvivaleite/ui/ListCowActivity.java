package com.example.appvivaleite.ui;

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
import com.example.appvivaleite.database.dao.RoomCowDAO;
import com.example.appvivaleite.model.Cow;
import com.example.appvivaleite.ui.adapter.ListCowAdapter;
import com.example.appvivaleite.ui.adapter.ListCowItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.onItemClickListCow;

import java.util.List;

public class ListCowActivity extends AppCompatActivity {

    private RoomCowDAO dao;
    private ListCowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cow);
        getSupportActionBar().setTitle("Listagem das Vacas");
        dao = VivaLeiteDatabase.getInstance(this).getRoomCowDAO();
        configRecyclerListCow(dao.allCow());
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
                Intent icc = new Intent(ListCowActivity.this, CowActivity.class);
                startActivity(icc);
                finish();
            case R.id.menu_new_cancel:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void configRecyclerListCow(List<Cow> cows) {
        RecyclerView listCow = findViewById(R.id.recycler_list_cow);
        configurationAdapter(cows, listCow);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListCowItemTouchHelperCallback(adapter, dao, cows, ListCowActivity.this));
        itemTouchHelper.attachToRecyclerView(listCow);
    }

    private void configurationAdapter(List<Cow> cows, RecyclerView listCows) {
        adapter = new ListCowAdapter(this, cows);
        listCows.setAdapter(adapter);
        adapter.setOnItemClickListener(new onItemClickListCow() {
            @Override
            public void onItemClickListCow(Cow cow, int position) {
                Intent iEditCow = new Intent(ListCowActivity.this, EditCowActivity.class);
                iEditCow.putExtra("EditVaca", cow);
                startActivityForResult(iEditCow, 2);
            }
        });
    }
}
