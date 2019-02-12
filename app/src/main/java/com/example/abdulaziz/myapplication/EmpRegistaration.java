package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmpRegistaration extends AppCompatActivity {

     EditText OrgNameEmp;
     EditText UserNameEmp;
     EditText EmailEmp;
     EditText MPhoneEmp;
     EditText PasswordEmp;
     EditText CityEmp;
     EditText LocationEmp;
     EditText IDEmp;
     EditText BioEmp;
     Button EmpRegisterButton;
     TextView SignInViewEmp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empregistaration);

         OrgNameEmp = (EditText)findViewById(R.id.OrgNameEmp);
         UserNameEmp = (EditText)findViewById(R.id.UserNameEmp);
         EmailEmp = (EditText)findViewById(R.id.EmailEmp);
         MPhoneEmp = (EditText)findViewById(R.id.MPhoneEmp);
         PasswordEmp = (EditText)findViewById(R.id.PasswordEmp);
         CityEmp = (EditText)findViewById(R.id.CityEmp);
         LocationEmp = (EditText)findViewById(R.id.LocationEmp);
         IDEmp = (EditText)findViewById(R.id.IDEmp);
         BioEmp = (EditText)findViewById(R.id.BioEmp);

        EmpRegisterButton = (Button) findViewById(R.id.EmpRegisterButton);
         SignInViewEmp = (TextView) findViewById(R.id.SignInViewEmp);

           final Employer emp = new Employer();
//         String EmpID= IDEmp.getText().toString();
//         int EmpIDInt=Integer.parseInt(EmpID);
//
//         String CityID= IDEmp.getText().toString();
//         int CityIDInt=Integer.parseInt(CityID);


             emp.setUserName(UserNameEmp.getText().toString());
            emp.setPassword(PasswordEmp.getText().toString());
            emp.setRPname("Test");
            emp.setRPID("1000Test");
            emp.setRPphoneNum("05050505Test");
            emp.setCityID(1);
            emp.setGoogleMapLoc(LocationEmp.getText().toString());
            emp.setOrgName(OrgNameEmp.getText().toString());
            emp.setOrgDoc("URLTest");
            emp.setBio("For Testing");
            emp.setOrgPic("Only For test");

            EmpRegisterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBAccess dba = new DBAccess();
                    dba.insertEmployer(emp);

                }
            });


    }
}
