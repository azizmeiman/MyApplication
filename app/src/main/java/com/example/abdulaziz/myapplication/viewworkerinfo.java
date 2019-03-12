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
        final TextView workerName = (TextView) findViewById(R.id.Wname);
        final TextView WorkerSkills = (TextView) findViewById(R.id.Wskills);
        final TextView WorkerPrice = (TextView) findViewById(R.id.Wprice);
        final TextView WorkerIncome = (TextView) findViewById(R.id.Wtotalicome);
        final TextView namelabel = (TextView) findViewById(R.id.textView10);
        final TextView skilllabel = (TextView) findViewById(R.id.textView11);
        final TextView pricelabel = (TextView) findViewById(R.id.textView12);
        final TextView incomelabel = (TextView) findViewById(R.id.textView13);

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

                    for(DataSnapshot c : dataSnapshot.getChildren()){
                        if(userUID.equals(child.child("posterID").getValue().toString())){
                            if(child.child("deleted").getValue().toString().equals("false")) {
                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);
                                break;
                            }
                            else
                                break;
                        }else
                            Toast.makeText(viewworkerinfo.this,"لايوجد لديك عمال", Toast.LENGTH_SHORT).show();

                    }


                  }

                Worker w1 = workersList.get(i);
                workerName.setText(w1.getName());
                WorkerSkills.setText(w1.getSkills());
                WorkerPrice.setText(String.valueOf(w1.getPrice()));
                WorkerIncome.setText(String.valueOf(w1.getTotalIncome()));


                 }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewworkerinfo.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }
}