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

public class ContractView extends AppCompatActivity {


    private ListView listViewCotract;
    private ContractAdapter CAdapter;
    private ArrayList<Contract> CotractList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_view);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        listViewCotract = (ListView) findViewById(R.id.ContractrList);
        CotractList = new ArrayList<>();


        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    Contract contract =  new Contract(child.getValue(Contract.class));
                    CotractList.add(contract);


                }
                CAdapter = new ContractAdapter(ContractView.this, CotractList);
                listViewCotract.setAdapter(CAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContractView.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }
}
