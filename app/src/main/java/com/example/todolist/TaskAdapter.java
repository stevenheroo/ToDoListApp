package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.edit_bottom_sheet_bar);

                EditText edit_firstname, edit_lastname, edit_phone;
                Button editBTN;

                edit_firstname = bottomSheetDialog.findViewById(R.id.edit_firstname);
                edit_lastname = bottomSheetDialog.findViewById(R.id.edit_lastname);
                edit_phone = bottomSheetDialog.findViewById(R.id.edit_phone);
                editBTN = bottomSheetDialog.findViewById(R.id.updateBTN);

                //populate info in text field
                edit_firstname.setText(taskModel.getFirstname().trim());
                edit_lastname.setText(taskModel.getLastname().trim());
                edit_phone.setText(taskModel.getPhone().trim());

                //update button
                editBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //objects to store text in text fields
                        String firstName = edit_firstname.getText().toString().trim();
                        String lastName = edit_lastname.getText().toString().trim();
                        String phoneNumber = edit_phone.getText().toString().trim();
                        String id = "";

                        //set string objects equivalent position
                        taskModel.setFirstname(firstName);
                        taskModel.setLastname(lastName);
                        taskModel.setPhone(phoneNumber);
                        //update info in database
                        databaseHelper.updateTable(taskModel.getId(), new TaskModel(
                                id, firstName, lastName, phoneNumber
                        ));
                        notifyDataSetChanged();
                        Toast.makeText(context, "Change Successful", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskModelArrayList.size();
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
