package com.example.abdulaziz.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegExpEmp extends AppCompatActivity {


    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser updateUI;
    String Email ;
    String password ;
    String RPname ;
    String RPIDemp ;
    String RPphoneNumemp ;
    int cityIDemp;
    String googleMapLocemp ;  //the URL of Google map location
    String orgNameemp ;
    String orgDocemp ;
    String bioemp ;
    String orgPicemp ; //the URL of the pic




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_exp_emp);

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailE = (EditText)findViewById(R.id.EmailE);
        final EditText PasswordE = (EditText)findViewById(R.id.PasswordE);
        final EditText nameemp = (EditText)findViewById(R.id.nameemp);
        final EditText RPID = (EditText)findViewById(R.id.RPID);
        final EditText RPphoneNum = (EditText)findViewById(R.id.RPphoneNum);
        final EditText cityID = (EditText)findViewById(R.id.cityID);
        final EditText googleMapLoc = (EditText)findViewById(R.id.googleMapLoc);
        final EditText orgName = (EditText)findViewById(R.id.orgName);
        final EditText orgDoc = (EditText)findViewById(R.id.orgDoc);
        final EditText bio = (EditText)findViewById(R.id.bio);
        final EditText orgPic = (EditText)findViewById(R.id.orgPic);




        Button Reg = (Button)findViewById(R.id.RegE);




        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = EmailE.getText().toString();
                password = PasswordE.getText().toString();
                RPname = nameemp.getText().toString();
                RPIDemp= RPID.getText().toString();
                RPphoneNumemp = RPphoneNum.getText().toString();
                cityIDemp = cityID.getText().length();
                googleMapLocemp = googleMapLoc.getText().toString();
                orgNameemp = orgName.getText().toString();
                orgDocemp = orgDoc.getText().toString();
                bioemp = bio.getText().toString();
                orgPicemp = orgPic.getText().toString();


                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Employer emp = new Employer(
                                    Email,
                                    password,
                                    RPname,
                                    RPIDemp,
                                    RPphoneNumemp,
                                    cityIDemp,
                                    googleMapLocemp,
                                    orgNameemp,
                                    orgDocemp,
                                    bioemp,
                                    orgPicemp


                            );

                            FirebaseDatabase.getInstance().getReference("Employer")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(emp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   // if (task.isSuccessful()) {
                                        Toast.makeText(RegExpEmp.this,"Registration Done!",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                 //       else {
                                   //     //display a failure message
                                    //}
                                //}
                            });

                        } else {
                            Toast.makeText(RegExpEmp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }


        });

}
}
