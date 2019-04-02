package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
       contract.setText("رقم العقد: "+currentRequest.getContractID());

        TextView empId = (TextView) listItem.findViewById(R.id.empID);
        //empId.setText("Employer Name: "+currentRequest.getEmpName());
        empId.setText("اسم الباحث: "+currentRequest.getEmpName());


        TextView workerID = (TextView) listItem.findViewById(R.id.workerID);
        //workerID.setText("Worker Name: "+currentRequest.getWorkerName());
        workerID.setText("اسم العامل: "+currentRequest.getWorkerName());

        TextView Date = (TextView) listItem.findViewById(R.id.Date);
       // Date.setText(currentRequest.getEndDate()+" حتى"+currentRequest.getStartDate()+"من");
        Date.setText("من" + currentRequest.getStartDate()+" حتى"+currentRequest.getEndDate());

        TextView totalPrice = (TextView) listItem.findViewById(R.id.totalP);
        totalPrice.setText("الاجمالي "+String.valueOf(currentRequest.getTotalprice())+"SR");



        Button Accept = (Button) listItem.findViewById(R.id.Accept);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contract c = new Contract(currentRequest.getContractID(),currentRequest.getWorkerID(),currentRequest.getEmpID(),currentRequest.getPosterID(),currentRequest.getEmpMobile(),currentRequest.getWorkerName(),currentRequest.getEmpName(),currentRequest.getPeriod(),currentRequest.getStartDate(),currentRequest.getEndDate(),currentRequest.getTotalprice(),1);
                incresstheContractPoster(currentRequest.getPosterID());
                incresstheContractEmp(currentRequest.getEmpID());
                DBA.insertContract(c);

                Toast.makeText(cContext, "تم قبول الطلب", Toast.LENGTH_SHORT).show();

                DBA.deleteRequest(currentRequest.getContractID());
            }
        });

        Button Reject = (Button) listItem.findViewById(R.id.Reject);
        Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBA.deleteRequest(currentRequest.getContractID());
                Toast.makeText(cContext, "تم رفض الطلب", Toast.LENGTH_SHORT).show();
            }
        });



        final String id = currentRequest.getEmpID();


        ImageButton chat = (ImageButton) listItem.findViewById(R.id.imageButton);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mobileNum =  currentRequest.getEmpMobile();
               String url = "https://api.whatsapp.com/send?phone="+"966"+mobileNum;
               Intent whatsappinent = new Intent(Intent.ACTION_VIEW);
               whatsappinent.setData(Uri.parse(url));
               cContext.startActivity(whatsappinent);
            }
        });

        return listItem;
    }


//        public String empNumber(String empID) {
//        final String EmpID = empID;
//
//
//        myRef.child("Employer").addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            final String[] mobilephone = {""};
//            Employer emp = dataSnapshot.child(EmpID).getValue(Employer.class);
//            mobilephone[0] = emp.getRPphoneNum();
//
//        }
//
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//        }
//
//    });
//
//    return mobilephone[0];
//  }


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
