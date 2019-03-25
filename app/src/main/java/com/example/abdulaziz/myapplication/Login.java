package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
    private ProgressBar mprogress;


    private DatabaseReference jLoginDatabase;
    private DatabaseReference blockDatabase;
    private DatabaseReference blockDatabaseE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button LoginButtun = (Button) findViewById(R.id.LoginButtun);
        mprogress  = (ProgressBar) findViewById(R.id.progressbar);
        mprogress.setVisibility(View.INVISIBLE);

        LoginButtun.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                mprogress.setVisibility(View.VISIBLE);
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


        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(password)) {
            mprogress.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Enter user Name / password first . . .", Toast.LENGTH_SHORT).show();}
            else{
        mAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            public void onComplete(final Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String RegisteredUserID = currentUser.getUid();

                    jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(RegisteredUserID);

                    blockDatabase =FirebaseDatabase.getInstance().getReference().child("WorkerPoster").child(RegisteredUserID);
                    blockDatabaseE =FirebaseDatabase.getInstance().getReference().child("Employer").child(RegisteredUserID);
                    jLoginDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String userType = dataSnapshot.child("UserType").getValue().toString();

                            if (userType.equals("Admin")) {

                                Intent intentMain = new Intent(Login.this, AdminMain.class);
                                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                mprogress.setVisibility(View.INVISIBLE);
                                startActivity(intentMain);
                            } else if (userType.equals("WorkerPoster")) {
                                blockDatabase.addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        boolean is = (boolean) dataSnapshot.child("blucked").getValue();
                                        if(is==false){
                                            Intent intentMain = new Intent(Login.this, WorkerPosterMain.class);
                                            intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            mprogress.setVisibility(View.INVISIBLE);
                                            startActivity(intentMain);}
                                            else {
                                            mprogress.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Login.this, "Sorry you are blocked ", Toast.LENGTH_SHORT).show();
                                        }

                                    }


                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        databaseError.getMessage();

                                    }

                                });




                            } else if (userType.equals("Employer")) {

                                blockDatabaseE.addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        boolean is = (boolean) dataSnapshot.child("blucked").getValue();
                                        if(is==false){


                                            Intent intentMain = new Intent(Login.this, EmployerMainActivity.class);
                                            intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            mprogress.setVisibility(View.INVISIBLE);
                                            startActivity(intentMain);
                                        }
                                        else {
                                            mprogress.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Login.this, "Sorry you are blocked ", Toast.LENGTH_SHORT).show();
                                        }

                                    }


                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }

                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            mprogress.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                   //         databaseError.getMessage();
                        }

                    });

                }else{
                    mprogress.setVisibility(View.INVISIBLE);
                    String error = task.getException().toString();
                    Toast.makeText(Login.this, "Wrong Email or Password: " + error, Toast.LENGTH_SHORT).show();

                }
            }




      }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mprogress.setVisibility(View.INVISIBLE);
                    String error = e.getMessage().toString();
                    Toast.makeText(Login.this, "Wrong Email or Password: " + error, Toast.LENGTH_SHORT).show();

                }
            });
   }
}

}