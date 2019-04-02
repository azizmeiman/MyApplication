package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class workerposterreports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerposterreports);
        final ImageButton HighestRate = (ImageButton) findViewById(R.id.workerpostertoprate);
        final ImageButton LowestRate = (ImageButton) findViewById(R.id.workerposterbadrate);
        final ImageButton HighestIncome = (ImageButton) findViewById(R.id.workerpostehighesticome);
        final ImageButton LowestIncome = (ImageButton) findViewById(R.id.workerposterlowesticome);
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
