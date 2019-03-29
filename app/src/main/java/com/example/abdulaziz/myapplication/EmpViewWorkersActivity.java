package com.example.abdulaziz.myapplication;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmpViewWorkersActivity extends AppCompatActivity {

    private ListView listView;
    private Spinner sortS;
    private Switch aSwitch;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private ArrayList<Worker> AvailabilWorkers;
    private ArrayList<Worker> PriceWorkers;
    private ArrayList<Worker> RatingWorkers;
    private ArrayList<Worker> currentList;



  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_emp_view_workers);

      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference myRef = database.getReference();

      Intent intent = getIntent();
      Bundle extras = intent.getExtras();
      final int months = extras.getInt("EXTRA_MONTHS");
      final int days = extras.getInt("EXTRA_DAYS");


      aSwitch = (Switch) findViewById(R.id.switch1);
      listView = (ListView) findViewById(R.id.WorkersList2);
      sortS = (Spinner) findViewById(R.id.sortSpinner);
      final List<String> sort = new ArrayList<String>();
      sort.add("ترتيب حسب: السعر");
      sort.add("ترتيب حسب: التقييم");
      ArrayAdapter<String> sortA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sort);

      sortS.setAdapter(sortA);
      workersList      = new ArrayList<>();
      AvailabilWorkers = new ArrayList<>();
      PriceWorkers     = new ArrayList<>();
      RatingWorkers    = new ArrayList<>();
      currentList      = new ArrayList<>();

      myRef.child("Worker").addValueEventListener(new ValueEventListener() {

          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {


              for (DataSnapshot child : dataSnapshot.getChildren()) {


                  Worker worker =  new Worker(child.getValue(Worker.class));

                  workersList.add(worker);

              }
              currentList = sortByPrice(workersList);
              listView.setAdapter(new WorkerAdapter(EmpViewWorkersActivity.this, currentList));

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Toast.makeText(EmpViewWorkersActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

          }

      });




aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            for(Worker w : currentList){
                if(w.isAvailable()){
                    AvailabilWorkers.add(w);
                }
            }
            currentList = AvailabilWorkers;
            listView.setAdapter(new WorkerAdapter(EmpViewWorkersActivity.this, currentList));

        }
        else if(!isChecked){

          currentList = workersList;
          listView.setAdapter(new WorkerAdapter(EmpViewWorkersActivity.this, currentList));
        }

    }


});


sortS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(aSwitch.isChecked()){
            currentList = AvailabilWorkers;
        }else if(!aSwitch.isChecked()){
            currentList = workersList;
        }

        if(sortS.getSelectedItem().equals(sort.get(0)) ){ //Price

        currentList = sortByPrice(currentList);
            listView.setAdapter(new WorkerAdapter(EmpViewWorkersActivity.this, currentList));
        }

        else
        if (sortS.getSelectedItem().equals(sort.get(1))){  //Rating

            currentList = sortByRating(currentList);
            listView.setAdapter(new WorkerAdapter(EmpViewWorkersActivity.this, currentList));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});




      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(EmpViewWorkersActivity.this, WorkerProfileActivity.class);

              intent.putExtra("id",currentList.get(position).getID());
              intent.putExtra("month",months);
              intent.putExtra("day",days);

              startActivity(intent);

          }
      });



  }
    public ArrayList<Worker> sortByPrice(ArrayList<Worker> a){

        Collections.sort(a, new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {

                if (o1.getPrice() ==
                        o2.getPrice())
                {
                    return 0;
                }
                else if (o1.getPrice() >
                        o2.getPrice())
                {
                    return 1;
                }
                return -1;
            }
        });

  return a;}

    public ArrayList<Worker> sortByRating(ArrayList<Worker> a){

        Collections.sort(a, new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {

                float fo1 = o1.getTotalRate()/o1.getnRate();
                float fo2 = o2.getTotalRate()/o2.getnRate();

                if ( fo1 == fo2)
                {
                    return 0;
                }
                else if (fo1 > fo2)
                {
                    return -1;
                }
                return 1;
            }
        });

        return a;}



}