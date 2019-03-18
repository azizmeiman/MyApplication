package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class viewPosterAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poster_admin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        final TextView Orgname = (TextView) findViewById(R.id.orgNAMEp);
        final TextView RPname = (TextView) findViewById(R.id.RPnamep);
        final TextView RPid = (TextView) findViewById(R.id.RPIDp);
        final TextView Rpnumber = (TextView) findViewById(R.id.RPphonNumberp);
        final TextView empMail = (TextView) findViewById(R.id.Emailp);
        final TextView cityn = (TextView) findViewById(R.id.cityp);


        final Button blockposter = (Button) findViewById(R.id.Blockp);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String email = extras.getString("rpid");

        myRef.child("WorkerPoster").addValueEventListener(new ValueEventListener() {
            WorkerPoster WorkerPoster = new WorkerPoster();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(child.child("rpid").getValue().toString().equals(email)) {

                        WorkerPoster = new WorkerPoster(child.getValue(WorkerPoster.class));
                        Orgname.setText("Organization  name: "+WorkerPoster.getOrgName());
                        RPname.setText("Responsible person name: "+WorkerPoster.getRPname());
                        RPid.setText("Responsible person ID:"+WorkerPoster.getRPID());
                        empMail.setText("Email:"+WorkerPoster.getEmail());
                        Rpnumber.setText("Phone Number:"+WorkerPoster.getRPphone());
                        cityn.setText("City:"+WorkerPoster.getCityID());


                        ImageView image = (ImageView)findViewById(R.id.imageView1111);
                        if(WorkerPoster.getOrgPic() == null)
                            image.setImageResource(R.drawable.profile);
                        else
                            Picasso.get().load(WorkerPoster.getOrgDoc()).into(image);

                        break;
                    }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(viewPosterAdmin.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });

    }
}
