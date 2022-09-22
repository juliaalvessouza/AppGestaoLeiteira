package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Medication;

import java.util.List;

public class ListMedicationAdapter extends RecyclerView.Adapter<ListMedicationAdapter.ListMedicationViewHolder> {

    private final List<Medication> medications;
    private final Context context;
    private onItemClickListMedication onItemClickListMedication;

    public ListMedicationAdapter( Context context, List<Medication> medications) {
        this.context = context;
        this.medications = medications;
    }

    public void setOnItemClickListMedication(onItemClickListMedication onItemClickListMedication) {
        this.onItemClickListMedication = onItemClickListMedication;
    }

    @NonNull
    @Override
    public ListMedicationAdapter.ListMedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_medication, parent, false);
        return new ListMedicationAdapter.ListMedicationViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMedicationAdapter.ListMedicationViewHolder holder, int position) {
        Medication medication = medications.get(position);
        holder.vincula(medication);
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    class ListMedicationViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameCowMedication;
        private final TextView tvDateMedication;
        private final TextView tvCarenciaMedication;
        private final TextView tvMedication;
        private Medication medication;

        public ListMedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCowMedication = itemView.findViewById(R.id.tv_name_medication);
            tvDateMedication = itemView.findViewById(R.id.tv_date_medication);
            tvCarenciaMedication = itemView.findViewById(R.id.tv_carencia_dias_medication);
            tvMedication = itemView.findViewById(R.id.tv_input_medication);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListMedication.onItemClickListMedication(medication, getAdapterPosition());
                }
            });

        }
        public void vincula(Medication medication){
            this.medication = medication;
            preencheCampos(medication);
        }

        private void preencheCampos(Medication medication) {
            tvNameCowMedication.setText(medication.getBrincoCowMedication());
            tvDateMedication.setText(medication.getDateMedication());
            tvCarenciaMedication.setText(medication.getCarenciaDias());
            tvMedication.setText(medication.getMedication());
        }

    }

    public void removeMedication(int position) {
        medications.remove(position);
        notifyItemRemoved(position);
    }
}
