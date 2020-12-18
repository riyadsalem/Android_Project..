package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void BackTo(View view) {
        Intent intent = new Intent(getApplicationContext(), Lists.class);
        startActivity(intent);

    }

    public void ShowListViewSrh(View view) {
    }
}