package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin_chat_with_poster extends AppCompatActivity {


    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chat_with_poster);
        mEditTextSubject = findViewById(R.id.postemailsubjectemp);
        mEditTextMessage = findViewById(R.id.postemailmassegeemp);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String PosterName = extras.getString("Name");
        final String PosterEmail = extras.getString("Email");
        final String PosterFees = extras.getString("Fees");

        mEditTextSubject.setText("الرجاء دفع رسوم التطبيق");
        mEditTextMessage.setText("آهلا " + PosterName +  " \n " + " \n " +  "الرجاء دفع رسوم التطبيق والمقدرة بمبلغ  " + PosterFees + " ريال "+ " \n " + " \n " + " وشكرا جزيلا"  + " \n " + "فريق تطبيق آزر");

        Button buttonSend = findViewById(R.id.postsendemailbutemp);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipientList = PosterEmail;

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
            });
        }


}
