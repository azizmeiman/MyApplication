package com.example.abdulaziz.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.FitWindowsViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewWorkesActivity extends AppCompatActivity {



    private ListView listView;
    private Switch availableSwitch;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private ArrayList<Worker> AvailableList;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workes);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


        listView = (ListView) findViewById(R.id.WorkersList);
        availableSwitch = findViewById(R.id.avilableworkerSwitch);


        workersList = new ArrayList<>();
        AvailableList = new ArrayList<>();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();


        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

                                                        @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for (DataSnapshot c : dataSnapshot.getChildren()) {

                      if (userUID.equals(child.child("posterID").getValue().toString())) {
                         if ((child.child("deleted").getValue().toString().equals("false"))) {
                             Worker worker = new Worker(child.getValue(Worker.class));

                             if(worker.isAvailable()) {
                                 AvailableList.add(worker);
                                 workersList.add(worker);
                                 break;
                             }
                             else
                                 workersList.add(worker);
                                   break;
                                     } else
                             Toast.makeText(ViewWorkesActivity.this, "لايوجد لديك عمال", Toast.LENGTH_SHORT).show();
                                       break;
                                         }


                                           }

                                             }

                wAdapter = new WorkerAdapter(ViewWorkesActivity.this, workersList);
                listView.setAdapter(wAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewWorkesActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });




     availableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked) {
                               wAdapter = new WorkerAdapter(ViewWorkesActivity.this, AvailableList);
                               listView.setAdapter(wAdapter);

                           }
                           else{
                       wAdapter = new WorkerAdapter(ViewWorkesActivity.this, workersList);
                       listView.setAdapter(wAdapter);

                   }


                   }


             });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Worker w = workersList.get(position);
                Intent intent = new Intent(ViewWorkesActivity.this, viewworkerinfo.class);
                intent.putExtra("workerid",w.getID());
                startActivity(intent);

            }
        });



    }


}


