package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewEmpProfileAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emp_profile_admin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        final TextView Orgname = (TextView) findViewById(R.id.orgNAME);
        final TextView RPname = (TextView) findViewById(R.id.RPname);
        final TextView RPid = (TextView) findViewById(R.id.RPID);
        final TextView Rpnumber = (TextView) findViewById(R.id.RPphonNumber);
        final TextView empMail = (TextView) findViewById(R.id.EmailE);
        final TextView cityn = (TextView) findViewById(R.id.city);


        final Button blockEmp = (Button) findViewById(R.id.Block);
        final Button checkPdf = (Button) findViewById(R.id.check);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String email = extras.getString("email");

        myRef.child("Employer").addValueEventListener(new ValueEventListener() {
            Employer Employer = new Employer();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(child.child("email").getValue().toString().equals(email)) {

                        Employer = new Employer(child.getValue(Employer.class));
                        Orgname.setText("Organization  name: "+Employer.getOrgName());
                        RPname.setText("Responsible person name: "+Employer.getRPname());
                        RPid.setText("Responsible person ID:"+Employer.getRPID());
                        empMail.setText("Email:"+Employer.getEmail());
                        Rpnumber.setText("Phone Number:"+Employer.getRPphoneNum());
                        cityn.setText("City:"+Employer.getCityID());


                        ImageView image = (ImageView)findViewById(R.id.imageView111);
                        if(Employer.getOrgPic() == null)
                            image.setImageResource(R.drawable.profile);
                        else
                            Picasso.get().load(Employer.getOrgPic()).into(image);

                        checkPdf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent PdfCheck = new Intent(Intent.ACTION_VIEW);
                                PdfCheck.setData(Uri.parse(Employer.getOrgDoc()));
                                startActivity(PdfCheck);
                            }
                        });
                        break;
                    }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewEmpProfileAdmin.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });

    }
}
