package com.example.mobileapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.TaskStackBuilder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.app.PendingIntent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton add;
    public static final int REQUEST_CODE =100;
    Task newTask = new Task();
    RecyclerView TRecyclerView;
    TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //list view

        loadTasks();
        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpopupwindow();
            }
        });

    }
    public void onClick(View v)
    {

    }
////hi
    private void openpopupwindow() {
        Intent popupwindow = new Intent(MainActivity.this, popupWindow.class);
        startActivityForResult(popupwindow,REQUEST_CODE);
    }
    public void loadTasks(){
        TRecyclerView = findViewById(R.id.Recycler);
        List<Task> List= new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Task");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userList.clear();
                for (DataSnapshot dataSnapshotp : snapshot.getChildren()) {
                    Task task = dataSnapshotp.getValue(Task.class);
                    List.add(task);
                }

                adapter = new TaskAdapter(MainActivity.this, List, new ClickListener() {
                    @Override
                    public void OnItemClick(View v, int pos) {

                    } // end on item click listener
                });
                TRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                TRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {

                    loadTasks();

                    Toast.makeText(getApplicationContext(),"Reminder added",1000).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Reminder not added",1000).show();
                }
        }
    }
}