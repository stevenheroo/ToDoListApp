package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    Context context;
    ArrayList<TaskModel> taskModelArrayList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskList) {
        this.context = context;
        this.taskModelArrayList = taskList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rows_layout, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        final TaskModel taskModel = taskModelArrayList.get(position);
        holder.firstname.setText(taskModel.getFirstname().toUpperCase().trim());
        holder.lastname.setText(taskModel.getLastname().toUpperCase().trim());
        holder.phone.setText(taskModel.getPhone());
        holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteTask();
                    }
                }
        );
    }

    private void deleteTask() {

    }

    @Override
    public int getItemCount() {
        return taskModelArrayList.size();
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {

        TextView firstname;
        TextView lastname;
        TextView phone;
        TextView id;
        CardView cardParentView;
        ImageView edit;
        ImageView delete;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            phone = itemView.findViewById(R.id.phone);
            id = itemView.findViewById(R.id.id);
            cardParentView = itemView.findViewById(R.id.card_parentView);
            edit = itemView.findViewById(R.id.edit_img_BTN);
            delete = itemView.findViewById(R.id.delete_img_BTN);

        }
    }
}
