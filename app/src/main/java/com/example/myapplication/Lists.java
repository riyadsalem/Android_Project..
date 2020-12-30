package com.example.myapplication;
//////////////////////////////////  complete   //////////////////////////////////
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ListAdapter;
import com.example.myapplication.model.ListItemRV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Lists extends AppCompatActivity {

    DatabaseReference reff;
    FirebaseAuth mAuth;
   // List list;
    TextView txtBackToLoginLs;
    RecyclerView list_RV ;
    ListAdapter listAdapter ;
  //  TaskAdapter taskAdapter;
    static java.util.List<ListItemRV> listList_RV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        list_RV = findViewById(R.id.lists_rv);
        txtBackToLoginLs = findViewById(R.id.txtBackToLoginLs);

     //   listList_RV.add(new ListItemRV("Coding"));
      //  listList_RV.add(new ListItemRV("Maintenance"));

       // get list //
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listList_RV.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                            ListItemRV List =  snapshot.getValue(ListItemRV.class);
                            listList_RV.add(List);
                        }
                        listAdapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });




        list_RV.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this , (ArrayList<ListItemRV>) listList_RV);
        list_RV.setAdapter(listAdapter);

       // list =new List();
   //     reff = FirebaseDatabase.getInstance().getReference().child("List");
    }

    public void CreateNewList(View view) {
        EditText edtCreateNLs = (EditText) findViewById(R.id.edtCreateNLs);

/*
        String listName= edtCreateNLs.getText().toString().trim();
        list.setListName(listName);
        reff.push().setValue(list);
        Toast.makeText(Lists.this, "Data CREATE Successfully", Toast.LENGTH_SHORT).show();

 */

/// save ////
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        ListItemRV newTask= new ListItemRV();
        newTask.setNewList(edtCreateNLs.getText().toString());
       // newTask.setCounterTasks(taskAdapter.getItemCount());
        String listId=FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
        newTask.setId(listId);
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(listId).setValue(newTask);
        Toast.makeText(Lists.this,"task has been added successfully", Toast.LENGTH_SHORT).show();
        edtCreateNLs.setText(" ");


        txtBackToLoginLs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lists.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void GoToSearch(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);
    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Lists.this,"signed out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Lists.this, LogIn.class);
        startActivity(intent);
    }

  /*  public void backToLogin(View view) {
        Intent intent = new Intent(Lists.this, LogIn.class);
        startActivity(intent);
    }

   */

}
