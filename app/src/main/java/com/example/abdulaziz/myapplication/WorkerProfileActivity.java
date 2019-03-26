package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerProfileActivity extends AppCompatActivity {

    int totalPrice1;
    int monthD;
    Worker worker = new Worker();
    WorkerPoster wposter = new WorkerPoster();
    static WorkerPoster poster = new WorkerPoster();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference myRef2 = database.getReference();


        final TextView name = (TextView) findViewById(R.id.workerName);
        final TextView skills = (TextView) findViewById(R.id.skills);
        final TextView price = (TextView) findViewById(R.id.price);
        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        final ListView feedback = (ListView) findViewById(R.id.feedback);
        final RatingBar rate = (RatingBar) findViewById(R.id.workerRate);
        final  Button hire = (Button) findViewById(R.id.Hire);
        final Button chat = (Button)findViewById(R.id.button2);


        ArrayList<String> feed = new ArrayList<String>();
        feed.add("جيد ");
        feed.add("سيء جداً");
        feed.add("جيد جداً");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, feed);
        feedback.setAdapter(myAdapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String id = extras.getString("id");
        final int months = extras.getInt("month");
        final int days = extras.getInt("day");




        myRef.child("Worker").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                   if(child.child("id").getValue().toString().equals(id)) {

                     worker = new Worker(child.getValue(Worker.class));
                     name.setText("Worker name: "+worker.getName());
                     skills.setText("Skills: "+worker.getSkills());
                     price.setText("price perH: "+String.valueOf(worker.getPrice())+".00 SR");
                     monthD = months*30;
                     monthD=monthD+days;
                     float r =  worker.getTotalRate()/worker.getnRate();
                     rate.setRating(r);
                     totalPrice1 = worker.getPrice()*monthD;
                     totalPrice.setText("Total price: "+String.valueOf(totalPrice1)+".00 SR");


                       break;
                   }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(WorkerProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });

        myRef2.child("WorkerPoster").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                        if ((child.child("idp").getValue().toString().equals(worker.getPosterID()))) {
                            wposter = new WorkerPoster(child.getValue(WorkerPoster.class));
                            poster = wposter;
                            break;
                        }

                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request = new Intent(WorkerProfileActivity.this,RequestActivity.class);
                request.putExtra("id",id);
                request.putExtra("period",monthD);
                request.putExtra("totalPrice", totalPrice1);
                request.putExtra("posterID",worker.getPosterID());
                request.putExtra("wName",worker.getName());
                request.putExtra("price",worker.getPrice());
                startActivity(request);
            }
        });
        final String mobile = poster.getRPphone();


Toast.makeText(WorkerProfileActivity.this,poster.getIDP(),Toast.LENGTH_SHORT).show();
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum =  mobile;
                String url = "https://api.whatsapp.com/send?phone="+"966"+mobileNum;
                Intent whatsappinent = new Intent(Intent.ACTION_VIEW);
                whatsappinent.setData(Uri.parse(url));
                startActivity(whatsappinent);
            }
        });
    }
}
