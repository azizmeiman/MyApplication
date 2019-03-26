package com.example.abdulaziz.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WorkerAdapter extends ArrayAdapter<Worker> {

    private Context wContext;
    private List<Worker> workersList = new ArrayList<>();

    public WorkerAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Worker> list) {
        super(context, 0 , list);
        wContext = context;
        workersList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(wContext).inflate(R.layout.list_item,parent,false);

        Worker currentWorker = workersList.get(position);

        TextView Available = (TextView) listItem.findViewById(R.id.textViewAva);
        String isAvailable = isAvailableMethod(currentWorker);


        if(isAvailable.equals("")){
            Available.setText("متاح");
        }else {
            String s = "غير متاح حتى "+isAvailable;
            Available.setText(s);
        }

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_Worker);
        if(currentWorker.getPicture() == null)
            image.setImageResource(R.drawable.profile);
        else
        Picasso.get().load(currentWorker.getPicture()).into(image);
       // Glide.with(context).load(currentWorker.getPicture()).into(image);

        //image.setImageResource(R.drawable.profile);

        TextView name = (TextView) listItem.findViewById(R.id.textView5);
        name.setText(currentWorker.getName());

        TextView price = (TextView) listItem.findViewById(R.id.textView6);
        int priceS = currentWorker.getPrice();
        price.setText(String.valueOf(priceS));


        TextView city = (TextView) listItem.findViewById(R.id.textView7);
        String cityS = wContext.getString(R.string.cityIcon)+currentWorker.getCity();  //getCity************
        city.setText(cityS);






        return listItem;
    }



    String endDate ;
    boolean busy = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public String isAvailableMethod(final Worker worker){




        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Date current = new Date();

                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Date  start = new Date(), end = new Date();

                    String sStart = child.child("startDate").getValue().toString();
                    String sEnd = child.child("endDate").getValue().toString();


                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterS = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                        start = formatterS.parse(sStart);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterE = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                        end = formatterE.parse(sEnd);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(worker.getID().equals(child.child("workerID").getValue().toString()) && end.after(current) && start.before(current)){

                        endDate = sEnd ;
                        busy = true;

                        break;
                    }

                }
                if(busy){
                    myRef.child("Worker").child(worker.getID()).child("available").setValue(false);
                }else {
                    myRef.child("Worker").child(worker.getID()).child("available").setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(busy){
            worker.setAvailable(false);
            return endDate;
        }else{
            worker.setAvailable(true);
            return "";
        }

    }
}