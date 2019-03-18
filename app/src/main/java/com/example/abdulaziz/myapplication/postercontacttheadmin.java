package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class postercontacttheadmin extends AppCompatActivity {

        private EditText mEditTextTo;
        private EditText mEditTextSubject;
        private EditText mEditTextMessage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_postercontacttheadmin);

            mEditTextSubject = findViewById(R.id.emailsubject);
            mEditTextMessage = findViewById(R.id.emailmassege);

            Button buttonSend = findViewById(R.id.sendemailbut);
            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendMail();
                }
            });
        }

        private void sendMail () {
            String recipientList = "info@aazer.epizy.com";

            String [] recipients = recipientList.split(",");

            String subject = mEditTextSubject.getText().toString();
            String message = mEditTextMessage.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        }

}