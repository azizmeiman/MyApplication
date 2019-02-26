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

public class PosterRegisaration extends AppCompatActivity {


    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser updateUI;
    String Email;
    String password;
    String RPnamePoster;
    String RPIDPoster;
    String RPphoneNumPoster;
    String cityIDposter;
    String googleMapLocPoster;  //the URL of Google map location
    String orgNamePoster;
    String orgDocPoster;
    String bioPoster;
    String orgPicPoster; //the URL of the pic
    int Checkuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_regisaration);

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailP = (EditText) findViewById(R.id.EmailP);
        final EditText PasswordP = (EditText) findViewById(R.id.PasswordP);
        final EditText namep = (EditText) findViewById(R.id.namep);
        final EditText RPIDP = (EditText) findViewById(R.id.RPIDP);
        final EditText RPphoneNumP = (EditText) findViewById(R.id.RPphoneNumP);
        final EditText cityIDP = (EditText) findViewById(R.id.cityIDP);
        final EditText googleMapLocP = (EditText) findViewById(R.id.googleMapLocP);
        final EditText orgNameP = (EditText) findViewById(R.id.orgNameP);
        final EditText orgDocP = (EditText) findViewById(R.id.orgDocP);
        final EditText bioP = (EditText) findViewById(R.id.bioP);
        final EditText orgPicP = (EditText) findViewById(R.id.orgPicP);


        Button Reg = (Button) findViewById(R.id.RegE);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = EmailP.getText().toString();
                password = PasswordP.getText().toString();
                RPnamePoster = namep.getText().toString();
                RPIDPoster = RPIDP.getText().toString();
                RPphoneNumPoster = RPphoneNumP.getText().toString();
                cityIDposter = "";//We do after city list finish
                googleMapLocPoster = googleMapLocP.getText().toString();
                orgNamePoster = orgNameP.getText().toString();
                orgDocPoster = orgDocP.getText().toString();
                bioPoster = bioP.getText().toString();
                orgPicPoster = orgPicP.getText().toString();
                Checkuser = 3; //user number 3 is the workerposter
                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            WorkerPoster workerposter1 = new WorkerPoster(
                                    Email,
                                    password,
                                    RPnamePoster,
                                    RPIDPoster,
                                    RPphoneNumPoster,
                                    cityIDposter,
                                    googleMapLocPoster,
                                    orgNamePoster,
                                    orgDocPoster,
                                    bioPoster,
                                    orgPicPoster,
                                    Checkuser


                            );

                            FirebaseDatabase.getInstance().getReference("WorkerPoster")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(workerposter1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // if (task.isSuccessful()) {
                                    Toast.makeText(PosterRegisaration.this, "Registration Done!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                //       else {
                                //     //display a failure message
                                //}
                                //}
                            });

                        } else {
                            Toast.makeText(PosterRegisaration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }


        });

    }


}
