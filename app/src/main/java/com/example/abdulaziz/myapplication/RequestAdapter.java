package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends ArrayAdapter<Request>{



    private Context cContext;
    private List<Request> requestsList = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();

    public RequestAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Request> list) {
        super(context, 0 , list);
        cContext = context;
        requestsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(cContext).inflate(R.layout.contract_item,parent,false);

       final DBAccess DBA = new DBAccess();

        final Request currentRequest = requestsList.get(position);

        TextView contract = (TextView) listItem.findViewById(R.id.contractID);
       contract.setText(currentRequest.getContractID());

        TextView empId = (TextView) listItem.findViewById(R.id.empID);
        empId.setText("Employer Name: "+currentRequest.getEmpName());

        TextView workerID = (TextView) listItem.findViewById(R.id.workerID);
        workerID.setText("Worker Name: "+currentRequest.getWorkerName());

        TextView Date = (TextView) listItem.findViewById(R.id.Date);
        Date.setText("From "+currentRequest.getStartDate()+" To "+currentRequest.getEndDate());

        TextView totalPrice = (TextView) listItem.findViewById(R.id.totalP);
        totalPrice.setText("Total "+String.valueOf(currentRequest.getTotalprice())+"SR");
        final Intent back = new Intent(cContext,ViewRequestsActivity.class);

        Button Accept = (Button) listItem.findViewById(R.id.Accept);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contract c = new Contract(currentRequest.getContractID(),currentRequest.getWorkerID(),currentRequest.getEmpID(),currentRequest.getPosterID(),currentRequest.getWorkerName(),currentRequest.getEmpName(),currentRequest.getPeriod(),currentRequest.getStartDate(),currentRequest.getEndDate(),currentRequest.getTotalprice(),1);
                incresstheContractPoster(currentRequest.getPosterID());
                incresstheContractEmp(currentRequest.getEmpID());
                DBA.insertContract(c);
                DBA.deleteRequest(currentRequest.getContractID());
                cContext.startActivity(back);

            }
        });

        Button Reject = (Button) listItem.findViewById(R.id.Reject);
        Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBA.deleteRequest(currentRequest.getContractID());
                cContext.startActivity(back);
            }
        });

        return listItem;
    }



    public void incresstheContractPoster(final String id){


        myRef.child("WorkerPoster").addListenerForSingleValueEvent(new ValueEventListener() {
            WorkerPoster WorkerPoster = new WorkerPoster();

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                WorkerPoster = dataSnapshot.child(id).getValue(WorkerPoster.class);
               int cpo=1;
               final int   cp = WorkerPoster.getContractNumber()+cpo;

                    myRef.child("WorkerPoster").child(id).child("contractNumber").setValue(cp);}



            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void incresstheContractEmp(final String id){


        myRef.child("Employer").addListenerForSingleValueEvent(new ValueEventListener() {
            Employer Employer = new Employer();

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Employer = dataSnapshot.child(id).getValue(Employer.class);
                int cpo=1;
                final int   cp = Employer.getContractNomE()+cpo;

                myRef.child("Employer").child(id).child("contractNomE").setValue(cp);}



            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}
