package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Employer");

        Employer emp1 = new Employer(011,"Ahmed","123","khalid","000012","0566",01,"wwww.googlemap.com","MAC.co","www.com","hi there","http//:");
        //myRef.push().setValue(emp1);

        DBAccess d = new DBAccess();
        d.insertEmployer(emp1);

        //myRef.push("40","makkah","fahad");
        //myRef.child("city").push().setValue("makkah");
        //myRef.child("name").push().setValue("faha");
        //myRef.setValue("Hello, World!");



    }




}
