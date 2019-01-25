package com.example.abdulaziz.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBAccess {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    public DBAccess() {

    }

    public void insertEmployer(Employer e) {
        myRef = database.getReference("Employer");
        myRef.push().setValue(e);
    }

    public void insertWorkerPoster(WorkerPoster wp) {
        myRef = database.getReference("WorkerPoster");
        myRef.push().setValue(wp);
    }

    public void insetWorker(Worker w) {
        myRef = database.getReference("Worker");
        myRef.push().setValue(w);
    }

    public void insertSkill(Skill s) {
        myRef = database.getReference("Skill");
        myRef.push().setValue(s);
    }

    public void insertCity(City c) {
        myRef = database.getReference("City");
        myRef.push().setValue(c);
    }

    public void insertContract(Contract c) {
        myRef = database.getReference("Contract");
        myRef.push().setValue(c);
    }

    public void deleteEmployer(String id) {
        myRef = database.getReference("Employer");
        myRef.child(id);
        myRef.setValue(null);
    }

    public void deleteWorkerPoster(String id) {
        myRef = database.getReference("WorkerPoster");
        myRef.child(id);
        myRef.setValue(null);
    }

    public void deleteWorker(String id) {
        myRef = database.getReference("Worker");
        myRef.child(id);
        myRef.setValue(null);
    }

    public void deleteSkill(String id) {
        myRef = database.getReference("Skill");
        myRef.child(id);
        myRef.setValue(null);
    }

    public void deleteCity(String id) {
        myRef = database.getReference("City");
        myRef.child(id);
        myRef.setValue(null);
    }

    public void deleteContract(String id) {
        myRef = database.getReference("Contract");
        myRef.child(id);
        myRef.setValue(null);
    }


}
