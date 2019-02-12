package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PosterRegisaration extends AppCompatActivity {


    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser updateUI;
    String Email ;
    String password ;
    String RPname ;
    String RPID ;
    String RPphoneNum ;
     int cityID;
    String googleMapLoc ;  //the URL of Google map location
   String orgName ;
   String orgDoc ;
   String bio ;
   String orgPic ; //the URL of the pic
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_regisaration);

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailP = (EditText)findViewById(R.id.EmailP);
        final EditText PasswordP = (EditText)findViewById(R.id.PasswordP);
        final EditText RPname = (EditText)findViewById(R.id.nameemp);
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
                Email = EmailP.getText().toString();
                password = PasswordP.getText().toString();
                
                mAuth.createUserWithEmailAndPassword(Email, password);
                Toast.makeText(PosterRegisaration.this,"Registration Done!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

}
