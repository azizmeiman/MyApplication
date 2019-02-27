package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class searchForWorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_worker);


        Spinner cityID =  (Spinner) findViewById(R.id.citySpinner);
        List<String> cityL = new ArrayList<String>();
        cityL.add("Riyadh");
        cityL.add("Dammam");
        cityL.add("Jeddah");

        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityL);
        cityID.setAdapter(citites);



        Spinner skillID =  (Spinner) findViewById(R.id.skillsSpinner);
        List<String> skillL = new ArrayList<String>();
        cityL.add("حراثة");
        cityL.add("تلقيح النخل");
        cityL.add("حصاد");

        ArrayAdapter<String> skill = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skillL);
        cityID.setAdapter(skill);



        Spinner month =  (Spinner) findViewById(R.id.monthSpinner);
        List<String> monthL = new ArrayList<String>();
        cityL.add("01 Month");
        cityL.add("02 Months");
        cityL.add("03 Months");
        cityL.add("04 Months");
        cityL.add("05 Months");
        cityL.add("06 Months");
        cityL.add("07 Month");
        cityL.add("08 Months");
        cityL.add("09 Months");
        cityL.add("10 Month");
        cityL.add("11 Months");
        cityL.add("12 Months");

        ArrayAdapter<String> months = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monthL);
        cityID.setAdapter(months);



        Spinner day =  (Spinner) findViewById(R.id.daySpinner);
        List<String> dayL = new ArrayList<String>();
        cityL.add("01 day");
        cityL.add("02 days");
        cityL.add("03 days");
        cityL.add("04 days");
        cityL.add("05 days");
        cityL.add("06 days");
        cityL.add("07 days");
        cityL.add("08 days");
        cityL.add("09 days");
        cityL.add("10 days");
        cityL.add("11 days");
        cityL.add("12 days");
        cityL.add("13 days");
        cityL.add("14 days");
        cityL.add("15 days");
        cityL.add("16 days");
        cityL.add("17 days");
        cityL.add("18 days");
        cityL.add("19 days");
        cityL.add("20 days");
        cityL.add("21 days");
        cityL.add("22 days");
        cityL.add("23 days");
        cityL.add("24 days");
        cityL.add("25 days");
        cityL.add("26 days");
        cityL.add("27 days");
        cityL.add("28 days");
        cityL.add("29 days");
        cityL.add("30 days");

        ArrayAdapter<String> days = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayL);
        cityID.setAdapter(days);


    }
}
