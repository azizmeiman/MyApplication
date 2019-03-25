package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class poster_contract_view extends AppCompatActivity {

    private ListView listViewcontract;
    private Switch currentContract;
    private ContractAdapter CAdapter;
    private ArrayList<Contract> CotractList;
    private ArrayList<Contract> curContractList;
    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentID = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_contract_view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        listViewcontract = (ListView) findViewById(R.id.posterContractcur);
        currentContract = (Switch) findViewById(R.id.currcontracts);

        CotractList = new ArrayList<>();
        curContractList = new ArrayList<>();


        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CotractList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if (currentID.equals(child.child("posterID").getValue().toString())) {
                        Contract contract = new Contract(child.getValue(Contract.class));
                        String startDate = contract.getStartDate();
                        String endDate = contract.getEndDate();

                        CotractList.add(contract);
                        if (isCurrentContractMethod(startDate,endDate)==true) {
                            curContractList.add(contract);
                        }
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


        currentContract.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) { // if he want current contracts
                    CAdapter = new ContractAdapter(poster_contract_view.this, curContractList);
                    listViewcontract.setAdapter(CAdapter);


                } else {
                    CAdapter = new ContractAdapter(poster_contract_view.this, CotractList);
                    listViewcontract.setAdapter(CAdapter);

                }


            }
        });


        listViewcontract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(poster_contract_view.this, ViewProfileContract.class); // May I need to change it
                intent.putExtra("contractID", CotractList.get(position).getContractID());
                startActivity(intent);

            }
        });

    }

    String endDate;


    public boolean isCurrentContractMethod(String StartDate, String EndDate) {
        Date current = new Date();
        String sStart = StartDate;
        String sEnd = EndDate;
        Date start = new Date(), end = new Date();
        boolean isCurrentContract = false;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterS = new SimpleDateFormat("dd/MM/yyyy");

        try {

            start = formatterS.parse(sStart);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterE = new SimpleDateFormat("dd/MM/yyyy");

        try {

            end = formatterE.parse(sEnd);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (end.after(current) && start.before(current)) {
            isCurrentContract = true;

        }

        if(isCurrentContract){
            return true;
        }else{
            return false;
        }

    }

}