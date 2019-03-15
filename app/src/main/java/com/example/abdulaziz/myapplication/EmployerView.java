package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployerView extends AppCompatActivity {


        private ListView listView;
        private EmpAdapter eAdapter;
        private ArrayList<Employer> EmpList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_workes);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            listView = (ListView) findViewById(R.id.EmpList);
            EmpList = new ArrayList<>();


            myRef.child("Employer").addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot child : dataSnapshot.getChildren()) {


                        Employer Employer =  new Employer(child.getValue(Employer.class));
                        EmpList.add(Employer);


                    }
                    eAdapter = new EmpAdapter(EmployerView.this, EmpList);
                    listView.setAdapter(eAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(EmployerView.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

                }
            });



        }
}
