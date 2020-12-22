package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


        tasksList_RV.add(new TaskItemRV("Reade",false));
        tasksList_RV.add(new TaskItemRV("pop",true));
        tasksList_RV.add(new TaskItemRV("Push",true));


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
        Intent intent = new Intent(getApplicationContext(), AddTask.class);
        startActivity(intent);
    }
}