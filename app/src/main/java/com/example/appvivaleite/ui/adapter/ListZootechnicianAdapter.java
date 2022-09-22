package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.TesteZootechnician;

import java.util.List;

public class ListZootechnicianAdapter extends RecyclerView.Adapter<ListZootechnicianAdapter.ListZootechnicianViewHolder> {

    private final List<TesteZootechnician> testeZootechnicians;
    private final Context context;
    private onItemClickListZootechnician onItemClickListZootechnician;

    public ListZootechnicianAdapter( Context context, List<TesteZootechnician> testeZootechnicians) {
        this.context = context;
        this.testeZootechnicians = testeZootechnicians;
    }

    public void setOnItemClickListZootechnician(onItemClickListZootechnician onItemClickListZootechnician) {
        this.onItemClickListZootechnician = onItemClickListZootechnician;
    }

    @NonNull
    @Override
    public ListZootechnicianAdapter.ListZootechnicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_zootechnician, parent, false);
        return new ListZootechnicianAdapter.ListZootechnicianViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListZootechnicianAdapter.ListZootechnicianViewHolder holder, int position) {
        TesteZootechnician testeZootechnician = testeZootechnicians.get(position);
        holder.vincula(testeZootechnician);
    }

    @Override
    public int getItemCount() {
        return testeZootechnicians.size();
    }

    class ListZootechnicianViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameZootechnician;
        private final TextView dateZootechnician;
        private final TextView resultAD;
        private final TextView resultAE;
        private final TextView resultPD;
        private final TextView resultPE;
        private TesteZootechnician testeZootechnician;

        public ListZootechnicianViewHolder(@NonNull View itemView) {
            super(itemView);
            nameZootechnician = itemView.findViewById(R.id.tv_name_zootechnician);
            dateZootechnician = itemView.findViewById(R.id.tv_date_zootechnician);
            resultAD = itemView.findViewById(R.id.tv_ad);
            resultAE = itemView.findViewById(R.id.tv_ae);
            resultPD = itemView.findViewById(R.id.tv_pd);
            resultPE = itemView.findViewById(R.id.tv_pe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListZootechnician.onItemClickListZootechnician(testeZootechnician, getAdapterPosition());
                }
            });

        }
        public void vincula(TesteZootechnician testeZootechnician){
            this.testeZootechnician = testeZootechnician;
            preencheCampos(testeZootechnician);
        }

        private void preencheCampos(TesteZootechnician testeZootechnician) {
            nameZootechnician.setText(testeZootechnician.getBrincoCowTeste());
            dateZootechnician.setText(testeZootechnician.getDataTeste());
            resultAD.setText(testeZootechnician.getTesteAD());
            resultAE.setText(testeZootechnician.getTesteAE());
            resultPD.setText(testeZootechnician.getTestePD());
            resultPE.setText(testeZootechnician.getTestePE());
        }
    }

    public void removeTesteZootechnician(int position) {
        testeZootechnicians.remove(position);
        notifyItemRemoved(position);
    }
}
