package com.example.myapplication.Adapters;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.TaskItemRV;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVh> {

    Context context;
    ArrayList<TaskItemRV> tasks = null;

    public TaskAdapter(Context context, ArrayList<TaskItemRV> tasks) {
        this.context = context;
        this.tasks = tasks;
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class TaskVh extends RecyclerView.ViewHolder {

        CheckBox checkBox;

        public TaskVh(@NonNull View row) {
            super(row);
            checkBox = row.findViewById(R.id.checkboxTVC);
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
        holder.setData(tasks.get(position));
        final TaskItemRV taskEntity = tasks.get(position);
        if(taskEntity.getIsChecked()){
            holder.checkBox.setChecked(true);
            holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
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
    }


}