package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Calf;


import java.util.List;

public class ListCalfAdapter extends RecyclerView.Adapter<ListCalfAdapter.ListCalfViewHolder> {

    private final List<Calf> calfs;
    private final Context context;
    private onItemClickListCalf onItemClickListCalf;

    public ListCalfAdapter( Context context, List<Calf> calfs) {
        this.context = context;
        this.calfs = calfs;
    }

    public void setOnItemClickListener(onItemClickListCalf onItemClickListCalf) {
        this.onItemClickListCalf = onItemClickListCalf;
    }

    @NonNull
    @Override
    public ListCalfAdapter.ListCalfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_calf, parent, false);
        return new ListCalfAdapter.ListCalfViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCalfAdapter.ListCalfViewHolder holder, int position) {
        Calf calf = calfs.get(position);
        holder.vincula(calf);
    }

    @Override
    public int getItemCount() {
        return calfs.size();
    }

    class ListCalfViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCowCalf;
        private final TextView nascCalf;
        private final TextView genderCalf;
        private Calf calf;

        public ListCalfViewHolder(@NonNull View itemView) {
            super(itemView);
            genderCalf= itemView.findViewById(R.id.tv_gender);
            nomeCowCalf = itemView.findViewById(R.id.tv_name_cow_weaning);
            nascCalf = itemView.findViewById(R.id.tv_date_calf);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListCalf.onItemClickListCalf(calf, getAdapterPosition());
                }
            });

        }
        public void vincula(Calf calf){
            this.calf = calf;
            preencheCampos(calf);
        }

        private void preencheCampos(Calf calf) {
            nomeCowCalf.setText(calf.getBrincoCowCalf());
            nascCalf.setText(calf.getDataNascCalf());
            genderCalf.setText(calf.getGenderCalf());
        }
    }

    public void removeCalf(int position) {
        calfs.remove(position);
        notifyItemRemoved(position);
    }
}
