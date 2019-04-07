package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class highestBalancePoster extends AppCompatActivity {

    private ListView listViewposter;
    private PosterAdapter pAdapter;
    private ArrayList<WorkerPoster> posterList;
    private FirebaseAuth mAuth;
    int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_balance_poster);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listViewposter = (ListView) findViewById(R.id.highestBalancePosterList);
        posterList = new ArrayList<>();


        myRef.child("WorkerPoster").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    WorkerPoster workerPoster =  new WorkerPoster(child.getValue(WorkerPoster.class));
                    posterList.add(workerPoster);

                    Collections.sort(posterList, new Comparator<WorkerPoster>() {
                        @Override
                        public int compare(WorkerPoster o1, WorkerPoster o2) {

                            if (o1.getContractNumber() == o2.getContractNumber())
                            {
                                return 0;
                            }
                            else if (o1.getContractNumber() >
                                    o2.getContractNumber())
                            {
                                return -1;
                            }
                            return 1;
                        }
                    });

                }
                pAdapter = new PosterAdapter(highestBalancePoster.this, posterList);
                listViewposter.setAdapter(pAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(highestBalancePoster.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        listViewposter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(highestBalancePoster.this, viewPosterAdmin.class);
                intent.putExtra("rpid",posterList.get(position).getRPID());
                startActivity(intent);

            }
        });



    }
}
