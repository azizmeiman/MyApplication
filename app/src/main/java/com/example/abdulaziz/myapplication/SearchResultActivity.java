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

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        listView = (ListView) findViewById(R.id.searchResultList);
        workersList = new ArrayList<>();


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int months = extras.getInt("EXTRA_MONTHS");
        final int days = extras.getInt("EXTRA_DAYS");
        final String city = extras.getString("EXTRA_CITY");
        final String skill = extras.getString("EXTRA_SKILL");

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    workersList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {


                        if(skill.equals(child.child("skills").getValue().toString()) && city.equals(child.child("city").getValue().toString())){

                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);

                        }



                } String a = String.valueOf(workersList.size());
                Toast.makeText(SearchResultActivity.this, a, Toast.LENGTH_SHORT).show();

                wAdapter = new WorkerAdapter(SearchResultActivity.this, workersList);
                listView.setAdapter(wAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SearchResultActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SearchResultActivity.this, WorkerProfileActivity.class);
                intent.putExtra("id",workersList.get(position).getID());
                intent.putExtra("month",months);
                intent.putExtra("day",days);
                startActivity(intent);

            }
        });
    }
}
