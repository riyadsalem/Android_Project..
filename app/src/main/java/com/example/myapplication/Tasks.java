package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.TaskAdapter;
import com.example.myapplication.model.TaskItemRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tasks extends AppCompatActivity {

    // public Object listIdToAddTask;
    FirebaseAuth mAuth;
    RecyclerView tasks_RV = null;
    TaskAdapter taskAdapter = null;
   //  TaskItemRV taskItemRV = null;
    static List<TaskItemRV> tasksList_RV = new ArrayList<>();
    ImageButton btnCNTs ;
    EditText txtCreateNTs;
    TextView txtDeleteTS;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        TextView txtTitle2Ts = findViewById(R.id.txtTitle2Ts);
        txtTitle2Ts.setText(getIntent().getStringExtra("taskName")+" "+"List");

         btnCNTs = findViewById(R.id.btnCNTs);
         txtCreateNTs = findViewById(R.id.txtCreateNTs);
        txtDeleteTS =findViewById(R.id.txtDeleteTS);

        //  String taskName = getIntent().getStringExtra("taskName");
       // tasksList_RV.add(new TaskItemRV(taskName,false));

     /// save tasks //

        btnCNTs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                TaskItemRV newTask= new TaskItemRV();
                newTask.setTitle(txtCreateNTs.getText().toString());
                newTask.setIsChecked(false);
                String taskId=FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").push().getKey();
                String taskListId = getIntent().getStringExtra("taskId");
                newTask.setIdTask(taskId);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(taskListId).child("Tasks").child(taskId).setValue(newTask);
                Toast.makeText(Tasks.this,"task has been added successfully", Toast.LENGTH_SHORT).show();
                txtCreateNTs.setText(" ");
            }

        });


    //// get tasks //

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        String taskListId = getIntent().getStringExtra("taskId");

        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(taskListId).child("Tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tasksList_RV.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            TaskItemRV task =  snapshot.getValue(TaskItemRV.class);
                            tasksList_RV.add(task);
                        }
                        taskAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

   //////// delete tasks /////////

        txtDeleteTS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                String taskListId = getIntent().getStringExtra("taskId");
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(taskListId).removeValue();
            }

        });

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



    /*
    public void CreateNewTask(View view) {

        String taskId = getIntent().getStringExtra("taskId");

        EditText txtCreateNTs = findViewById(R.id.txtCreateNTs);
        Intent intent = new Intent(getApplicationContext(), AddTask.class);
        intent.putExtra("createNewTask",txtCreateNTs.getText().toString());
        intent.putExtra("taskId",taskId);
        startActivity(intent);
    }
     */

}