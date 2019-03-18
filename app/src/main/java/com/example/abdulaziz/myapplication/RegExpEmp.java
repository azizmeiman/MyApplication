package com.example.abdulaziz.myapplication;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RegExpEmp extends AppCompatActivity  {

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser updateUI;
    String Email;
    String password;
    String RPname;
    String RPIDemp;
    String RPphoneNumemp;
    String cityIDemp;
    String orgNameemp;
    String orgDocemp;
    String orgPicemp= null;; //the URL of the pic

    private ProgressDialog mprogress;

    private Uri mImageUri;

   private StorageReference mStorageRef;

    private StorageTask mUploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_exp_emp);

        mStorageRef = FirebaseStorage.getInstance().getReference("All Uploads ");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        mAuth = FirebaseAuth.getInstance();
        final EditText EmailE = (EditText) findViewById(R.id.EmailE);
        final EditText PasswordE = (EditText) findViewById(R.id.PasswordE);
        final EditText nameemp = (EditText) findViewById(R.id.nameemp);
        final EditText RPID = (EditText) findViewById(R.id.RPID);
        final EditText RPphoneNum = (EditText) findViewById(R.id.RPphoneNum);
        final Spinner cityID = (Spinner) findViewById(R.id.cityID);
        final EditText orgName = (EditText) findViewById(R.id.orgName);
        //final EditText orgDoc = (EditText) findViewById(R.id.orgDoc);
       // final EditText orgPic = (EditText) findViewById(R.id.orgPic);

        mprogress = new ProgressDialog(this);

//        final List<Object> CityList = new ArrayList<>();
//        myRef.child("City").addValueEventListener(new ValueEventListener() {

//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
//                for (DataSnapshot child : children) {
//                    Object Cities =child.getValue();
//                    CityList.add(Cities);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(RegExpEmp.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//           ArrayAdapter<Object> cityAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, CityList);
//           cityID.setAdapter(cityAdapter);

        List<String> cityL = new ArrayList<String>();
        cityL.add("Riyadh");
        cityL.add("Dammam");
        cityL.add("Jeddah");


        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityL);
        cityID.setAdapter(citites);

     Button pic = (Button)findViewById(R.id.PicUpload);

      pic.setOnClickListener(new View.OnClickListener() {



           public void onClick(View v) {
               openFileChooser();

           }
         });


        Button pdf = (Button)findViewById(R.id.PdfUpload);

        pdf.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                openFileChooserpdf();

            }
        });


        Button Reg = (Button)findViewById(R.id.RegE);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = EmailE.getText().toString();
                password = PasswordE.getText().toString();
                RPname = nameemp.getText().toString();
                RPIDemp= RPID.getText().toString();
                RPphoneNumemp = RPphoneNum.getText().toString();
                orgNameemp = orgName.getText().toString();
                cityIDemp = cityID.getSelectedItem().toString(); // نبغى نقرا هنا المدينة الي اخترناها





                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {




                            Employer emp = new Employer(
                                    Email,
                                    RPname,
                                    RPIDemp,
                                    RPphoneNumemp,
                                    cityIDemp,
                                    orgNameemp,
                                    orgDocemp  ,orgPicemp);





                            FirebaseDatabase.getInstance().getReference("Employer")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(emp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("UserType").setValue("Employer");
                                        Toast.makeText(RegExpEmp.this,"Done",Toast.LENGTH_LONG).show();
                                        Intent intentLogin = new Intent(RegExpEmp.this, Login.class);
                                        startActivity(intentLogin);
                                 }
                                        else {
                                        Toast.makeText(RegExpEmp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegExpEmp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
            uploadPic();
        }
    }
    private void uploadPic() {

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
                            Toast.makeText(RegExpEmp.this, "Upload successful", Toast.LENGTH_LONG).show();

                            if(orgPicemp!=null)
                                orgDocemp=System.currentTimeMillis()
                                    + "." + getFileExtension(mImageUri);
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
                                            orgPicemp = downloadUri.toString();
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
                            Toast.makeText(RegExpEmp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



}
