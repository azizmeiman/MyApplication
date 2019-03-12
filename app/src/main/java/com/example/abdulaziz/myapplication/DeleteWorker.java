package com.example.abdulaziz.myapplication;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DeleteWorker extends AppCompatActivity {


    private ListView listView;
    private WorkerAdapter wAdapter;
    private ArrayList<Worker> workersList;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_worker);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


        listView = (ListView) findViewById(R.id.workerdeletelist);
        workersList = new ArrayList<>();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();


        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for (DataSnapshot c : dataSnapshot.getChildren()) {
                        if (userUID.equals(child.child("posterID").getValue().toString())) {
                            Worker worker = new Worker(child.getValue(Worker.class));
                            workersList.add(worker);
                            break;

                        } else
                            Toast.makeText(DeleteWorker.this, "لم تقم بإضافة عامل", Toast.LENGTH_SHORT).show();

                    }


                }
                wAdapter = new WorkerAdapter(DeleteWorker.this, workersList);
                listView.setAdapter(wAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DeleteWorker.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {

                    AlertDialog myQuittingDialogBox =new AlertDialog.Builder(DeleteWorker.this)
                            //set message, title, and icon
                            .setTitle("Delete")
                            .setMessage("Do you want to Delete")


                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) { // All the work of the method is here "the funcation of the class" ,  the others is just for decorations

                                    Worker w = workersList.get(position);
                                    myRef.child("Worker").child(w.getID()).child("deleted").setValue(true);
                                    Toast.makeText(DeleteWorker.this, w.getName()+" is Deleted", Toast.LENGTH_LONG).show();


                                    dialog.dismiss();
                                }


                            })



                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();

                                }
                            })
                            .create();

                AlertDialog diaBox = myQuittingDialogBox;
                diaBox.show();





            }});
    }


}


