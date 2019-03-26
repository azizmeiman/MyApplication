package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
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

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private Spinner sortS;
    private Switch aSwitch;
    private ArrayList<Worker> AvailabilWorkers;
    private ArrayList<Worker> PriceWorkers;
    private ArrayList<Worker> RatingWorkers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();





        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int months = extras.getInt("EXTRA_MONTHS");
        final int days = extras.getInt("EXTRA_DAYS");
        final String city = extras.getString("EXTRA_CITY");
        final String skill = extras.getString("EXTRA_SKILL");

        aSwitch = (Switch) findViewById(R.id.switch11);
        sortS = (Spinner) findViewById(R.id.sortSpinner1);
        final List<String> sort = new ArrayList<String>();
        sort.add("ترتيب حسب: السعر");
        sort.add("ترتيب حسب: التقييم");
        ArrayAdapter<String> sortA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sort);

        listView = (ListView) findViewById(R.id.searchResultList);
        sortS.setAdapter(sortA);
        workersList      = new ArrayList<>();
        AvailabilWorkers = new ArrayList<>();
        PriceWorkers     = new ArrayList<>();
        RatingWorkers    = new ArrayList<>();

        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    workersList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {


                        if(skill.equals(child.child("skills").getValue().toString()) && city.equals(child.child("city").getValue().toString())){

                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);
                                if(worker.isAvailable()){
                                    AvailabilWorkers.add(worker);
                                }

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

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if(sortS.getSelectedItem().equals(sort.get(0))){
                        AvailabilWorkers = sortByPrice(AvailabilWorkers);
                    }else if(sortS.getSelectedItem().equals(sort.get(1))){
                        AvailabilWorkers = sortByRating(AvailabilWorkers);

                    }
                    wAdapter = new WorkerAdapter(SearchResultActivity.this, AvailabilWorkers);
                    listView.setAdapter(wAdapter);

                }
                else{

                    if(sortS.getSelectedItem().equals(sort.get(0))){
                        workersList = sortByPrice(workersList);
                    }else if(sortS.getSelectedItem().equals(sort.get(1))){
                        workersList = sortByRating(workersList);

                    }
                    wAdapter = new WorkerAdapter(SearchResultActivity.this, workersList);
                    listView.setAdapter(wAdapter);
                }

            }


        });



        sortS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sortS.getSelectedItem().equals(sort.get(0)) ){ //Price

                    if(aSwitch.isChecked()){
                        PriceWorkers = sortByPrice(AvailabilWorkers);
                    }else PriceWorkers = sortByPrice(workersList);
                    wAdapter = new WorkerAdapter(SearchResultActivity.this, PriceWorkers);
                    listView.setAdapter(wAdapter);}

                else
                if (sortS.getSelectedItem().equals(sort.get(1))){  //Rating
                    if(aSwitch.isChecked()){
                        RatingWorkers = sortByPrice(AvailabilWorkers);
                    }else RatingWorkers = sortByPrice(workersList);
                    wAdapter = new WorkerAdapter(SearchResultActivity.this, RatingWorkers);
                    listView.setAdapter(wAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public ArrayList sortByPrice(ArrayList a){

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

    public ArrayList sortByRating(ArrayList a){

        Collections.sort(a, new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {

                if (o1.getTotalRate() ==
                        o2.getTotalRate())
                {
                    return 0;
                }
                else if (o1.getTotalRate() >
                        o2.getTotalRate())
                {
                    return -1;
                }
                return 1;
            }
        });

        return a;}


}
