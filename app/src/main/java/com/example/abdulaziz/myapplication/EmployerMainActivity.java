package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class EmployerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_main);


        ImageButton sw = (ImageButton)findViewById(R.id.searchForWorker);
        ImageButton vc = (ImageButton)findViewById(R.id.employerContracts);
        Button contactAdminBut = (Button) findViewById(R.id.button);


        WorkerC w = new WorkerC();
        w.getWorkers();


        sw.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intentSearch = new Intent(EmployerMainActivity.this, searchForWorkerActivity.class);
                startActivity(intentSearch);
            }


        });

        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContract = new Intent(EmployerMainActivity.this, EmpContractViewActivity.class);
                startActivity(intentContract);
            }
        });

        contactAdminBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdminContact = new Intent(EmployerMainActivity.this, employer_contact_admin.class);
                startActivity(AdminContact);
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
                Intent intent = new Intent(EmployerMainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }

