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
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(EmpContext).inflate(R.layout.list_item,parent,false);

        Employer currentEmp = EmpList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_emp);
        if(currentEmp.getOrgPic() == null)
            Picasso.get().load(R.drawable.profile).into(image);
        else
            Picasso.get().load(currentEmp.getOrgPic()).into(image);
        //image.setImageResource(currentWorker.getPicture());

        TextView name = (TextView) listItem.findViewById(R.id.textViewEmp);
        name.setText(currentEmp.getRPname());

        TextView Orgname = (TextView) listItem.findViewById(R.id.textView6emp);
        Orgname.setText(currentEmp.getOrgName());

        return listItem;
    }
}
