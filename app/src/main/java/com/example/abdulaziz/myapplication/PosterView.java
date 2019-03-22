package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PosterView extends AppCompatActivity {

    private ListView listViewposter;
    private PosterAdapter pAdapter;
    private ArrayList<WorkerPoster> posterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_view);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference();

        listViewposter = (ListView) findViewById(R.id.posterList);
        posterList = new ArrayList<>();


        myRef.child("WorkerPoster").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    WorkerPoster workerPoster =  new WorkerPoster(child.getValue(WorkerPoster.class));
                    posterList.add(workerPoster);


                }
                pAdapter = new PosterAdapter(PosterView.this, posterList);
                listViewposter.setAdapter(pAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PosterView.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        listViewposter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PosterView.this, viewPosterAdmin.class);
                intent.putExtra("rpid",posterList.get(position).getRPID());
                startActivity(intent);

            }
        });



    }
}
