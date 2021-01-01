package com.example.myapplication.Adapters;
//////////////////////////////////  #complete   //////////////////////////////////

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Tasks;
import com.example.myapplication.model.ListItemRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListVh> {
    FirebaseAuth mAuth;
    int taskSize;
    private Context context;
    private ArrayList<ListItemRV> lists = null;
    // TaskAdapter taskAdapter;

    public ListAdapter(Context context, ArrayList<ListItemRV> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getItemCount() { return lists.size(); }


    public class ListVh extends RecyclerView.ViewHolder {
        TextView NewList;
       TextView txtCounterTasks;

        public ListVh(@NonNull View view) {
            super(view);
            NewList = itemView.findViewById(R.id.txtNewList);
          txtCounterTasks = itemView.findViewById(R.id.txtCounterTasks);
        }

        public void setData(final ListItemRV list) {
            NewList.setText(list.getNewList());
         //   txtCounterTasks.setText(list.getCounterTasks());

        }
    }


    @NonNull
    @Override
    public ListVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listsviewcall, parent, false);
        return new ListVh(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListVh holder, int position) {
        holder.setData(lists.get(position));
        final ListItemRV list = lists.get(position);
        holder.NewList.setText(list.getNewList());
    // holder.txtCounterTasks.setText(list.getCounterTasks());
        holder.txtCounterTasks.setText(taskSize(list.getId())+ " task");
/*
        holder.NewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTask.class);
                intent.putExtra("listName",list.getNewList());
                intent.putExtra("ListId",list.getId());
                context.startActivity(intent);

            }
        });

 */

        holder.NewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Task","GoToTasks");
                Intent intent = new Intent(context, Tasks.class);
                intent.putExtra("taskName",list.getNewList());
                intent.putExtra("taskId",list.getId());
                context.startActivity(intent);

            }
        });


    }

    public   int taskSize (String id ){

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        // <<<<<<<<<<<<<<<<<<<<<<<<<< Problem # 1 -> .child("Lists").???????.child("Tasks") >>>>>>>>>>>>>>>>>>>>>>
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("Tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            taskSize +=1;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


        return taskSize;
    }


}