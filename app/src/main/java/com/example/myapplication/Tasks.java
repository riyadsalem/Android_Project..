package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
    }

    public void backToList(View view) {
        startActivity( new Intent(getApplicationContext(),Lists.class));

    }

    public void GoToSearch(View view) {
    }

    public void DeleteTask(View view) {
    }

    public void CreateNewTask(View view) {
    }
}