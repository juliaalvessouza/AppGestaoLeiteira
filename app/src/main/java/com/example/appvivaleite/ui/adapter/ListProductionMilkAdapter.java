package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.ProductionMilk;

import java.util.List;

public class ListProductionMilkAdapter extends RecyclerView.Adapter<ListProductionMilkAdapter.ListProductionMilkViewHolder> {

    private final List<ProductionMilk> productionMilks;
    private final Context context;
    private onItemClickListProductionMilk onItemClickListProductionMilk;

    public ListProductionMilkAdapter( Context context, List<ProductionMilk> productionMilks) {
        this.context = context;
        this.productionMilks = productionMilks;
    }

    public void setOnItemClickListProductionMilk(onItemClickListProductionMilk onItemClickListProductionMilk) {
        this.onItemClickListProductionMilk = onItemClickListProductionMilk;
    }

    @NonNull
    @Override
    public ListProductionMilkAdapter.ListProductionMilkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_production_milk, parent, false);
        return new ListProductionMilkAdapter.ListProductionMilkViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductionMilkAdapter.ListProductionMilkViewHolder holder, int position) {
        ProductionMilk productionMilk = productionMilks.get(position);
        holder.vincula(productionMilk);
    }

    @Override
    public int getItemCount() {
        return productionMilks.size();
    }

    class ListProductionMilkViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameProductionMilk;
        private final TextView tvPeriodProdMilk;
        private final TextView tvQtadeMilk;
        private final TextView tvData;
        private ProductionMilk productionMilk;

        public ListProductionMilkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProductionMilk = itemView.findViewById(R.id.tv_name_production_milk);
            tvPeriodProdMilk = itemView.findViewById(R.id.tv_period);
            tvQtadeMilk = itemView.findViewById(R.id.tv_qtade_milk);
            tvData = itemView.findViewById(R.id.tv_data_prod);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListProductionMilk.onItemClickListProductionMilk(productionMilk, getAdapterPosition());
                }
            });

        }
        public void vincula(ProductionMilk productionMilk){
            this.productionMilk = productionMilk;
            preencheCampos(productionMilk);
        }

        private void preencheCampos(ProductionMilk productionMilk) {
            tvNameProductionMilk.setText(productionMilk.getBrincoCow());
            tvPeriodProdMilk.setText(productionMilk.getPeriod());
            tvQtadeMilk.setText(productionMilk.getQtadeProductionMilk());
            tvData.setText(productionMilk.getMomentInsert());

        }
    }

    public void removeProdMilk(int position) {
        productionMilks.remove(position);
        notifyItemRemoved(position);
    }
}
