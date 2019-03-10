package com.example.abdulaziz.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewWorkesActivity extends AppCompatActivity {



    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workes);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listView = (ListView) findViewById(R.id.WorkersList);
        workersList = new ArrayList<>();

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {



                    Worker worker =  new Worker(child.getValue(Worker.class));
                    workersList.add(worker);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        wAdapter = new WorkerAdapter(this,workersList);
        listView.setAdapter(wAdapter);



    }
}

