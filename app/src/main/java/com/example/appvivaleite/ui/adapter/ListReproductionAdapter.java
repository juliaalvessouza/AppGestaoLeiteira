package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Reproduction;

import java.util.List;

public class ListReproductionAdapter extends RecyclerView.Adapter<ListReproductionAdapter.ListReproductionViewHolder> {

    private final List<Reproduction> reproductions;
    private final Context context;
    private onItemClickListReproduction onItemClickListReproduction;

    public ListReproductionAdapter( Context context, List<Reproduction> reproductions) {
        this.context = context;
        this.reproductions = reproductions;
    }

    public void setOnItemClickListReproduction(onItemClickListReproduction onItemClickListReproduction) {
        this.onItemClickListReproduction = onItemClickListReproduction;
    }

    @NonNull
    @Override
    public ListReproductionAdapter.ListReproductionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_reproduction, parent, false);
        return new ListReproductionAdapter.ListReproductionViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListReproductionAdapter.ListReproductionViewHolder holder, int position) {
        Reproduction reproduction = reproductions.get(position);
        holder.vincula(reproduction);
    }

    @Override
    public int getItemCount() {
        return reproductions.size();
    }

    class ListReproductionViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameReproduction;
        private final TextView tvDateReproduction;
        private final TextView tvTipoReproduction;
        private Reproduction reproduction;

        public ListReproductionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameReproduction = itemView.findViewById(R.id.tv_name_reproduction);
            tvDateReproduction = itemView.findViewById(R.id.tv_date_reproduction);
            tvTipoReproduction = itemView.findViewById(R.id.tv_tipo_reproduction);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListReproduction.onItemClickListReproduction(reproduction, getAdapterPosition());
                }
            });

        }
        public void vincula(Reproduction reproduction){
            this.reproduction = reproduction;
            preencheCampos(reproduction);
        }

        private void preencheCampos(Reproduction reproduction) {
            tvNameReproduction.setText(reproduction.getBrincoCowReproduction());
            tvDateReproduction.setText(reproduction.getDataReproduction());
            tvTipoReproduction.setText(reproduction.getTypeReproduction());
        }

    }

    public void removeReproduction(int position) {
        reproductions.remove(position);
        notifyItemRemoved(position);
    }
}
