package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class ViewRequestsActivity extends AppCompatActivity {

    private ListView listView;
    private RequestAdapter rAdapter;
    private ArrayList<Request> RequestsList;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentID = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listView = (ListView) findViewById(R.id.requestsList);
        RequestsList = new ArrayList<>();


        myRef.child("Request").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                RequestsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(currentID.equals(child.child("posterID").getValue().toString()) && "3".equals(child.child("status").getValue().toString()) ) {
                        Request request = new Request(child.getValue(Request.class));
                        RequestsList.add(request);
                    }

                }
                if(RequestsList.size()!= 0 ) {
                    rAdapter = new RequestAdapter(ViewRequestsActivity.this, RequestsList);
                    listView.setAdapter(rAdapter);
                }else
                    Toast.makeText(ViewRequestsActivity.this, "لايوجد طلبات", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewRequestsActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
