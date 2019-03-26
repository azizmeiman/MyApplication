package com.example.abdulaziz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addRateActivity extends AppCompatActivity {

    private Worker w;
    private float cRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rate);

        final RatingBar rb = (RatingBar) findViewById(R.id.rateGitter);
        final Button addRate = (Button) findViewById(R.id.rateWorkerBut);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String workerID = extras.getString("workerID");

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                w = dataSnapshot.child(workerID).getValue(Worker.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cRate = rb.getRating();
            }
        });


        addRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               float total = w.getTotalRate();
                int numR = w.getnRate();

                total = total+cRate;
                numR = numR+1;
                myRef.child("Worker").child(workerID).child("totalRate").setValue(total);
                myRef.child("Worker").child(workerID).child("nRate").setValue(numR);

                finish();
            }
        });
    }
}
