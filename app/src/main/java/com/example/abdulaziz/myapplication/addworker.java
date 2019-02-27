package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addworker extends AppCompatActivity {

    String WorkerName;
    String WorkerID;
    String WorkerMobile;
    String WorkerNationality;
    String WorkerBDDate;
    String WorkerSkills;
    int WorkerFees;
    String WorkerDocument;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworker);

        final EditText WorkerNameEdit = (EditText) findViewById(R.id.WorkerNameText);
        final EditText WorkerIDEdit = (EditText) findViewById(R.id.WorkerIDText);
        final EditText WorkerMobileEdit = (EditText) findViewById(R.id.WorkerMobileText);
        final EditText WorkerNationalityEdit = (EditText) findViewById(R.id.WorkerNationalityText);
        final EditText WorkerBDateEdit = (EditText) findViewById(R.id.WorkerBDateText);
        final EditText WorkerSkillsEdit = (EditText) findViewById(R.id.WorkerSkillsText);
        final EditText WorkerFeesEdit = (EditText) findViewById(R.id.WorkerFeesText);
        final EditText WorkerDocumEdit = (EditText) findViewById(R.id.WorkerDocumentText);
        final Button AddworkerBut = (Button) findViewById(R.id.addworkerBut);

        AddworkerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 WorkerName = WorkerNameEdit.getText().toString();
                 WorkerID = WorkerIDEdit.getText().toString();
                 WorkerMobile = WorkerMobileEdit.getText().toString();
                 WorkerNationality = WorkerNationalityEdit.getText().toString();
                 WorkerBDDate = WorkerBDateEdit.getText().toString();
                 WorkerSkills = WorkerSkillsEdit.getText().toString();
                 WorkerFees = Integer.parseInt(WorkerFeesEdit.getText().toString());
                 WorkerDocument = WorkerDocumEdit.getText().toString();


                 Worker worker = new Worker(WorkerName, WorkerID, WorkerFees, WorkerDocument, WorkerNationality, WorkerSkills, WorkerMobile, WorkerBDDate);
                 DBAccess dba = new DBAccess();
                 dba.insetWorker(worker);
                 Toast.makeText(addworker.this, "تمت إضافة العامل", Toast.LENGTH_SHORT).show();
                 finish();




            }
        });






    }
}
