package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reports);


        final Button mostCont = (Button) findViewById(R.id.highestContP);

        mostCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostPosterCONT = new Intent(AdminReports.this, highestContractPoster.class );
                startActivity(MostPosterCONT);
            }
        });

        final Button mostContE = (Button) findViewById(R.id.highestContE);

        mostContE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostEmpCONT = new Intent(AdminReports.this, highestContractEmp.class );
                startActivity(MostEmpCONT);
            }
        });

        final Button mostbalanc = (Button) findViewById(R.id.highestBalaceP);


        mostbalanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostEmpCONT = new Intent(AdminReports.this, highestBalancePoster.class );
                startActivity(MostEmpCONT);
            }
        });





    }
}
