package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class poster_contract_view extends AppCompatActivity {

    private ListView listViewcontract;
    private Switch oldcontracts;
    private ContractAdapter CAdapter;
    private ArrayList<Contract> CotractList;
    private ArrayList<Contract> oldContractList;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentID = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_contract_view);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        listViewcontract = (ListView) findViewById(R.id.posterContractcur);
        CotractList = new ArrayList<>();
        oldContractList = new ArrayList<>();


        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {


                        if (currentID.equals(child.child("posterID").getValue().toString())) {

                            Contract contract = new Contract(child.getValue(Contract.class));
                            CotractList.add(contract);

                        }

                    }

                    CAdapter = new ContractAdapter(poster_contract_view.this, CotractList);
                    listViewcontract.setAdapter(CAdapter);
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(poster_contract_view.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


        oldcontracts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if (isChecked) { // if he want old contracts only
                     CAdapter = new ContractAdapter(poster_contract_view.this, oldContractList);
                     listViewcontract.setAdapter(CAdapter);

                 }
                 else{
                     CAdapter = new ContractAdapter(poster_contract_view.this, CotractList);
                     listViewcontract.setAdapter(CAdapter);

                 }


               }
                 });



        listViewcontract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(poster_contract_view.this, ViewProfileContract.class); // May I need to change it
                intent.putExtra("contractID",CotractList.get(position).getContractID());
                startActivity(intent);

            }
        });

    }
}


