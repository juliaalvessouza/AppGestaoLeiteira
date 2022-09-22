package com.example.appvivaleite.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.database.dao.RoomProductionMilkDAO;
import com.example.appvivaleite.model.ProductionMilk;

import java.util.List;

public class ListProductionMilkItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListProductionMilkAdapter adapter;
    private final RoomProductionMilkDAO dao;
    private List<ProductionMilk> productionMilks;
    private Context context;

    public ListProductionMilkItemTouchHelperCallback(ListProductionMilkAdapter adapter, RoomProductionMilkDAO dao, List<ProductionMilk> productionMilks, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.productionMilks = productionMilks;
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
                .setTitle("Deletar Produção de Leite")
                .setMessage("Tem certeza que deseja deletar produção de leite?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(productionMilks.get(positionCowMilking));
                        adapter.removeProdMilk(positionCowMilking);
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
