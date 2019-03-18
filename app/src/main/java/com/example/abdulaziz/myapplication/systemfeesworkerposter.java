package com.example.abdulaziz.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class systemfeesworkerposter extends AppCompatActivity {


    private String fees;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemfees);
        final TextView systemfeelabel = (TextView) findViewById(R.id.systemfeeslabel);
        final TextView systemfeeinput = (TextView) findViewById(R.id.systemfeesinput);
        final Button systemfeeupload = (Button)findViewById(R.id.systemfeesupload);


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String posterUID = currentUser.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        myRef.child("SystemFees").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WorkerPoster poster1 = dataSnapshot.child(posterUID).getValue(WorkerPoster.class);
                fees = String.valueOf(poster1.getSystemfees());
                systemfeeinput.setText(fees);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        //upload the pic part is down

        systemfeeupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
