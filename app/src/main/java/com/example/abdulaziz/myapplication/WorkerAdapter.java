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

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_Worker);
        if(currentWorker.getPicture() == null)
            Picasso.get().load(R.drawable.profile).into(image);
        else
        Picasso.get().load(currentWorker.getPicture()).into(image);
        //image.setImageResource(currentWorker.getPicture());

        TextView name = (TextView) listItem.findViewById(R.id.textView5);
        name.setText(currentWorker.getName());

        TextView price = (TextView) listItem.findViewById(R.id.textView6);
        price.setText(currentWorker.getPrice());

        return listItem;
    }
}