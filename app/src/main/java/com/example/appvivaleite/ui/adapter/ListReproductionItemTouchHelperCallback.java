package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomReproductionDAO;
import com.example.appvivaleite.model.Reproduction;

import java.util.List;

public class ListReproductionItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ListReproductionAdapter adapter;
    private final RoomReproductionDAO dao;
    private List<Reproduction> reproductions;
    private Context context;

    public ListReproductionItemTouchHelperCallback(ListReproductionAdapter adapter, RoomReproductionDAO dao, List<Reproduction> reproductions, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.reproductions = reproductions;
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
                .setTitle("Deletar reprodução")
                .setMessage("Tem certeza que deseja deletar reprodução?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(reproductions.get(positionCowMilking));
                        adapter.removeReproduction(positionCowMilking);
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
