package com.example.abdulaziz.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;


public class WorkerPosterMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_poster_main);

        WorkerC w = new WorkerC();
        w.getWorkers();

        final ImageButton ViewWorkerButton = (ImageButton) findViewById(R.id.ViewWorkerBut);
        final ImageButton AddWorkerButton = (ImageButton) findViewById(R.id.AddWorkerBut);
        final ImageButton EditWorkerButton = (ImageButton) findViewById(R.id.EditWorkerBut);
        final ImageButton ViewRequestButton = (ImageButton) findViewById(R.id.ViewRequestBut);
        final ImageButton ViewContractButton = (ImageButton) findViewById(R.id.ViewContractBut);
        final ImageButton DeleteWorkerButton = (ImageButton) findViewById(R.id.DeleteWorkerBut);
        final Button ReportsButton = (Button) findViewById(R.id.ReportsBut);
        final Button ChatwithAdminButton = (Button) findViewById(R.id.ChatwithAdminBut);

        ViewWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewWorkerIntent = new Intent(WorkerPosterMain.this, ViewWorkesActivity.class );
                startActivity(ViewWorkerIntent);
            }
        });

        AddWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddWorkerIntent = new Intent(WorkerPosterMain.this, addworker.class );
                startActivity(AddWorkerIntent);
            }
        });

        EditWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditWorkerIntent = new Intent(WorkerPosterMain.this, editworkers.class );
                startActivity(EditWorkerIntent);
            }
        });

        DeleteWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DeleteWorkerIntent = new Intent(WorkerPosterMain.this, DeleteWorker.class );
                startActivity(DeleteWorkerIntent);
            }
        });


        ReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ReportWorkerPosterIntent = new Intent(WorkerPosterMain.this, workerposterreports.class );
                startActivity(ReportWorkerPosterIntent);
            }
        });


        ViewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRequest = new Intent(WorkerPosterMain.this,ViewRequestsActivity.class);
                startActivity(viewRequest);
            }
        });


        ChatwithAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmail = new Intent(WorkerPosterMain.this,postercontacttheadmin.class);
                startActivity(sendEmail);
            }
        });

        ViewContractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CurContract = new Intent(WorkerPosterMain.this, poster_contract_view.class);
                startActivity(CurContract);
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(WorkerPosterMain.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
