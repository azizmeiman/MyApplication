package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class searchForWorkerActivity extends AppCompatActivity {

    private ArrayAdapter citites;
    private ArrayAdapter skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_worker);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        final Button listAll = (Button) findViewById(R.id.listAllWorkers);
        final Button search = (Button) findViewById(R.id.Search);

        final Spinner cityID =  (Spinner) findViewById(R.id.citySpinner);
        final List<String> cityL = new ArrayList<String>();

        myRef.child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cityL.add(child.getValue(String.class));
                }
                citites = new ArrayAdapter<String>(searchForWorkerActivity.this, android.R.layout.simple_list_item_1, cityL);
                cityID.setAdapter(citites);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









       final Spinner month =  (Spinner) findViewById(R.id.monthSpinner);
        List<String> monthL = new ArrayList<String>();

     monthL.add("00 Month");
     monthL.add("01 Month");
     monthL.add("02 Months");
     monthL.add("03 Months");
     monthL.add("04 Months");
     monthL.add("05 Months");
     monthL.add("06 Months");
     monthL.add("07 Months");
     monthL.add("08 Months");
     monthL.add("09 Months");
     monthL.add("10 Months");
     monthL.add("11 Months");
     monthL.add("12 Months");

        ArrayAdapter<String> months = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monthL);
        month.setAdapter(months);



        final Spinner day =  (Spinner) findViewById(R.id.daySpinner);
        List<String> dayL = new ArrayList<String>();
        dayL.add("00 day");
        dayL.add("01 day");
     dayL.add("02 days");
     dayL.add("03 days");
     dayL.add("04 days");
     dayL.add("05 days");
     dayL.add("06 days");
     dayL.add("07 days");
     dayL.add("08 days");
     dayL.add("09 days");
     dayL.add("10 days");
     dayL.add("11 days");
     dayL.add("12 days");
     dayL.add("13 days");
     dayL.add("14 days");
     dayL.add("15 days");
     dayL.add("16 days");
     dayL.add("17 days");
     dayL.add("18 days");
     dayL.add("19 days");
     dayL.add("20 days");
     dayL.add("21 days");
     dayL.add("22 days");
     dayL.add("23 days");
     dayL.add("24 days");
     dayL.add("25 days");
     dayL.add("26 days");
     dayL.add("27 days");
     dayL.add("28 days");
     dayL.add("29 days");
     dayL.add("30 days");

        ArrayAdapter<String> days = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayL);
        day.setAdapter(days);













        final Spinner skillID =  (Spinner) findViewById(R.id.skillsSpinner);
        final List<String> skillL = new ArrayList<String>();

        myRef.child("Skills").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    skillL.add(child.getValue(String.class));
                }
                skill = new ArrayAdapter<String>(searchForWorkerActivity.this, android.R.layout.simple_list_item_1, skillL);
                skillID.setAdapter(skill);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        listAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWorker = new Intent(searchForWorkerActivity.this, EmpViewWorkersActivity.class);
                int monthI,dayI;
                monthI=0; dayI=0;
                String cityI="";
                String skillI="";
                // String skillsArr[] = new String[6];
                String dayString = (String)day.getSelectedItem();
                String monthString = (String)month.getSelectedItem();

                dayString = dayString.substring(0,2);
                monthString = monthString.substring(0,2);

                dayI = Integer.parseInt(dayString);
                monthI = Integer.parseInt(monthString);

                Bundle extras = new Bundle();
                extras.putInt("EXTRA_DAYS",dayI);
                extras.putInt("EXTRA_MONTHS",monthI);

                intentWorker.putExtras(extras);
                startActivity(intentWorker);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearchResult = new Intent(searchForWorkerActivity.this, SearchResultActivity.class);
               int monthI,dayI;
               monthI=0; dayI=0;
               String cityI="";
               String skillI="";
               // String skillsArr[] = new String[6];
                String dayString = (String)day.getSelectedItem();
                String monthString = (String)month.getSelectedItem();

                dayString = dayString.substring(0,2);
                monthString = monthString.substring(0,2);

                dayI = Integer.parseInt(dayString);
                monthI = Integer.parseInt(monthString);

                cityI = (String) cityID.getSelectedItem();
                skillI = (String) skillID.getSelectedItem();

                Bundle extras = new Bundle();
                extras.putInt("EXTRA_DAYS",dayI);
                extras.putInt("EXTRA_MONTHS",monthI);
                extras.putString("EXTRA_CITY",cityI);
                extras.putString("EXTRA_SKILL",skillI);
               // extras.putStringArray("EXTRA_SKILLS",skillsArr);

                intentSearchResult.putExtras(extras);

                startActivity(intentSearchResult);
            }
        });
    }
}
