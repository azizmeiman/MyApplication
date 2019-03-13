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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class addworker extends AppCompatActivity {

    String WorkerName;
    String WorkerID;
    String WorkerMobile;
    String WorkerNationality;
    String WorkerBDDate;
    String WorkerSkills;
    int WorkerFees;
    String Workerpic=null;
    String Workerpdf;
    String PosterUID;
    String WorkerCity;
    int totalincome;
    boolean isAvailable;
    boolean deleted;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


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
        final Spinner WorkerCityEdit = (Spinner) findViewById(R.id.spinnerCity);
        final EditText WorkerBDateEdit = (EditText) findViewById(R.id.WorkerBDateText);
        final Spinner WorkerSkillsEdit = (Spinner) findViewById(R.id.spinnerSkills);
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


        List<String> cityL = new ArrayList<String>();
        cityL.add("الرياض");
        cityL.add("الدمام");
        cityL.add("جده");
        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityL);
        WorkerCityEdit.setAdapter(citites);


        List<String> SkillsL = new ArrayList<String>();
        SkillsL.add("مزارع");
        SkillsL.add("راعي");
        SkillsL.add("سائق حراثه");

        ArrayAdapter<String> Skills = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SkillsL);
        WorkerSkillsEdit.setAdapter(Skills);



        AddworkerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 WorkerName = WorkerNameEdit.getText().toString();
                 WorkerID = WorkerIDEdit.getText().toString();
                 WorkerMobile = WorkerMobileEdit.getText().toString();
                 WorkerNationality = WorkerNationalityEdit.getText().toString();
                 WorkerCity = WorkerCityEdit.getSelectedItem().toString();
                 WorkerBDDate = WorkerBDateEdit.getText().toString();
                 WorkerSkills = WorkerSkillsEdit.getSelectedItem().toString();
                 WorkerFees = Integer.parseInt(WorkerFeesEdit.getText().toString());
                 PosterUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                 totalincome = 0; // When worker added total income will be 0
                 isAvailable=true;
                 deleted = false;



                 DatabaseReference pushRef = myRef.child("Worker").push();
                 String key_ID = pushRef.getKey();

                 Worker worker = new Worker(WorkerName, WorkerID,deleted, WorkerFees, Workerpic, WorkerNationality, WorkerCity, WorkerSkills, WorkerMobile,
                                              WorkerBDDate, PosterUID, Workerpdf, totalincome,key_ID,isAvailable);

                 pushRef.setValue(worker);

                 Toast.makeText(addworker.this, "تمت إضافة العامل", Toast.LENGTH_SHORT).show();

                Toast.makeText(addworker.this, WorkerSkills, Toast.LENGTH_SHORT).show();

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
