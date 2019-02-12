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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_regisaration);

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailP = (EditText)findViewById(R.id.EmailP);
        final EditText PasswordP = (EditText)findViewById(R.id.PasswordP);






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
