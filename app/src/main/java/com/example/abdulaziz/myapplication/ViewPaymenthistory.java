package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class ViewPaymenthistory extends AppCompatActivity {

    private ListView listView;
    private PaymantAdapter rAdapter;
    private ArrayList<PaymentRecord> PaymentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_paymenthistory);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String currentID = extras.getString("idp");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listView = (ListView) findViewById(R.id.PamentsList);
        PaymentList = new ArrayList<>();


        myRef.child("PaymentRecord").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                PaymentList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(currentID.equals(child.child("posterID").getValue()))  {
                        PaymentRecord Record = new PaymentRecord(child.getValue(PaymentRecord.class));
                        PaymentList.add(Record);
                    }

                }
                if(PaymentList.size()!= 0 ) {
                    rAdapter = new PaymantAdapter(ViewPaymenthistory.this, PaymentList);
                    listView.setAdapter(rAdapter);
                }else
                    Toast.makeText(ViewPaymenthistory.this, "لايوجد عمليات ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewPaymenthistory.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });





    }
}
