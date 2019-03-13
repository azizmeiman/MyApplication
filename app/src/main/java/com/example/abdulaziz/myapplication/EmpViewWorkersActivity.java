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

public class EmpViewWorkersActivity extends AppCompatActivity {

    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_emp_view_workers);

      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference myRef = database.getReference();

      listView = (ListView) findViewById(R.id.WorkersList2);
      workersList = new ArrayList<>();


      myRef.child("Worker").addValueEventListener(new ValueEventListener() {

          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              workersList.clear();
              for (DataSnapshot child : dataSnapshot.getChildren()) {


                  Worker worker =  new Worker(child.getValue(Worker.class));
                  workersList.add(worker);


              }
              if(workersList.size()!= 0 ) {
                  wAdapter = new WorkerAdapter(EmpViewWorkersActivity.this, workersList);
                  listView.setAdapter(wAdapter);
              }else
                  Toast.makeText(EmpViewWorkersActivity.this, "لايوجد عمال", Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Toast.makeText(EmpViewWorkersActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

          }
      });


      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(EmpViewWorkersActivity.this, WorkerProfileActivity.class);
              intent.putExtra("id",position);
              //intent.putExtra("month",);
              startActivity(intent);

          }
      });



  }


}