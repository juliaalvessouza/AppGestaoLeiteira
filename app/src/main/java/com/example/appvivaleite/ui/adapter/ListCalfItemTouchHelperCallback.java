package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomCalfDAO;
import com.example.appvivaleite.model.Calf;

import java.util.List;

public class ListCalfItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListCalfAdapter adapter;
    private final RoomCalfDAO dao;
    private List<Calf> calfs;
    private Context context;


    public ListCalfItemTouchHelperCallback(ListCalfAdapter adapter, RoomCalfDAO dao, List<Calf> calfs, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.calfs = calfs;
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
                .setTitle("Deletar bezerro")
                .setMessage("Tem certeza que deseja deletar bezerro por óbito ou venda?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(calfs.get(positionCowMilking));
                        adapter.removeCalf(positionCowMilking);
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
