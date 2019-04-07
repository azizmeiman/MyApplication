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

public class PaymantAdapter extends ArrayAdapter<PaymentRecord> {


    private Context payContext;
    private List<PaymentRecord> payList = new ArrayList<>();

    public PaymantAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<PaymentRecord> list) {
        super(context, 0 , list);
        payContext = context;
        payList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItempay = convertView;
        if(listItempay == null)
            listItempay = LayoutInflater.from(payContext).inflate(R.layout.activity_paymant_adapter,parent,false);

        PaymentRecord currentEmp = payList.get(position);

        ImageView image = (ImageView)listItempay.findViewById(R.id.imageView_pic);
            Picasso.get().load(currentEmp.getPic()).into(image);
        //image.setImageResource(currentWorker.getPicture());

        TextView name = (TextView) listItempay.findViewById(R.id.textViewAmaunt);
        name.setText("المبلغ المدفوع"+String.valueOf(currentEmp.getAmountOfPayment()));




        return listItempay;
    }
}
