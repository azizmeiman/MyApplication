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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class addworker extends AppCompatActivity {

    String WorkerName;
    String WorkerID;
    String WorkerMobile;
    String WorkerNationality;
    String WorkerBDDate;
    String WorkerSkills;
    String WorkerFees;
    String WorkerDocument;
    String Workerpic=null;
    String Workerpdf;
    String PosterUID;


    private ProgressDialog mprogress;

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworker);
        mStorageRef = FirebaseStorage.getInstance().getReference("All Uploads ");
        final EditText WorkerNameEdit = (EditText) findViewById(R.id.WorkerNameText);
        final EditText WorkerIDEdit = (EditText) findViewById(R.id.WorkerIDText);
        final EditText WorkerMobileEdit = (EditText) findViewById(R.id.WorkerMobileText);
        final EditText WorkerNationalityEdit = (EditText) findViewById(R.id.WorkerNationalityText);
        final EditText WorkerBDateEdit = (EditText) findViewById(R.id.WorkerBDateText);
        final EditText WorkerSkillsEdit = (EditText) findViewById(R.id.WorkerSkillsText);
        final EditText WorkerFeesEdit = (EditText) findViewById(R.id.WorkerFeesText);
        final Button AddworkerBut = (Button) findViewById(R.id.addworkerBut);

        mprogress = new ProgressDialog(this);


        Button pic = (Button)findViewById(R.id.PicUploadW);

        pic.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooser();

            }
        });
        Button picup = (Button)findViewById(R.id.UploadthepicWorker);

        picup.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                uploadFile();

            }
        });
        Button pdf = (Button)findViewById(R.id.PdfUploadW);

        pdf.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooserpdf();

            }
        });
        Button pdfup = (Button)findViewById(R.id.UploadthepdfW);

        pdfup.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                uploadFile();

            }
        });

        AddworkerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 WorkerName = WorkerNameEdit.getText().toString();
                 WorkerID = WorkerIDEdit.getText().toString();
                 WorkerMobile = WorkerMobileEdit.getText().toString();
                 WorkerNationality = WorkerNationalityEdit.getText().toString();
                 WorkerBDDate = WorkerBDateEdit.getText().toString();
                 WorkerSkills = WorkerSkillsEdit.getText().toString();
                 WorkerFees = WorkerFeesEdit.getText().toString();
                 PosterUID = FirebaseAuth.getInstance().getCurrentUser().getUid();


                 Worker worker = new Worker(WorkerName, WorkerID, WorkerFees, Workerpic, WorkerNationality, WorkerSkills, WorkerMobile, WorkerBDDate, PosterUID, Workerpdf);
                 DBAccess dba = new DBAccess();
                 dba.insetWorker(worker);
                 Toast.makeText(addworker.this, "تمت إضافة العامل", Toast.LENGTH_SHORT).show();
                 finish();




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
        }
    }
    private void uploadFile() {

        if (mImageUri != null) {

            mprogress.setMessage("Uploading ... ");
            mprogress.show();

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            mprogress.dismiss();
                            Toast.makeText(addworker.this, "Upload successful", Toast.LENGTH_LONG).show();


                            if(Workerpic!=null)
                                Workerpdf=System.currentTimeMillis()
                                        + "." + getFileExtension(mImageUri);
                            else
                                Workerpic=System.currentTimeMillis()
                                        + "." + getFileExtension(mImageUri);



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mprogress.dismiss();
                            Toast.makeText(addworker.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
