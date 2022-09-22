package com.example.appvivaleite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.database.VivaLeiteDatabase;
import com.example.appvivaleite.database.dao.RoomWeaningDAO;
import com.example.appvivaleite.model.Weaning;
import com.example.appvivaleite.ui.adapter.ListWeaningItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.WeaningAdapter;
import com.example.appvivaleite.ui.adapter.onItemClickWeaning;

import java.util.List;

public class ListWeaningActivity extends AppCompatActivity {

    private RoomWeaningDAO dao;
    private WeaningAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_weaning);
        getSupportActionBar().setTitle("Listagem dos Desmame");
        dao = VivaLeiteDatabase.getInstance(this).getRoomWeaningDAO();
        configRecyclerListWeaning(dao.allWeanings());
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
                Intent ic = new Intent(ListWeaningActivity.this, MainRegisterActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void configRecyclerListWeaning(List<Weaning> weanings) {
        RecyclerView listWeanings = findViewById(R.id.recycler_list_weaning);
        configurationAdapter(weanings, listWeanings);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListWeaningItemTouchHelperCallback(adapter, dao, weanings, ListWeaningActivity.this));
        itemTouchHelper.attachToRecyclerView(listWeanings);
    }

    private void configurationAdapter(List<Weaning> weanings, RecyclerView listCows) {
        adapter = new WeaningAdapter(this, weanings);
        listCows.setAdapter(adapter);
        adapter.setOnItemClickListenerWeaning(new onItemClickWeaning() {
            @Override
            public void onItemClick(Weaning weaning, int position) {
                Toast.makeText(getApplicationContext(), "Arraste para o lado para deletar por venda ou óbito!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
