package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomWeaningDAO;
import com.example.appvivaleite.model.Weaning;

import java.util.List;

public class ListWeaningItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final WeaningAdapter adapter;
    private final RoomWeaningDAO dao;
    private List<Weaning> weanings;
    private Context context;

    public ListWeaningItemTouchHelperCallback(WeaningAdapter adapter, RoomWeaningDAO dao, List<Weaning> weanings, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.weanings = weanings;
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
        int positionWeaning = viewHolder.getAdapterPosition();
        new AlertDialog.Builder(context)
                .setTitle("Venda ou Óbito")
                .setMessage("Tem certeza que deseja deletar desmame por venda ou óbito?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(weanings.get(positionWeaning));
                        adapter.removeWeanings(positionWeaning);
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
