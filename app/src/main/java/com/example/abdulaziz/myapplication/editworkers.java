package com.example.abdulaziz.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class editworkers extends AppCompatActivity {



    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworker);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


        listView = (ListView) findViewById(R.id.editworkers);

        workersList = new ArrayList<>();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();


        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workersList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for(DataSnapshot c : dataSnapshot.getChildren()){
                        if(userUID.equals(child.child("posterID").getValue().toString())){
                            if(child.child("deleted").getValue().toString().equals("false")) {

                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);

                                break;
                            }
                            else
                                break;
                        }

                    }


                }
                wAdapter = new WorkerAdapter(editworkers.this, workersList);
                listView.setAdapter(wAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(editworkers.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });








        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(editworkers.this, editingworker.class);
                intent.putExtra("id",position);
                startActivity(intent);

            }
        });



    }


}


