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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends ArrayAdapter<Request>{



    private Context cContext;
    private List<Request> requestsList = new ArrayList<>();

    public RequestAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Request> list) {
        super(context, 0 , list);
        cContext = context;
        requestsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(cContext).inflate(R.layout.contract_item,parent,false);

        Request currentRequest = requestsList.get(position);

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


        return listItem;
    }


}
