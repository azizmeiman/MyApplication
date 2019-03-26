package com.example.abdulaziz.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

public class addworker extends AppCompatActivity  {

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
    float Rate;
    int nRate;


    EditText bDate;
    DatePickerDialog bDatePickerDialog;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    private ProgressDialog mprogress;

    private Uri mImageUri;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;

    private ArrayAdapter citites;
    private ArrayAdapter skill;
    private ArrayAdapter nationality;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworker);
        mStorageRef = FirebaseStorage.getInstance().getReference("All Uploads ");
        final EditText WorkerNameEdit = (EditText) findViewById(R.id.WorkerNameText);
        final EditText WorkerIDEdit = (EditText) findViewById(R.id.WorkerIDText);
        final EditText WorkerMobileEdit = (EditText) findViewById(R.id.WorkerMobileText);
        final EditText WorkerFeesEdit = (EditText) findViewById(R.id.WorkerFeesText);
        final Button AddworkerBut = (Button) findViewById(R.id.addworkerBut);

        mprogress = new ProgressDialog(this);


        Button pic = (Button)findViewById(R.id.PicUploadW);

        pic.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooser();

            }
        });

        Button pdf = (Button)findViewById(R.id.PdfUploadW);

        pdf.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooserpdf();

            }
        });



        final Spinner cityID =  (Spinner) findViewById(R.id.spinnerCity);
        final List<String> cityL = new ArrayList<String>();

        myRef.child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cityL.add(child.getValue(String.class));
                }
                citites = new ArrayAdapter<String>(addworker.this, android.R.layout.simple_list_item_1, cityL);
                cityID.setAdapter(citites);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        final Spinner skillID =  (Spinner) findViewById(R.id.spinnerSkills);
        final List<String> skillL = new ArrayList<String>();

        myRef.child("Skills").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    skillL.add(child.getValue(String.class));
                }
                skill = new ArrayAdapter<String>(addworker.this, android.R.layout.simple_list_item_1, skillL);
                skillID.setAdapter(skill);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        final Spinner WorkerNationalityEdit = (Spinner) findViewById(R.id.WorkerNationality);
        final List<String> NationalityL = new ArrayList<String>();

        myRef.child("Nationality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    NationalityL.add(child.getValue(String.class));
                }
                nationality = new ArrayAdapter<String>(addworker.this, android.R.layout.simple_list_item_1, NationalityL);
                WorkerNationalityEdit.setAdapter(nationality);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


// initiate the date picker and a button
        bDate = (EditText) findViewById(R.id.WorkerBDateText);
        // perform click event on edit text
        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int sYear = c.get(Calendar.YEAR); // current year
                int sMonth = c.get(Calendar.MONTH); // current month
                int sDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                bDatePickerDialog = new DatePickerDialog(addworker.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                WorkerBDDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                bDate.setText(WorkerBDDate);

                            }
                        }, sYear, sMonth, sDay);

                bDatePickerDialog.show();
            }
        });


        AddworkerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 WorkerName = WorkerNameEdit.getText().toString();
                 WorkerID = WorkerIDEdit.getText().toString();
                 WorkerMobile = WorkerMobileEdit.getText().toString();
                 WorkerNationality = WorkerNationalityEdit.getSelectedItem().toString();
                 WorkerCity = cityID.getSelectedItem().toString();
                 WorkerSkills = skillID.getSelectedItem().toString();
                 WorkerFees = Integer.parseInt(WorkerFeesEdit.getText().toString());
                 PosterUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                 totalincome = 0; // When worker added total income will be 0
                 isAvailable=true;
                 deleted = false;
                 Rate = 0;
                 nRate = 0;



                 DatabaseReference pushRef = myRef.child("Worker").push();
                 String key_ID = pushRef.getKey();

                 Worker worker = new Worker(WorkerName, WorkerID,deleted, WorkerFees, Workerpic, WorkerNationality, WorkerCity, WorkerSkills, WorkerMobile,
                                              WorkerBDDate, PosterUID, Workerpdf, totalincome,key_ID,isAvailable,Rate,nRate);

                 pushRef.setValue(worker);

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
            uploadFile();
        }
    }
    private void uploadFile() {

        if (mImageUri != null) {

            mprogress.setMessage("Uploading ... ");
            mprogress.show();

            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            mprogress.dismiss();
                            Toast.makeText(addworker.this, "Upload successful", Toast.LENGTH_LONG).show();


                            if(Workerpic!=null){
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
                                            Workerpdf = downloadUri.toString();
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
                                            Workerpic = downloadUri.toString();
                                        } else {
                                            // Handle failures
                                            // ...
                                        }
                                    }
                                });
                             //   Workerpic = taskSnapshot.getUploadSessionUri().toString();

                            }

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
