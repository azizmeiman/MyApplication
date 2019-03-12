package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WorkerPosterMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_poster_main);

        final Button ViewWorkerButton = (Button) findViewById(R.id.ViewWorkerBut);
        final Button AddWorkerButton = (Button) findViewById(R.id.AddWorkerBut);
        final Button EditWorkerButton = (Button) findViewById(R.id.EditWorkerBut);
        final Button ViewRequestButton = (Button) findViewById(R.id.ViewRequestBut);
        final Button ViewContractButton = (Button) findViewById(R.id.ViewContractBut);
        final Button DeleteWorkerButton = (Button) findViewById(R.id.DeleteWorkerBut);
        final Button ReportsButton = (Button) findViewById(R.id.ReportsBut);
        final Button ChatwithAdminButton = (Button) findViewById(R.id.ChatwithAdminBut);

        AddWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddWorkerIntent = new Intent(WorkerPosterMain.this, addworker.class );
                startActivity(AddWorkerIntent);
            }
        });

        ViewWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewWorkerIntent = new Intent(WorkerPosterMain.this, ViewWorkesActivity.class );
                startActivity(ViewWorkerIntent);
            }
        });

        DeleteWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DeleteWorkerIntent = new Intent(WorkerPosterMain.this, DeleteWorker.class );
                startActivity(DeleteWorkerIntent);
            }
        });

        





    }
}
