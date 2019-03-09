package com.example.abdulaziz.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.List;

public class viewworkers extends AppCompatActivity {

//private FirebaseAuth mAuth;
//private FirebaseDatabase database;
//private DatabaseReference myRef;
//private Worker workers;
//private List<Object> workerlist = new ArrayList<Object>();
//private RecyclerView workerlistview;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_viewworkers);
//         workerlistview = (RecyclerView) findViewById(R.id.viewworkerlist);
//
//         mAuth = FirebaseAuth.getInstance();
//
//         database = FirebaseDatabase.getInstance();
//         myRef = database.getReference();
//
//        FirebaseUser currentuser = mAuth.getCurrentUser();
//        final String Posteruid = currentuser.getUid();
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                workerlist.clear();
//
//                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String IDworkerposter = dataSnapshot.child("posterUID").getKey().toString();
//                    if(Posteruid.equals(IDworkerposter))    {
//                        Object worker = ds.getValue(Object.class);
//
//                        workerlist.add(worker);
//                    }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(viewworkers.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                return;
//            }
//        });
//
//        ArrayAdapter<Object>WorkerAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, workerlist);
//        workerlistview.Adapter(WorkerAdapter);
//
//
//
//
//
//
//
//
//
//        }
//
//    public class CountryViewHolder {
//        CardView cv;
//        TextView countryname;
//        ImageView flagphoto;
//
//        CountryViewHolder(View itemView) {
//            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cardview);
//            countryname = (TextView)itemView.findViewById(R.id.country_name);
//            flagphoto = (ImageView)itemView.findViewById(R.id.flag_photo);
//        }
//    }
}