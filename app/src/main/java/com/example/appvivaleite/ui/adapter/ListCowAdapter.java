package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Cow;

import java.util.List;

public class ListCowAdapter extends RecyclerView.Adapter<ListCowAdapter.ListCowViewHolder> {

    private final List<Cow> cows;
    private final Context context;
    private onItemClickListCow onItemClickListCow;

    public ListCowAdapter( Context context, List<Cow> cows) {
        this.context = context;
        this.cows = cows;
    }

    public void setOnItemClickListener(onItemClickListCow onItemClickListCow) {
        this.onItemClickListCow = onItemClickListCow;
    }

    @NonNull
    @Override
    public ListCowAdapter.ListCowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_cow, parent, false);
        return new ListCowAdapter.ListCowViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCowAdapter.ListCowViewHolder holder, int position) {
        Cow cow = cows.get(position);
        holder.vincula(cow);
    }

    @Override
    public int getItemCount() {
        return cows.size();
    }

    class ListCowViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCow;
        private final TextView nascCow;
        private final TextView lactacaoCow;
        private Cow cow;

        public ListCowViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCow = itemView.findViewById(R.id.tv_name_cow);
            nascCow = itemView.findViewById(R.id.tv_date_cow);
            lactacaoCow = itemView.findViewById(R.id.tv_lactacao_cow);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListCow.onItemClickListCow(cow, getAdapterPosition());
                }
            });
        }

        public void vincula(Cow cow){
            this.cow = cow;
            preencheCampos(cow);
        }

        private void preencheCampos(Cow cow) {
            nomeCow.setText(cow.toString());
            nascCow.setText(cow.getDataNascCow());
            lactacaoCow.setText(cow.getLactacaoCow());
        }
    }

    public void removeCows(int position) {
        cows.remove(position);
        notifyItemRemoved(position);
    }
}
