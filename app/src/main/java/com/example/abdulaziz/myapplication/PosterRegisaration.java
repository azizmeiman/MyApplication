package com.example.abdulaziz.myapplication;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class PosterRegisaration extends AppCompatActivity {


    private FirebaseAuth mAuth;
  static FirebaseUser currentUser;
//    FirebaseUser updateUI;
    String Email;
    String password;
    String RPnamePoster;
    String RPIDPoster;
    String RPphoneNumPoster;
    String cityIDposter;
    String orgNamePoster;
    String orgDocPoster;
    String orgPicPoster= null; //the URL of the pic
    double systemfee;


    private ProgressDialog mprogressP;
    private ProgressBar mprogressE;

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;

    private ArrayAdapter citites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_regisaration);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        mStorageRef = FirebaseStorage.getInstance().getReference("Pictures");

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailP = (EditText) findViewById(R.id.EmailP);
        final EditText PasswordP = (EditText) findViewById(R.id.PasswordP);
        final EditText namep = (EditText) findViewById(R.id.namep);
        final EditText RPIDP = (EditText) findViewById(R.id.RPIDP);
        final EditText RPphoneNumP = (EditText) findViewById(R.id.RPphoneNumP);

        final EditText orgNameP = (EditText) findViewById(R.id.orgNameP);
       // final EditText orgDocP = (EditText) findViewById(R.id.orgDocP);
     //   final EditText orgPicP = (EditText) findViewById(R.id.orgPicP);
        mprogressP = new ProgressDialog(this);
        mprogressE  = (ProgressBar) findViewById(R.id.progressbar);
        mprogressE.setVisibility(View.INVISIBLE);

        EmailP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(EmailP.getText().length()<10)
                    EmailP.setError("الرجاء كاتبة الايميل بالطريقة الصحيحة ");
            }
        });
        PasswordP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(PasswordP.getText().length()<6)
                    PasswordP.setError("يجب ان يكون 6 خانات فما فوق ");
            }
        });
        RPphoneNumP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(RPphoneNumP.getText().length()<9)
                    RPphoneNumP.setError("الرجاء كتابة الرقم كاملا بدون صفر ");
            }
        });


        final Spinner Cityposter = (Spinner) findViewById(R.id.cityPoster);
        final List<String> cityL = new ArrayList<String>();

        myRef.child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cityL.add(child.getValue(String.class));
                }
                citites = new ArrayAdapter<String>(PosterRegisaration.this, android.R.layout.simple_list_item_1, cityL);
                Cityposter.setAdapter(citites);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button pic = (Button)findViewById(R.id.PicUploadP);

        pic.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooser();


            }
        });

        Button pdf = (Button)findViewById(R.id.PdfUploadP);

        pdf.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooserpdf();

            }
        });

        Button Reg = (Button) findViewById(R.id.RegP);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogressE.setVisibility(View.VISIBLE);
                Email = EmailP.getText().toString();
                password = PasswordP.getText().toString();
                RPnamePoster = namep.getText().toString();
                RPIDPoster = RPIDP.getText().toString();
                RPphoneNumPoster = RPphoneNumP.getText().toString();
                cityIDposter = Cityposter.getSelectedItem().toString();
                orgNamePoster = orgNameP.getText().toString();


                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(password)  || TextUtils.isEmpty(RPnamePoster) || TextUtils.isEmpty(RPIDPoster) || TextUtils.isEmpty(RPphoneNumPoster) || TextUtils.isEmpty(orgNamePoster) ) {
                    Toast.makeText(PosterRegisaration.this, "املئ جميع الحقول. . .", Toast.LENGTH_LONG).show();
                    mprogressE.setVisibility(View.INVISIBLE);
                }
                else{
                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                       currentUser = FirebaseAuth.getInstance().getCurrentUser();
                       String pid  =   currentUser.getUid();


                            WorkerPoster workerposter1 = new WorkerPoster(
                                    pid,
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
                                        FirebaseDatabase.getInstance().getReference("SystemFees").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("systemfees").setValue(0);
                                        Toast.makeText(PosterRegisaration.this,"Done",Toast.LENGTH_LONG).show();
                                        Intent intentLogin = new Intent(PosterRegisaration.this, Login.class);
                                        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        mprogressE.setVisibility(View.INVISIBLE);
                                        startActivity(intentLogin);
                                    }
                                    else {
                                        mprogressE.setVisibility(View.INVISIBLE);
                                        Toast.makeText(PosterRegisaration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            mprogressE.setVisibility(View.INVISIBLE);
                            Toast.makeText(PosterRegisaration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });}
            }


        });

    }

    public void sendVerficationEmail(){
        currentUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }

        });


    }

    private static final int PICK_IMAGE_REQUEST = 2;

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private void openFileChooserpdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            //  Picasso.with(this).load(mImageUri).into(mImageView);
            uploadFile();
        }
    }
    private void uploadFile() {
        if (mImageUri != null) {
            mprogressP.setMessage("Uploading ... ");
            mprogressP.show();
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            mprogressP.dismiss();
                            Toast.makeText(PosterRegisaration.this, "Upload successful", Toast.LENGTH_LONG).show();

                            if(orgPicPoster!=null){

                                 Task<Uri> urlTask = mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            throw task.getException();
                                        }

                                        // Continue with the task to get the download URL

                                        return fileReference.getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            orgDocPoster = downloadUri.toString();
                                        } else {
                                            // Handle failures
                                            // ...
                                        }
                                    }
                                });


                            }
                            else {
                                Task<Uri> urlTask = mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            throw task.getException();
                                        }

                                        // Continue with the task to get the download URL

                                        return fileReference.getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            orgPicPoster = downloadUri.toString();
                                        } else {
                                            // Handle failures
                                            // ...
                                        }
                                    }
                                });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mprogressP.dismiss();
                            Toast.makeText(PosterRegisaration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



}
