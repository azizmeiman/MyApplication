package com.example.abdulaziz.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBAccess {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    public DBAccess(){

    }
    public void insertEmployer(Employer emp){
        myRef = database.getReference("Employer");
        myRef.push().setValue(emp);
    }

    public void inserWorkerPoster(WorkerPoster wp){
        myRef = database.getReference("WorkerPoster");
        myRef.push().setValue(wp);
    }

    public void insetWorker(Worker w){
        myRef = database.getReference("Worker");
        myRef.push().setValue(w);
    }
}
