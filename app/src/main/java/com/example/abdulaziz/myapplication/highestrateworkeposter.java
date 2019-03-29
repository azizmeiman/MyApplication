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
import java.util.Collections;
import java.util.Comparator;

public class highestrateworkeposter extends AppCompatActivity {


    private ListView  highestrateList;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private FirebaseAuth mAuth;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highestrateworkeposter);
         highestrateList = (ListView)findViewById(R.id.highestrateworkers);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        workersList = new ArrayList<>();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();


        myRef.child("Worker").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for(DataSnapshot c : dataSnapshot.getChildren()){

                        if(userUID.equals(child.child("posterID").getValue().toString())){
                            if(child.child("deleted").getValue().toString().equals("false")) {
                                n++;
                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);

                                    }break;
                                }
                           break;
                        }

                    }
                Collections.sort(workersList, new Comparator<Worker>() {
                    @Override
                    public int compare(Worker o1, Worker o2) {

                        if (o1.getTotalRate() ==
                                o2.getTotalRate())
                        {
                            return 0;
                        }
                        else if (o1.getTotalRate() >
                                o2.getTotalRate())
                        {
                            return -1;
                        }
                        return 1;
                    }
                });



                wAdapter = new WorkerAdapter(highestrateworkeposter.this, workersList);
                highestrateList.setAdapter(wAdapter);
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(highestrateworkeposter.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });








        highestrateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Worker w = workersList.get(position);

                Intent intent = new Intent(highestrateworkeposter.this, viewworkerinfo.class);
                intent.putExtra("workerid",w.getID());
                startActivity(intent);

            }
        });



    }


}


