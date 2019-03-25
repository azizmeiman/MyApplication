package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        final Button ViewEmpButton = (Button) findViewById(R.id.ViewEmplyer);

        ViewEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewEmpIntent = new Intent(AdminMain.this, EmployerView.class );
                startActivity(ViewEmpIntent);
            }
        });

        final Button ViewposterButton = (Button) findViewById(R.id.ViewWorkerPoster);

        ViewposterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewposterIntent = new Intent(AdminMain.this, PosterView.class );
                startActivity(ViewposterIntent);
            }
        });


        final Button ViewConractButton = (Button) findViewById(R.id.ViewAllContract);

        ViewConractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewContractIntent = new Intent(AdminMain.this, ContractView.class );
                startActivity(ViewContractIntent);
            }
        });
        final Button reportsAdmin = (Button) findViewById(R.id.reports);

        reportsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewReportsIntent = new Intent(AdminMain.this, AdminReports.class );
                startActivity(ViewReportsIntent);
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
                Intent intent = new Intent(AdminMain.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
