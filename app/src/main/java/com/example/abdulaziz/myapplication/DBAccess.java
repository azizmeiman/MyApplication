package com.example.abdulaziz.myapplication;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
//        myRef = database.getReference("Worker");
//        myRef.push().setValue(w);
//        FirebaseDatabase.getInstance().getReference("Worker")
//        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//        .setValue(w);
        myRef.child("Worker").push().setValue(w);
        String key = myRef.getKey();

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

    public void insertFeedback(Feedback f) {
        myRef = database.getReference("Feedback");
        myRef.push().setValue(f);
    }

    public void insertRequest(Request r) {
        myRef = database.getReference("Request");
        myRef.push().setValue(r);
    }

    public void insertPaymentRecord(PaymentRecord p) {
        myRef = database.getReference("PaymentRecord");
        myRef.push().setValue(p);
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

    public void deleteRequest(String id) {

        myRef = database.getReference("Request");
        myRef.child(id).setValue(null);

    }
//
//    public void totalFees(String id,double newContractPrice) {
//        double newfees = newContractPrice*0.01;
//        double currentfees;
//        String posterID = id;
//        currentfees = CurrentFees(id);
//        double newTotalFees = currentfees + newfees;
//        myRef = database.getReference("WorkerPoster");
//        myRef.child(id).child("systemfees").setValue(currentfees);
//
//    }


    public void totalFees(final String UID, final double newContractPrice) {

       myRef.child("WorkerPoster").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (UID.equals(child.child("idp").getValue().toString())) {

                        WorkerPoster WP = new WorkerPoster(child.getValue(WorkerPoster.class));
                        double totalcurrentfees =  WP.getSystemfees();
                        myRef = database.getReference("WorkerPoster");
                        double totalfee = (newContractPrice*0.01)+totalcurrentfees;

                        myRef.child(UID).child("systemfees").setValue(totalfee);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}