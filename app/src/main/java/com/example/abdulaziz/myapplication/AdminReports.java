package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reports);


        final ImageButton mostCont = (ImageButton) findViewById(R.id.highestContP);

        mostCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostPosterCONT = new Intent(AdminReports.this, highestContractPoster.class );
                startActivity(MostPosterCONT);
            }
        });

        final ImageButton mostContE = (ImageButton) findViewById(R.id.highestContE);

        mostContE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostEmpCONT = new Intent(AdminReports.this, highestContractEmp.class );
                startActivity(MostEmpCONT);
            }
        });

        final ImageButton mostbalanc = (ImageButton) findViewById(R.id.highestBalaceP);


        mostbalanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostEmpCONT = new Intent(AdminReports.this, highestBalancePoster.class );
                startActivity(MostEmpCONT);
            }
        });





    }
}
