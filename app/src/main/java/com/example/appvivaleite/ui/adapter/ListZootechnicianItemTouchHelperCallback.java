package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomTesteZootechnician;
import com.example.appvivaleite.model.TesteZootechnician;

import java.util.List;

public class ListZootechnicianItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListZootechnicianAdapter adapter;
    private final RoomTesteZootechnician dao;
    private List<TesteZootechnician> testeZootechnicians;
    private Context context;
    public ListZootechnicianItemTouchHelperCallback(ListZootechnicianAdapter adapter, RoomTesteZootechnician dao, List<TesteZootechnician> testeZootechnicians, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.testeZootechnicians = testeZootechnicians;
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
                .setTitle("Deletar Teste Zootécnico")
                .setMessage("Tem certeza que deseja deletar teste?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(testeZootechnicians.get(positionCowMilking));
                        adapter.removeTesteZootechnician(positionCowMilking);
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
