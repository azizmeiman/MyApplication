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
    }
}
