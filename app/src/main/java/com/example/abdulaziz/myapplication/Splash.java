package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent intent = new Intent(this, MainActivity.class);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            startActivity(intent);

            }
        };
        Timer splash =new Timer() ;
        splash.schedule(task,7000);

    }
}
