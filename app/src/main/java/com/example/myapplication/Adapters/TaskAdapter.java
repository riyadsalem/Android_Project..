package com.example.myapplication.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddTask;
import com.example.myapplication.R;
import com.example.myapplication.model.TaskItemRV;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVh> {

    Context context;
    List<TaskItemRV> tasks ;
   // FirebaseAuth mAuth;
 //   Tasks bigTasks;

    public TaskAdapter(Context context,List <TaskItemRV> tasks) {
        this.context = context;
        this.tasks = tasks;
    }
    @Override
    public int getItemCount() { return tasks.size(); }


    class TaskVh extends RecyclerView.ViewHolder {

        CheckBox checkBox;
     //   TextView txtDeleteTS;
        TextView txtEditTs;

        public TaskVh(@NonNull View view) {
            super(view);
            checkBox = itemView.findViewById(R.id.checkboxTVC);
        //    txtDeleteTS = itemView.findViewById(R.id.txtDeleteTS);
            txtEditTs = itemView.findViewById(R.id.txtEditTs);
        }

        public void setData(final TaskItemRV task) {
            checkBox.setText(task.getTitle());
            checkBox.setSelected(task.getIsChecked());
        }
    }

    @NonNull
    @Override
    public TaskVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tasksviewcall, parent, false);
        return new TaskVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskVh holder, int position) {
        holder.setData(tasks.get(position));  /////////////////////
        final TaskItemRV taskEntity = tasks.get(position);
        if(taskEntity.getIsChecked() ){
            holder.checkBox.setChecked(true);
            holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
       // holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         //   @Override
         //   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
/*
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();

                TaskItemRV newTask= new TaskItemRV();
                newTask.setTitle(taskEntity.getTitle());
                newTask.setIsChecked(isChecked);
                newTask.setIdTask(taskEntity.idTask);
  // FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("Tasks").child(taskEntity.getIdTask()).setValue(newTask);

 */
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                taskEntity.setIsChecked(isChecked);
                holder.checkBox.setSelected(isChecked);
                if(isChecked){
                    holder.checkBox.setText(taskEntity.getTitle());
                    holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    holder.checkBox.setText(taskEntity.getTitle());
                    holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });

        holder.txtEditTs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Task","GoToTasks");
                Intent intent = new Intent(context, AddTask.class);
                intent.putExtra("titleTask",taskEntity.getTitle());
                intent.putExtra("idForTask",taskEntity.getIdTask());
                context.startActivity(intent);
            }
        });

/*
        holder.txtDeleteTS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").child(taskEntity.getIdTask()).removeValue();
            }
        });
 */


    }
}