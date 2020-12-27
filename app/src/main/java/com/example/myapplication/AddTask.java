package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.TaskItemRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AddTask extends AppCompatActivity {
    EditText edtTaskNameAT,edtDateAT,edtDescriptionAT;
    TextView addTask;
    TextView txtEdit;
    TextView deleteTask;
    // TextView txtBackToLists;
  //  DatabaseReference reff;
  //  TaskItemRV taskItemRV;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        edtTaskNameAT = (EditText) findViewById(R.id.edtTaskNameAT);
        edtDateAT = (EditText) findViewById(R.id.edtDateAT);
        edtDescriptionAT = (EditText) findViewById(R.id.edtDescriptionAT);
        addTask = (TextView) findViewById(R.id.addTask);
        txtEdit = (TextView) findViewById(R.id.txtEdit);
        deleteTask = (TextView) findViewById(R.id.deleteTask);
     //   txtBackToLists = (TextView) findViewById(R.id.txtBackToLists);

        String titleTask = getIntent().getStringExtra("titleTask");
      //  String idForTask = getIntent().getStringExtra("idForTask");
        edtTaskNameAT.setText(titleTask);

        // reff = FirebaseDatabase.getInstance().getReference().child("Task");
/*
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
             //   intent.putExtra("taskName",edtTaskNameAT.getText().toString());
                intent.putExtra("taskDate",edtDateAT.getText().toString());
                intent.putExtra("taskDescription",edtDescriptionAT.getText().toString());
                startActivity(intent);


 */
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleTask = getIntent().getStringExtra("titleTask");
                String idForTask = getIntent().getStringExtra("idForTask");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        TaskItemRV newTask = new TaskItemRV();
        newTask.setIdTask(idForTask);
        newTask.setTitle(titleTask);
        newTask.setDate(edtDateAT.getText().toString());
        newTask.setDescription(edtDescriptionAT.getText().toString());
      //  newTask.setIdTask(taskId);
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("Tasks").child(idForTask).setValue(newTask);
        Toast.makeText(AddTask.this, "task has been added successfully", Toast.LENGTH_SHORT).show();
        edtTaskNameAT.setText(" ");
        edtDateAT.setText(" ");
        edtDescriptionAT.setText(" ");
        //   Intent intent = new Intent(getApplicationContext(), Tasks.class);
        //    intent.putExtra("titleName",edtTaskNameAT.getText().toString());
        //  intent.putExtra("dataTask",edtDateAT.getText().toString());
        //    intent.putExtra("descriptionTask",edtDescriptionAT.getText().toString());
        //  startActivity(intent);


    }

});


   /// edit /////

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTask = getIntent().getStringExtra("titleTask");
                String idForTask = getIntent().getStringExtra("idForTask");
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                TaskItemRV newTask = new TaskItemRV();
                newTask.setIdTask(idForTask);
                newTask.setTitle(edtTaskNameAT.getText().toString());
                newTask.setDate(edtDateAT.getText().toString());
                newTask.setDescription(edtDescriptionAT.getText().toString());
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("Tasks").child(idForTask).setValue(newTask);
                Toast.makeText(AddTask.this, "task has been added successfully", Toast.LENGTH_SHORT).show();
                edtTaskNameAT.setText(" ");
                edtDateAT.setText(" ");
                edtDescriptionAT.setText(" ");
                //   Intent intent = new Intent(getApplicationContext(), Tasks.class);
                //    intent.putExtra("titleName",edtTaskNameAT.getText().toString());
                //  intent.putExtra("dataTask",edtDateAT.getText().toString());
                //    intent.putExtra("descriptionTask",edtDescriptionAT.getText().toString());
                //  startActivity(intent);


            }

        });

 //// delete /////////

        deleteTask.setOnClickListener(new View.OnClickListener(){
            String idForTask = getIntent().getStringExtra("idForTask");
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("Tasks").child(idForTask).removeValue();
                edtTaskNameAT.setText(" ");
                edtDateAT.setText(" ");
                edtDescriptionAT.setText(" ");

            }
        });

/*
        txtBackToLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tasks.class);
                startActivity(intent);
            }
        });

 */



}
}
