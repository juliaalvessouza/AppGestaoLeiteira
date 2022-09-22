package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Weaning;

import java.util.List;

public class WeaningAdapter extends RecyclerView.Adapter<WeaningAdapter.WeaningViewHolder> {

    private final List<Weaning> weanings;
    private final Context context;
    private onItemClickWeaning onItemClickWeaning;

    public WeaningAdapter( Context context, List<Weaning> weanings) {
        this.context = context;
        this.weanings = weanings;
    }

    public void setOnItemClickListenerWeaning(onItemClickWeaning onItemClickWeaning) {
        this.onItemClickWeaning = onItemClickWeaning;
    }

    @NonNull
    @Override
    public WeaningAdapter.WeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_weaning, parent, false);
        return new WeaningAdapter.WeaningViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull WeaningAdapter.WeaningViewHolder holder, int position) {
        Weaning weaning = weanings.get(position);
        holder.vincula(weaning);
    }

    @Override
    public int getItemCount() {
        return weanings.size();
    }

    class WeaningViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCowWeaning;
        private final TextView genderWeaning;
        private Weaning weaning;

        public WeaningViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCowWeaning = itemView.findViewById(R.id.tv_name_cow_weaning);
            genderWeaning = itemView.findViewById(R.id.tv_gender_weaning);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickWeaning.onItemClick(weaning, getAdapterPosition());
                }
            });

        }
        public void vincula(Weaning weaning){
            this.weaning = weaning;
            preencheCampos(weaning);
        }

        private void preencheCampos(Weaning weaning) {
            nomeCowWeaning.setText(weaning.getBrincoCowWeaning());
            genderWeaning.setText(weaning.getGenderWeaning());

        }
    }

    public void removeWeanings(int position) {
        weanings.remove(position);
        notifyItemRemoved(position);
    }
}
