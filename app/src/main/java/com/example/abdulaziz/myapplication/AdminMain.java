package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        final Button ViewEmpButton = (Button) findViewById(R.id.ViewEmplyer);

        ViewEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewEmpIntent = new Intent(AdminMain.this, EmployerView.class );
                startActivity(ViewEmpIntent);
            }
        });

        final Button ViewposterButton = (Button) findViewById(R.id.ViewWorkerPoster);

        ViewposterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewposterIntent = new Intent(AdminMain.this, PosterView.class );
                startActivity(ViewposterIntent);
            }
        });


        final Button ViewConractButton = (Button) findViewById(R.id.ViewAllContract);

        ViewConractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewContractIntent = new Intent(AdminMain.this, ContractView.class );
                startActivity(ViewContractIntent);
            }
        });


    }
}
