package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String workerID = extras.getString("workerID");

        final EditText Feed1 = (EditText) findViewById(R.id.Feed);
        final Button addFeed = (Button) findViewById(R.id.addFeedback2);


        addFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackActivity.this, EmpContractViewActivity.class);
                DBAccess dbAccess = new DBAccess();
                String Feedback1 = Feed1.getText().toString();
                Feedback f = new Feedback(workerID,Feedback1);

                dbAccess.insertFeedback(f);

                startActivity(intent);
            }
        });
    }
}
