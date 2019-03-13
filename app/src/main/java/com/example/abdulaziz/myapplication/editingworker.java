package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class editingworker extends AppCompatActivity {

    ArrayList<Worker> workersList;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editingworker);

        final EditText WorkerNameEdit = (EditText) findViewById(R.id.WorkerNameedit);
        final EditText WorkerIDEdit = (EditText) findViewById(R.id.WorkerIDedit);
        final EditText WorkerMobileEdit = (EditText) findViewById(R.id.WorkerMobileedit);
        final EditText WorkerNationalityEdit = (EditText) findViewById(R.id.WorkerNationalityedit);
        final EditText WorkerCityEdit = (EditText) findViewById(R.id.WorkerCityedit);
        final EditText WorkerBDateEdit = (EditText) findViewById(R.id.WorkerBDateedit);
        final EditText WorkerSkillsEdit = (EditText) findViewById(R.id.WorkerSkillsedit);
        final EditText WorkerFeesEdit = (EditText) findViewById(R.id.WorkerFeesedit);
        final EditText WorkerIncomeEdit = (EditText) findViewById(R.id.WorkerIncomeedit);


        final Button edit = (Button) findViewById(R.id.editworkerBut);

        final int i = getIntent().getIntExtra("id", 0);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


        workersList = new ArrayList<>();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userUID = currentUser.getUid();


        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    for (DataSnapshot c : dataSnapshot.getChildren()) {
                        if (userUID.equals(child.child("posterID").getValue().toString())) {
                            if (child.child("deleted").getValue().toString().equals("false")) {
                                Worker worker = new Worker(child.getValue(Worker.class));
                                workersList.add(worker);
                                break;

                            } else
                                break;
                        }
                    }


                }
                final Worker w1 = workersList.get(i);


                WorkerNameEdit.append(w1.getName());
                WorkerIDEdit.append(w1.getWorkerID());
                WorkerMobileEdit.append(w1.getPhonenumber());
                WorkerNationalityEdit.append(w1.getNationality());
                WorkerCityEdit.append(w1.getCity());
                WorkerBDateEdit.append(w1.getBirthDate());
                WorkerSkillsEdit.append(w1.getSkills());
                String price = String.valueOf(w1.getPrice());
                String income = String.valueOf(w1.getTotalIncome());
                WorkerFeesEdit.append(price);
                WorkerIncomeEdit.append(income);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String workerName = WorkerNameEdit.getText().toString();
                        String WorkerID = WorkerIDEdit.getText().toString();
                        String workermobile = WorkerMobileEdit.getText().toString();
                        String workerNat = WorkerNationalityEdit.getText().toString();
                        String workercity = WorkerCityEdit.getText().toString();
                        String workerbdate = WorkerBDateEdit.getText().toString();
                        String workerskills = WorkerSkillsEdit.getText().toString();
                        int workerfee = Integer.parseInt(WorkerFeesEdit.getText().toString());
                        int totalIncome = Integer.parseInt(WorkerIncomeEdit.getText().toString());

                        w1.setName(workerName);
                        w1.setWorkerID(WorkerID);
                        w1.setPhonenumber(workermobile);
                        w1.setNationality(workerNat);
                        w1.setCity(workercity);
                        w1.setBirthDate(workerbdate);
                        w1.setSkills(workerskills);
                        w1.setPrice(workerfee);
                        w1.setTotalIncome(totalIncome);


                        myRef.child("Worker").child(w1.getID()).setValue(w1); finish();
                    }


                });

            }

                @Override
                public void onCancelled (DatabaseError databaseError){
                    Toast.makeText(editingworker.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

                }


        });

    }
}
