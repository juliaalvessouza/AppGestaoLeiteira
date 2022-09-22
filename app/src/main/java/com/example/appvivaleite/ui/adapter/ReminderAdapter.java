package com.example.appvivaleite.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvivaleite.R;
import com.example.appvivaleite.model.Reminder;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private final List<Reminder> reminders;
    private final Context context;
    private onItemClickListReminder onItemClickListReminder;

    public ReminderAdapter( Context context, List<Reminder> remiders) {
        this.context = context;
        this.reminders = remiders;
    }

    public void setOnItemClickListenerReminder(onItemClickListReminder onItemClickListReminder) {
        this.onItemClickListReminder = onItemClickListReminder;
    }

    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_card_list_reminder, parent, false);
        return new ReminderAdapter.ReminderViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.vincula(reminder);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final TextView contextReminder;
        private Reminder reminder;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv);
            contextReminder= itemView.findViewById(R.id.tv_reminder);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onItemClickListReminder.onItemClickListReminder(reminder, getAdapterPosition());
                }
            });

        }
        public void vincula(Reminder reminder){
            this.reminder = reminder;
            preencheCampos(reminder);
        }

        private void preencheCampos(Reminder reminder) {
            text.setText("Lembrar");
            contextReminder.setText(reminder.getContextReminder());
        }
    }
    public void removeReminder(int position) {
        reminders.remove(position);
        notifyItemRemoved(position);
    }

    public void addReminder(Reminder reminder){
        reminders.add(reminder);
        notifyDataSetChanged();
    }
}
