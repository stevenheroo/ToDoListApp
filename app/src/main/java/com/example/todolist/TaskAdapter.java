package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    Context context;
    ArrayList<TaskModel> taskModelArrayList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskModelArrayList) {
        this.context = context;
        this.taskModelArrayList = taskModelArrayList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rows_layout, null, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        TaskModel taskModel = taskModelArrayList.get(position);

        holder.firstname.setText(taskModel.getFirstname().toUpperCase());
        holder.lastname.setText(taskModel.getLastname().toUpperCase());
        holder.phone.setText(taskModel.getPhone());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {

        EditText firstname;
        EditText lastname;
        EditText phone;
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
