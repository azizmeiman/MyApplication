package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.*;

public class Login extends AppCompatActivity {

    String Email;
    String password;
    private FirebaseAuth mAuth;


    private DatabaseReference jLoginDatabase;
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

            public void onComplete(final Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String RegisteredUserID = currentUser.getUid();

                    jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(RegisteredUserID);

                    jLoginDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String userType = dataSnapshot.child("UserType").getValue().toString();

                            if (userType.equals("Admin")) {

                                Intent intentMain = new Intent(Login.this, AdminMain.class);
                                startActivity(intentMain);
                            } else if (userType.equals("WorkerPoster")) {
                                Intent intentMain = new Intent(Login.this, WorkerPosterMain.class);
                                startActivity(intentMain);
                            } else if (userType.equals("Employer")) {

                                Intent intentMain = new Intent(Login.this, EmployerMainActivity.class);
                                startActivity(intentMain);
                            } else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });

                }
            }




      });
   }
}