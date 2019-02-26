package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.view.View.*;

public class Login extends AppCompatActivity {

    String Email;
    String password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button LoginButtun = (Button) findViewById(R.id.LoginButtun);

        LoginButtun.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                signIn();
            }

        });


    }

    public void signIn() {

        final EditText EmailLogin = (EditText) findViewById(R.id.EmailLogin);
        final EditText PasswordLogin = (EditText) findViewById(R.id.PasswordLogin);


        mAuth = FirebaseAuth.getInstance();

        Email = EmailLogin.getText().toString();

        password = PasswordLogin.getText().toString();


        mAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intentLogin = new Intent(Login.this, EmployerMainActivity.class);
                    Toast.makeText(Login.this, "Admin", Toast.LENGTH_LONG).show();
//                    if (u1.Checkuser() == 1) {
//                        Toast.makeText(Login.this, "Admin", Toast.LENGTH_LONG).show();
//                        Intent intentLogin = new Intent(Login.this, AdminMain.class);
//                        startActivity(intentLogin);
//                    }
//                    if (u1.Checkuser() == 2) {
//                        Toast.makeText(Login.this, "Employer", Toast.LENGTH_LONG).show();
//                        Intent intentLogin = new Intent(Login.this, EmployerMainActivity.class);
//                        startActivity(intentLogin);
//                    }
//                    if (u1.Checkuser() == 3) {
//                        Toast.makeText(Login.this, "WorkerPoster", Toast.LENGTH_LONG).show();
//                        Intent intentLogin = new Intent(Login.this, WorkerPosterMain.class);
//                        startActivity(intentLogin);
//                    } else
//                        Toast.makeText(Login.this, "nothing", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }

                }

            }
        });

    }
}