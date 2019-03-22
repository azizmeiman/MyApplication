package com.example.abdulaziz.myapplication;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import org.w3c.dom.Text;

public class systemfeesworkerposter extends AppCompatActivity {


    private String fees;
    private FirebaseAuth mAuth;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    private ProgressBar mprogress;

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;

    private String pic;
     String posterUID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemfees);
        mStorageRef = FirebaseStorage.getInstance().getReference("Pictures");

        final TextView systemfeelabel = (TextView) findViewById(R.id.systemfeeslabel);
        final TextView systemfeeinput = (TextView) findViewById(R.id.systemfeesinput);
        final Button systemfeeupload = (Button)findViewById(R.id.systemfeesupload);
        mprogress  = (ProgressBar) findViewById(R.id.progressbar);
        mprogress.setVisibility(View.INVISIBLE);


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        posterUID = currentUser.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        myRef.child("WorkerPoster").addValueEventListener(new ValueEventListener() {
            WorkerPoster poster1=new  WorkerPoster();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 poster1 = dataSnapshot.child(posterUID).getValue(WorkerPoster.class);
                fees = String.valueOf(poster1.getSystemfees());
                systemfeeinput.setText(fees);
                systemfeeupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFileChooser();
                    }
                });



            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        //upload the pic part is down



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

            mprogress.setVisibility(View.VISIBLE);

            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            mprogress.setVisibility(View.INVISIBLE);
                            Toast.makeText(systemfeesworkerposter.this, "Upload successful", Toast.LENGTH_LONG).show();



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
                                            pic=downloadUri.toString();
                                            myRef.child("WorkerPoster").child(posterUID).child("feesRelesPic").setValue(pic); finish();

                                        } else {
                                            // Handle failures
                                            // ...
                                        }
                                    }
                                });
                                //   Workerpic = taskSnapshot.getUploadSessionUri().toString();

                            }


                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mprogress.setVisibility(View.INVISIBLE);
                            Toast.makeText(systemfeesworkerposter.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
