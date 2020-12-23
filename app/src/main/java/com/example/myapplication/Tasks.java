package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.TaskAdapter;
import com.example.myapplication.model.TaskItemRV;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    RecyclerView tasks_RV = null;
    TaskAdapter taskAdapter = null;
    static ArrayList<TaskItemRV> tasksList_RV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        String taskName = getIntent().getStringExtra("taskName");
        String taskDate = getIntent().getStringExtra("taskDate");
        String taskDescription = getIntent().getStringExtra("taskDescription");


        tasksList_RV.add(new TaskItemRV(taskName,false));


        tasks_RV = findViewById(R.id.tasks_rv);
        tasks_RV.setLayoutManager(new LinearLayoutManager(Tasks.this));
        taskAdapter = new TaskAdapter(this ,tasksList_RV );
        tasks_RV.setAdapter(taskAdapter);

    }

    public void backToList(View view) {
        startActivity( new Intent(getApplicationContext(),Lists.class));

    }

    public void GoToSearch(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);
    }

    public void DeleteTask(View view) {
    }

    public void CreateNewTask(View view) {

        EditText txtCreateNTs = findViewById(R.id.txtCreateNTs);
        Intent intent = new Intent(getApplicationContext(), AddTask.class);
        intent.putExtra("createNewTask",txtCreateNTs.getText().toString());
        startActivity(intent);
    }
}