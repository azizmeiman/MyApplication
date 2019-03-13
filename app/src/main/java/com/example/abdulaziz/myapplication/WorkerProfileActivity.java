package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        final TextView name = (TextView) findViewById(R.id.workerName);
        final TextView skills = (TextView) findViewById(R.id.skills);
        final TextView price = (TextView) findViewById(R.id.price);
        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        final ListView feedback = (ListView) findViewById(R.id.feedback);
        final  Button hire = (Button) findViewById(R.id.Hire);


        ArrayList<String> feed = new ArrayList<String>();
        feed.add("Good ");
        feed.add("Worst!");
        feed.add("Very Good");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, feed);
        feedback.setAdapter(myAdapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String id = extras.getString("id");
        final int months = extras.getInt("month");
        final int days = extras.getInt("day");



        myRef.child("Worker").addValueEventListener(new ValueEventListener() {
            Worker worker = new Worker();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                   if(child.child("id").getValue().toString().equals(id)) {

                    worker = new Worker(child.getValue(Worker.class));
                     name.setText("Worker name: "+worker.getName());
                     skills.setText("skills: "+worker.getSkills());
                     price.setText("price perH:"+String.valueOf(worker.getPrice())+"SR");
                     int monthD = months*30;
                     monthD=monthD+days;
                     int totalPrice1 = worker.getPrice()*monthD;
                     totalPrice.setText("Total price:"+String.valueOf(totalPrice1)+"SR");

                       break;
                   }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(WorkerProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });

        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request = new Intent(WorkerProfileActivity.this,RequestActivity.class);
                startActivity(request);
            }
        });



    }
}
