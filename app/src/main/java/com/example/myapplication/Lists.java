package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ListAdapter;
import com.example.myapplication.model.List;
import com.example.myapplication.model.ListItemRV;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Lists extends AppCompatActivity {

    DatabaseReference reff;
    List list;
    //......................................
    RecyclerView list_RV ;
    ListAdapter listAdapter ;
    static java.util.List<ListItemRV> listList_RV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        list_RV = findViewById(R.id.lists_rv);

        listList_RV.add(new ListItemRV("Coding"));
        listList_RV.add(new ListItemRV("Maintenance"));

        list_RV.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this , (ArrayList<ListItemRV>) listList_RV);
        list_RV.setAdapter(listAdapter);

//..........................................................................................
        list =new List();
        reff = FirebaseDatabase.getInstance().getReference().child("List");
    }

    public void backToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Tasks.class));

    }

    public void CreateNewList(View view) {
        EditText edtCreateNLs = (EditText) findViewById(R.id.edtCreateNLs);

        String listName= edtCreateNLs.getText().toString().trim();
        list.setListName(listName);

        reff.push().setValue(list);
        Toast.makeText(Lists.this, "Data CREATE Successfully", Toast.LENGTH_SHORT).show();

    }

    public void GoToSearch(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);
    }
}
