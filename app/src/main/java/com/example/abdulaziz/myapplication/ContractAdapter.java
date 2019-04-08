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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContractAdapter extends ArrayAdapter<Contract> {


    private Context ContractContext;
    private List<Contract> ContractList = new ArrayList<>();

    public ContractAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Contract> list) {
        super(context, 0 , list);
        ContractContext = context;
        ContractList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemConract = convertView;
        if(listItemConract == null)
            listItemConract = LayoutInflater.from(ContractContext).inflate(R.layout.activity_list_item_contract,parent,false);

        Contract currentContract = ContractList.get(position);

        ImageView image = (ImageView)listItemConract.findViewById(R.id.imageView_cont);

            image.setImageResource(R.drawable.contract2);

        TextView Workername = (TextView) listItemConract.findViewById(R.id.textViewWorkername);
        //Workername.setText("Worker name: "+currentContract.getWorkerName());
        Workername.setText("اسم العامل "+currentContract.getWorkerName());

        TextView StartDate = (TextView) listItemConract.findViewById(R.id.textViewStartDate);
        //StartDate.setText("Start Date: "+currentContract.getStartDate());
        StartDate.setText("من "+currentContract.getStartDate());

        TextView EndtDate = (TextView) listItemConract.findViewById(R.id.textViewEndDate);
      //  EndtDate.setText("End Date: "+currentContract.getEndDate());
        EndtDate.setText("حتى "+currentContract.getEndDate());

        TextView TotalPrice = (TextView) listItemConract.findViewById(R.id.textViewTotalPrice);
       // TotalPrice.setText("Total Price: "+currentContract.getTotalprice());
        TotalPrice.setText("الاجمالي "+currentContract.getTotalprice()+"SR");

        return listItemConract;
    }
}
