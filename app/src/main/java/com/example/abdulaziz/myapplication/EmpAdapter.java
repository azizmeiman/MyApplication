package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EmpAdapter extends ArrayAdapter<Employer> {


    private Context EmpContext;
    private List<Employer> EmpList = new ArrayList<>();

    public EmpAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Employer> list) {
        super(context, 0 , list);
        EmpContext = context;
        EmpList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemEmp = convertView;
        if(listItemEmp == null)
            listItemEmp = LayoutInflater.from(EmpContext).inflate(R.layout.activity_list_item_emp,parent,false);

        Employer currentEmp = EmpList.get(position);

        ImageView image = (ImageView)listItemEmp.findViewById(R.id.imageView_emp);
        if(currentEmp.getOrgPic() == null)
            image.setImageResource(R.drawable.profile);
        else
            Picasso.get().load(currentEmp.getOrgPic()).into(image);
        //image.setImageResource(currentWorker.getPicture());

        TextView name = (TextView) listItemEmp.findViewById(R.id.textViewEmp);
        name.setText(currentEmp.getRPname());

        TextView Orgname = (TextView) listItemEmp.findViewById(R.id.textView6emp);
        Orgname.setText(currentEmp.getOrgName());

        TextView cotnractnumber = (TextView) listItemEmp.findViewById(R.id.textView7);
        cotnractnumber.setText("Number of contract:"+currentEmp.getContractNomE());


        return listItemEmp;
    }
}
