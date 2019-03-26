package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class workerposterreports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerposterreports);
        final Button HighestRate = (Button) findViewById(R.id.workerpostertoprate);
        final Button LowestRate = (Button) findViewById(R.id.workerposterbadrate);
        final Button HighestIncome = (Button) findViewById(R.id.workerpostehighesticome);
        final Button LowestIncome = (Button) findViewById(R.id.workerposterlowesticome);
        //final Button pastcontracts = (Button) findViewById(R.id.pastContractsPoster);
        final Button systemfees = (Button)findViewById(R.id.systemfees);




        HighestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(workerposterreports.this,highestrateworkeposter.class);
                startActivity(intent);
        }
        });

        LowestRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(workerposterreports.this,lowestrateworkerposter.class);
                startActivity(intent);

            }
        });

        HighestIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (workerposterreports.this, highestincomeworkerposter.class);
                startActivity(intent);
            }
        });


        LowestIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (workerposterreports.this,lowestincomeworker.class);
                startActivity(intent);
            }
        });



        systemfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (workerposterreports.this, systemfeesworkerposter.class);
                startActivity(intent);
            }
        });



    }


}
