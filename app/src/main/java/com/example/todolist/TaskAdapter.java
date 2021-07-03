package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    Context context;
    ArrayList first_name, last_name, phone_number;

    public TaskAdapter(Context context, ArrayList first_name, ArrayList last_name, ArrayList phone_number) {
        this.context = context;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rows_layout, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        holder.firstname.setText(first_name.get(position).toString().toUpperCase());
        holder.lastname.setText(last_name.get(position).toString().toUpperCase());
        holder.phone.setText(phone_number.get(position).toString().trim());

    }

    @Override
    public int getItemCount() {
        return first_name.size();
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {

        TextView firstname;
        TextView lastname;
        TextView phone;
        CardView cardParentView;
        ImageView edit;
        ImageView delete;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            phone = itemView.findViewById(R.id.phone);
            cardParentView = itemView.findViewById(R.id.card_parentView);
            edit = itemView.findViewById(R.id.edit_img_BTN);
            delete = itemView.findViewById(R.id.delete_img_BTN);

        }
    }
}
