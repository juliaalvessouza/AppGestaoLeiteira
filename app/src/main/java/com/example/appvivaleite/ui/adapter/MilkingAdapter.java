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

public class MilkingAdapter extends RecyclerView.Adapter<MilkingAdapter.MilkingViewHolder> {

    private final List<Cow> cowMilkings;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public MilkingAdapter(Context context, List<Cow> cowMilkings) {
        this.cowMilkings = cowMilkings;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MilkingAdapter.MilkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_milking, parent, false);
        return new MilkingViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull MilkingAdapter.MilkingViewHolder holder, int position) {
        Cow milking = cowMilkings.get(position);
        holder.vincula(milking);

    }

    @Override
    public int getItemCount() {
        return cowMilkings.size();
    }


    class MilkingViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCow;
        private Cow milking;

        public MilkingViewHolder(@NonNull View itemView) {
            super(itemView);
        nomeCow = itemView.findViewById(R.id.tv_name_cow);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(milking, getAdapterPosition());
            }
        });
        }

        public void vincula(Cow milking){
            this.milking = milking;
            preencheCampos(milking);
        }

        private void preencheCampos(Cow milking) {
            nomeCow.setText(milking.getNomeCow());
        }
    }

    public void addMilking(Cow milking){
        cowMilkings.add(milking);
        notifyDataSetChanged();
    }

    public void removeMilking(int position) {
        cowMilkings.remove(position);
        notifyItemRemoved(position);
    }
}