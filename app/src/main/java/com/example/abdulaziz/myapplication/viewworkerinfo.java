package com.example.abdulaziz.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewworkerinfo extends AppCompatActivity {

    String workername;
    String workerprice;
    ArrayList<Worker> workersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkerinfo);
        ImageView imageView = (ImageView) findViewById(R.id.imageView10);
        TextView workerName = (TextView) findViewById(R.id.Wname);
        TextView WorkerSkills = (TextView) findViewById(R.id.Wskills);
        TextView WorkerPrice = (TextView) findViewById(R.id.Wprice);
        TextView WorkerIncome = (TextView) findViewById(R.id.Wtotalicome);

        final int i = getIntent().getIntExtra("id",0);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        final FirebaseAuth  mAuth = FirebaseAuth.getInstance();

        workersList = new ArrayList<>();

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for (DataSnapshot c : dataSnapshot.getChildren()) {
                        if (userUID.equals(child.child("posterID").getValue().toString())) {
                            Worker worker = new Worker(child.getValue(Worker.class));
                            workersList.add(worker);
                            String a = String.valueOf(workersList.size());
                            Toast.makeText(viewworkerinfo.this, a,Toast.LENGTH_SHORT).show();
                            break;

                        } else
                            Toast.makeText(viewworkerinfo.this, "لم تقم بإضافة عامل", Toast.LENGTH_SHORT).show();

                       }


                  }


                 }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewworkerinfo.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}