package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ResetTheBalance extends AppCompatActivity {

    private String fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_the_palanc);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        final TextView balance = (TextView) findViewById(R.id.amount);
        final Button edit = (Button) findViewById(R.id.button3);
        final EditText reset = (EditText) findViewById(R.id.editText12);



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String idp = extras.getString("idp");

        myRef.child("WorkerPoster").addValueEventListener(new ValueEventListener() {
            WorkerPoster WorkerPoster = new WorkerPoster();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(child.child("idp").getValue().toString().equals(idp)) {



                        WorkerPoster =  dataSnapshot.child(idp).getValue(WorkerPoster.class);

                       fees = String.valueOf(WorkerPoster.getSystemfees());
                        balance.setText(fees);

                        ImageView image = (ImageView) findViewById(R.id.imageView13);

                        if(WorkerPoster.getFeesRelesPic() != "empty") {

                            Picasso.get().load(WorkerPoster.getFeesRelesPic()).into(image);


                        }
                        else {
                            image.setImageResource(R.drawable.profile);
                        }

                        edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String newP
                                        = reset.getText().toString();
                                double fse= WorkerPoster.getSystemfees()- Double.parseDouble(newP);

                                myRef.child("WorkerPoster").child(WorkerPoster.getIDP()).child("systemfees").setValue(fse);
                                finish();
                            }
                        });


                        break;
                    }

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ResetTheBalance.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });

    }
}
