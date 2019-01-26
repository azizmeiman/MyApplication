package com.example.abdulaziz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegExpEmp extends AppCompatActivity {


    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser updateUI;
    String Email ;
    String password ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_exp_emp);

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailE = (EditText)findViewById(R.id.EmailE);
        final EditText PasswordE = (EditText)findViewById(R.id.PasswordE);



        Button Reg = (Button)findViewById(R.id.RegE);




        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = EmailE.getText().toString();

                password = PasswordE.getText().toString();
                mAuth.createUserWithEmailAndPassword(Email, password);
                Toast.makeText(RegExpEmp.this,"Registration Done!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

}
