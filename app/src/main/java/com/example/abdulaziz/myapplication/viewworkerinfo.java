package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewworkerinfo extends AppCompatActivity {

    String workername;
    String workerprice;
    ArrayList<Worker> workersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkerinfo);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView10);
        final TextView workerName = (TextView) findViewById(R.id.Wname);
        final TextView WorkerSkills = (TextView) findViewById(R.id.Wskills);
        final TextView WorkerPrice = (TextView) findViewById(R.id.Wprice);
        final TextView WorkerIncome = (TextView) findViewById(R.id.Wtotalicome);
        final TextView namelabel = (TextView) findViewById(R.id.textView10);
        final TextView skilllabel = (TextView) findViewById(R.id.textView11);
        final TextView pricelabel = (TextView) findViewById(R.id.textView12);
        final TextView incomelabel = (TextView) findViewById(R.id.textView13);
        final TextView ratelabel = (TextView) findViewById(R.id.textView117);
        final RatingBar rate = (RatingBar) findViewById(R.id.ratingBar3);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String workerid = extras.getString("workerid");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        final FirebaseAuth  mAuth = FirebaseAuth.getInstance();
        final String currentWorker = mAuth.getCurrentUser().getUid();

        workersList = new ArrayList<>();

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Worker w1 = dataSnapshot.child(workerid).getValue(Worker.class);

                workerName.setText(w1.getName());
                WorkerSkills.setText(w1.getSkills());
                WorkerPrice.setText(String.valueOf(w1.getPrice()));
                WorkerIncome.setText(String.valueOf(w1.getTotalIncome()));
                rate.setRating(w1.getTotalRate()/w1.getnRate());
                Picasso.get().load(w1.getPicture()).into(imageView);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewworkerinfo.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}