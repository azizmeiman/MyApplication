package com.example.abdulaziz.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class PosterRegisaration extends AppCompatActivity {


    private FirebaseAuth mAuth;
//    FirebaseUser currentUser;
//    FirebaseUser updateUI;
    String Email;
    String password;
    String RPnamePoster;
    String RPIDPoster;
    String RPphoneNumPoster;
    String cityIDposter;
    String orgNamePoster;
    String orgDocPoster;
    String orgPicPoster; //the URL of the pic

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_regisaration);
        mStorageRef = FirebaseStorage.getInstance().getReference("Pictures");

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailP = (EditText) findViewById(R.id.EmailP);
        final EditText PasswordP = (EditText) findViewById(R.id.PasswordP);
        final EditText namep = (EditText) findViewById(R.id.namep);
        final EditText RPIDP = (EditText) findViewById(R.id.RPIDP);
        final EditText RPphoneNumP = (EditText) findViewById(R.id.RPphoneNumP);
        final Spinner Cityposter = (Spinner) findViewById(R.id.cityPoster);
        final EditText orgNameP = (EditText) findViewById(R.id.orgNameP);
        final EditText orgDocP = (EditText) findViewById(R.id.orgDocP);
     //   final EditText orgPicP = (EditText) findViewById(R.id.orgPicP);

        List<String> cityL = new ArrayList<String>();
        cityL.add("Riyadh");
        cityL.add("Dammam");
        cityL.add("Jeddah");


        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityL);
        Cityposter.setAdapter(citites);

        Button pic = (Button)findViewById(R.id.PicUpload);

        pic.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooser();

            }
        });

        Button Reg = (Button) findViewById(R.id.RegE);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = EmailP.getText().toString();
                password = PasswordP.getText().toString();
                RPnamePoster = namep.getText().toString();
                RPIDPoster = RPIDP.getText().toString();
                RPphoneNumPoster = RPphoneNumP.getText().toString();
                cityIDposter = Cityposter.getSelectedItem().toString();
                orgNamePoster = orgNameP.getText().toString();
                orgDocPoster = orgDocP.getText().toString();

                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            uploadFile();

                            WorkerPoster workerposter1 = new WorkerPoster(
                                    Email,
                                    RPnamePoster,
                                    RPIDPoster,
                                    RPphoneNumPoster,
                                    cityIDposter,
                                    orgNamePoster,
                                    orgDocPoster,
                                    orgPicPoster



                            );

                            FirebaseDatabase.getInstance().getReference("WorkerPoster")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(workerposter1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("UserType").setValue("WorkerPoster");
                                        Toast.makeText(PosterRegisaration.this,"Done",Toast.LENGTH_LONG).show();
                                        Intent intentLogin = new Intent(PosterRegisaration.this, Login.class);
                                        startActivity(intentLogin);
                                    }
                                    else {
                                        Toast.makeText(PosterRegisaration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(PosterRegisaration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


        });

    }
    private static final int PICK_IMAGE_REQUEST = 1;

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            Toast.makeText(PosterRegisaration.this, "Upload successful", Toast.LENGTH_LONG).show();

                            orgPicPoster=System.currentTimeMillis()
                                    + "." + getFileExtension(mImageUri);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PosterRegisaration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



}
