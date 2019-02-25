package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EmployerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_main);

        ImageButton sw = (ImageButton)findViewById(R.id.searchForWorker);

        sw.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intentSearch = new Intent(EmployerMainActivity.this, searchForWorkerActivity.class);
                startActivity(intentSearch);
            }


        });

    }
    }

