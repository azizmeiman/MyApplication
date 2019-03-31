package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkerC {




    String endDate ;
    boolean busy = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference myRef2 = database.getReference();


public void getWorkers(){
    final ArrayList<Worker> workersList = new ArrayList<>();

    myRef.child("Worker").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot child : dataSnapshot.getChildren()) {


                Worker worker =  new Worker(child.getValue(Worker.class));
                isAvailableMethod(worker);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

        }


    public void isAvailableMethod(final Worker worker){




        myRef.child("Contract").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                long numOfChild = dataSnapshot.getChildrenCount();
                        Date current = new Date();

                long i =0;
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Date  start = new Date(), end = new Date();

                    i++;
                    Contract c = new Contract(child.getValue(Contract.class));
                    String sStart = c.getStartDate();
                    String sEnd = c.getEndDate();


                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterS = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                        start = formatterS.parse(sStart);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterE = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                        end = formatterE.parse(sEnd);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(worker.getID().equals(c.getWorkerID()) && end.after(current) && start.before(current)){

                        endDate = sEnd ;
                        busy = true;
                        myRef2.child("Worker").child(worker.getID()).child("available").setValue(false);
                        String s = c.getEndDate()+"غير متاح حتى";
                        myRef2.child("Worker").child(worker.getID()).child("until").setValue(s);
                        worker.setAvailable(false);
                        worker.setUntil(c.getEndDate());
                        return;
                    }if(i==numOfChild) {
                        myRef2.child("Worker").child(worker.getID()).child("available").setValue(true);
                        myRef2.child("Worker").child(worker.getID()).child("until").setValue("متاح");
                        return;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}





