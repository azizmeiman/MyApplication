package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class searchForWorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_worker);


       final Spinner cityID =  (Spinner) findViewById(R.id.citySpinner);
        final Button listAll = (Button) findViewById(R.id.listAllWorkers);
        final Button search = (Button) findViewById(R.id.Search);

        List<String> cityL = new ArrayList<String>();
        cityL.add("Riyadh");
        cityL.add("Dammam");
        cityL.add("Jeddah");

        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityL);
        cityID.setAdapter(citites);





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

        List<String> skillL = new ArrayList<String>();
     skillL.add("مزارع");
     skillL.add("ميكانيكي الآت");
     skillL.add("سباك");

        final ArrayAdapter<String> skill = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skillL);
        skillID.setAdapter(skill);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityS = cityID.getSelectedItem().toString();
                String monthS = month.getSelectedItem().toString();
                String dayS = day.getSelectedItem().toString();
                String skillS = skillID.getSelectedItem().toString();

                //Intent intentWorker = new Intent(searchForWorkerActivity.this, ViewWorkersActivity.class);


            }
        });


        listAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWorker = new Intent(searchForWorkerActivity.this, ViewWorkesActivity.class);
                startActivity(intentWorker);
            }
        });
    }
}
