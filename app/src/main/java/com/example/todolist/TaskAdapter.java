package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    Context context;
    ArrayList<TaskModel> taskModelArrayList;
    DatabaseHelper databaseHelper;

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

        databaseHelper = new DatabaseHelper(context);
        final TaskModel taskModel = taskModelArrayList.get(position);
        holder.firstname.setText(taskModel.getFirstname().toUpperCase().trim());
        holder.lastname.setText(taskModel.getLastname().toUpperCase().trim());
        holder.phone.setText(taskModel.getPhone());
        holder.id.setText(taskModel.getId());

        //Delete task from list
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    databaseHelper.deleteTable(taskModel.getId());
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                            .setTitle("Delete?")
                            .setMessage("Do wish to remove")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    taskModelArrayList.remove(position);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do not delete
                                    Toast.makeText(context,"cancelled", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.show();
                    }
                }
        );
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
