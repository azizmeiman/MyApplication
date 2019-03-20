package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmpContractViewActivity extends AppCompatActivity {

    private ListView listViewCotract;
    private ContractAdapter CAdapter;
    private ArrayList<Contract> CotractList;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentID = user.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_contract_view);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        listViewCotract = (ListView) findViewById(R.id.ContractList2);
        CotractList = new ArrayList<>();


        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(currentID.equals(child.child("empID").getValue().toString())){

                    Contract contract =  new Contract(child.getValue(Contract.class));
                    CotractList.add(contract);
                    }


                }

                CAdapter = new ContractAdapter(EmpContractViewActivity.this, CotractList);
                listViewCotract.setAdapter(CAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EmpContractViewActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        listViewCotract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EmpContractViewActivity.this, ViewProfileContract.class);
                intent.putExtra("contractID",CotractList.get(position).getContractID());
                startActivity(intent);

            }
        });



    }



    }

