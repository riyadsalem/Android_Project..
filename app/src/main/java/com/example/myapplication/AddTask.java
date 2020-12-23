package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTask extends AppCompatActivity {
    EditText edtTaskNameAT,edtDateAT,edtDescriptionAT;
    TextView addTask;
    DatabaseReference reff;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        edtTaskNameAT =(EditText) findViewById(R.id.edtTaskNameAT);
        edtDateAT = (EditText)findViewById(R.id.edtDateAT);
        edtDescriptionAT =(EditText) findViewById(R.id.edtDescriptionAT);
        addTask  = (TextView) findViewById(R.id.addTask);

        task=new Task();

        reff = FirebaseDatabase.getInstance().getReference().child("Task");

        String createNewTask = getIntent().getStringExtra("createNewTask");
        edtTaskNameAT.setText(createNewTask);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName= edtTaskNameAT.getText().toString().trim();
                String date= edtDateAT.getText().toString().trim();
                String description= edtDescriptionAT.getText().toString().trim();
                task.setTaskName(taskName);
                task.setDate(date);
                task.setDescription(description);
                reff.push().setValue(task);
                Toast.makeText(AddTask.this, "Data CREATE Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Tasks.class);
                intent.putExtra("taskName",edtTaskNameAT.getText().toString());
                intent.putExtra("taskDate",edtDateAT.getText().toString());
                intent.putExtra("taskDescription",edtDescriptionAT.getText().toString());
                startActivity(intent);
            }
        });


    }
}