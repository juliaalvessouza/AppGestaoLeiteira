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
import com.example.appvivaleite.database.dao.RoomReminderDAO;
import com.example.appvivaleite.model.Reminder;
import com.example.appvivaleite.ui.adapter.ListReminderItemTouchHelperCallback;
import com.example.appvivaleite.ui.adapter.ReminderAdapter;
import com.example.appvivaleite.ui.adapter.onItemClickListReminder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ReminderActivity extends AppCompatActivity {

    private TextInputLayout inputReminder;
    private Button btnReminder;
    private RoomReminderDAO dao;
    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        getSupportActionBar().setTitle("Cadastro de Lembretes");
        dao = VivaLeiteDatabase.getInstance(this).getRoomReminderDAO();
        startInputs();
        startListReminder();
        recyclerReminder(dao.allReminder());
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
                Intent ic = new Intent(ReminderActivity.this, MainRegisterActivity.class);
                startActivity(ic);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startListReminder() {
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contextReminder = inputReminder.getEditText().getText().toString();
                dao.save(new Reminder(contextReminder));
                adapter.addReminder(new Reminder(contextReminder));
            }
        });
    }
    private void recyclerReminder(List<Reminder> reminders) {
        RecyclerView listReminder = findViewById(R.id.recycler_list_reminder);
        configurationAdapter(reminders, listReminder);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListReminderItemTouchHelperCallback(adapter, dao, reminders, ReminderActivity.this));
        itemTouchHelper.attachToRecyclerView(listReminder);
    }

    private void configurationAdapter(List<Reminder> reminders, RecyclerView listReminder) {
        adapter = new ReminderAdapter(this, reminders);
        listReminder.setAdapter(adapter);
        adapter.setOnItemClickListenerReminder(new onItemClickListReminder() {
            @Override
            public void onItemClickListReminder(Reminder reminder, int position) {
                Toast.makeText(getApplicationContext(), "Para excluir o lembrete, arraste para o lado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startInputs() {
        inputReminder = findViewById(R.id.input_reminder);
        btnReminder = findViewById(R.id.btn_check_reminder);
    }
}
