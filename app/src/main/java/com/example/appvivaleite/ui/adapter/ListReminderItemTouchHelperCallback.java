package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomReminderDAO;
import com.example.appvivaleite.model.Reminder;

import java.util.List;

public class ListReminderItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ReminderAdapter adapter;
    private final RoomReminderDAO dao;
    private List<Reminder> reminders;
    private Context context;

    public ListReminderItemTouchHelperCallback(ReminderAdapter adapter, RoomReminderDAO dao, List<Reminder> reminders, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.reminders = reminders;
        this.context = context;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int moveRightLeft = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, moveRightLeft);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int positionCowMilking = viewHolder.getAdapterPosition();
        new AlertDialog.Builder(context)
                .setTitle("Deletar Lembrete")
                .setMessage("Tem certeza que deseja deletar lembrete?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(reminders.get(positionCowMilking));
                        adapter.removeReminder(positionCowMilking);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }
}
