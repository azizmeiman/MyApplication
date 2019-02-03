package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button EmpButton = (Button)findViewById(R.id.EmployerReg);
        Button PosterReg = (Button)findViewById(R.id.PosterReg);
        Button gotoLogin = (Button)findViewById(R.id.gotoLogin);


        PosterReg.setOnClickListener(new View.OnClickListener() {

            Intent intentPoster = new Intent(MainActivity.this, PosterRegisaration.class);

            @Override
            public void onClick(View v) {

                startActivity(intentPoster);
            }


        });


        EmpButton.setOnClickListener(new View.OnClickListener() {

            Intent intentEmp = new Intent(MainActivity.this,RegExpEmp.class);
            @Override
            public void onClick(View v) {

                startActivity(intentEmp);
            }
        });

        gotoLogin.setOnClickListener(new View.OnClickListener() {

            Intent intentPoster = new Intent(MainActivity.this, Login.class);

            @Override
            public void onClick(View v) {

                startActivity(intentPoster);
            }


        });

        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Employer");

        //Employer emp1 = new Employer(011,"Ahmed","123","khalid","000012","0566",01,"wwww.googlemap.com","MAC.co","www.com","hi there","http//:");
        //myRef.push().setValue(emp1);

        //DBAccess d = new DBAccess();
       // d.insertEmployer(emp1);

        //myRef.push("40","makkah","fahad");
        //myRef.child("city").push().setValue("makkah");
        //myRef.child("name").push().setValue("faha");
        //myRef.setValue("Hello, World!");



    }




}
