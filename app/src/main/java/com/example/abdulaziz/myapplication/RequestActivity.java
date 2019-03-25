package com.example.abdulaziz.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestActivity extends AppCompatActivity {

    EditText sDate;
    DatePickerDialog sDatePickerDialog;

    EditText eDate;
    DatePickerDialog eDatePickerDialog;
    String startDate;
    String endDate;
    String empName, posterName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference myRef2 = database.getReference();

    Employer emp = new Employer();
    WorkerPoster wp = new WorkerPoster();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String empID = user.getUid();

    private int startYear, startMonth, startDay, endYear, endMonth, endDay, startInDay, endInDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        final Button sendRequst = (Button)findViewById(R.id.sendRequst);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final String workerID = extras.getString("id");
        final int period = extras.getInt("period");
        final int totalPrice = extras.getInt("totalPrice");
        final String posterID = extras.getString("posterID");
        final String workerName = extras.getString("wName");
        final int workerPrice = extras.getInt("price");

        // initiate the date picker and a button
        sDate = (EditText) findViewById(R.id.startDate);
        // perform click event on edit text
        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int sYear = c.get(Calendar.YEAR); // current year
                int sMonth = c.get(Calendar.MONTH); // current month
                int sDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                sDatePickerDialog = new DatePickerDialog(RequestActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startYear = year*12;
                                startMonth = (monthOfYear+1)*30;
                                startDay = dayOfMonth;
                                startInDay = startYear+startMonth+startDay;
                               startDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                sDate.setText(startDate);

                            }
                        }, sYear, sMonth, sDay);

                sDatePickerDialog.show();
            }
        });

        // initiate the date picker and a button
        eDate = (EditText) findViewById(R.id.endDate);
        // perform click event on edit text
        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int eYear = c.get(Calendar.YEAR); // current year
                int eMonth = c.get(Calendar.MONTH); // current month
                int eDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                eDatePickerDialog = new DatePickerDialog(RequestActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                endYear = year*12;
                                endMonth = (monthOfYear+1)*30;
                                endDay = dayOfMonth;
                                endInDay = endYear+endMonth+endDay;
                                endDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                eDate.setText(endDate);

                            }
                        }, eYear, eMonth, eDay);


                eDatePickerDialog.show();
            }
        });

        myRef.child("Employer").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if (empID.equals(child.child("ide").getValue().toString())){
                        emp = child.getValue(Employer.class);
                    }

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RequestActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });




        sendRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RequestActivity.this,searchForWorkerActivity.class);

                DatabaseReference pushRef = myRef2.child("Request").push();
                String key_ID = pushRef.getKey();
              Request r;
              int p,t;
                if(period >1) {
                     r = new Request(key_ID, workerID, empID, posterID, workerName, emp.getOrgName(), period, startDate, endDate, totalPrice, 3);
                }else{
                    p = endInDay-startInDay;
                    t = p*workerPrice;
                    r = new Request(key_ID, workerID, empID, posterID, workerName, emp.getOrgName(), p, startDate, endDate, t, 3);}
                pushRef.setValue(r);
                Toast.makeText(RequestActivity.this,"تم إرسال الطلب",Toast.LENGTH_SHORT).show();
                startActivity(back);


            }
        });


    }
}
