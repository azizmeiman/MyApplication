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
import java.util.Collections;
import java.util.Comparator;

public class highestContractEmp extends AppCompatActivity {

    private ListView listVieemp;
    private EmpAdapter EAdapter;
    private ArrayList<Employer> empList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_contract_emp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listVieemp = (ListView) findViewById(R.id.highestContractempList);
        empList = new ArrayList<>();


        myRef.child("Employer").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    Employer Employer =  new Employer(child.getValue(Employer.class));
                    empList.add(Employer);

                    Collections.sort(empList, new Comparator<Employer>() {
                        @Override
                        public int compare(Employer o1, Employer o2) {

                            if (o1.getContractNomE() == o2.getContractNomE())
                            {
                                return 0;
                            }
                            else if (o1.getContractNomE() >
                                    o2.getContractNomE())
                            {
                                return -1;
                            }
                            return 1;
                        }
                    });

                }
                EAdapter = new EmpAdapter(highestContractEmp.this, empList);
                listVieemp.setAdapter(EAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(highestContractEmp.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        listVieemp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(highestContractEmp.this, ViewEmpProfileAdmin.class);
                intent.putExtra("email", empList.get(position).getEmail());
                startActivity(intent);

            }
        });


    }
}
