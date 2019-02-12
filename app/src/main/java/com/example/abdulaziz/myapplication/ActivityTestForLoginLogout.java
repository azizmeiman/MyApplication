package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityTestForLoginLogout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_login_logout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if (id==R.id.itemLogout){
            FirebaseAuth.getInstance().signOut();

            Intent intentlogout = new Intent(ActivityTestForLoginLogout.this, MainActivity.class);
            startActivity(intentlogout
            );
        }
        return true;
    }


}
