package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Lists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
    }

    public void backToLogin(View view) {
        startActivity( new Intent(getApplicationContext(),Tasks.class));

    }

    public void CreateNewList(View view) {
    }

    public void GoToSearch(View view) {
    }
}
