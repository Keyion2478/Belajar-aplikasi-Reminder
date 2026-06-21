package com.example.reminderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderAdapter
        extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    Context context;
    ArrayList<Reminder> reminderList;
    DBHelper dbHelper;
    OnItemActionListener listener;

    public interface OnItemActionListener {
        void onEdit(Reminder reminder);
        void onDelete(Reminder reminder);
        void onComplete(Reminder reminder);
    }

    public ReminderAdapter(Context context,
                           ArrayList<Reminder> reminderList,
                           DBHelper dbHelper,
                           OnItemActionListener listener) {

        this.context = context;
        this.reminderList = reminderList;
        this.dbHelper = dbHelper;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_reminder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {

        Reminder reminder = reminderList.get(position);

        holder.tvTitle.setText(reminder.getTitle());

        holder.tvDescription.setText(
                reminder.getDescription()
        );

        holder.tvDate.setText(
                reminder.getDate()
        );

        holder.tvTime.setText(
                reminder.getTime()
        );

        holder.tvCategory.setText(
                "#" + reminder.getCategory()
        );

        holder.tvStatus.setText(
                reminder.getStatus()
        );

        if (reminder.getStatus().equals("Complete")) {
            holder.tvStatus.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                            context.getResources().getColor(R.color.colorComplete)
                    )
            );
        } else {
            holder.tvStatus.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                            context.getResources().getColor(R.color.colorPrimary)
                    )
            );
        }

        holder.btnEdit.setOnClickListener(v ->
                listener.onEdit(reminder)
        );

        holder.btnDelete.setOnClickListener(v ->
                listener.onDelete(reminder)
        );

        holder.btnComplete.setOnClickListener(v ->
                listener.onComplete(reminder)
        );
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvTitle,
                tvDescription,
                tvDate,
                tvTime,
                tvCategory,
                tvStatus;

        Button btnEdit,
                btnDelete,
                btnComplete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvStatus = itemView.findViewById(R.id.tvStatus);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnComplete = itemView.findViewById(R.id.btnComplete);
        }
    }
}