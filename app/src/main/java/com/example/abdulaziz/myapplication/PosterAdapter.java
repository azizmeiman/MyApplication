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

public class PosterAdapter extends ArrayAdapter<WorkerPoster> {


    private Context PosterContext;
    private List<WorkerPoster> PosterList = new ArrayList<>();


    public PosterAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<WorkerPoster> list) {
        super(context, 0 , list);
        PosterContext = context;
        PosterList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemPoster = convertView;
        if(listItemPoster == null)
            listItemPoster = LayoutInflater.from(PosterContext).inflate(R.layout.activity_list__item_poster,parent,false);

        WorkerPoster currentPoster = PosterList.get(position);

        ImageView image = (ImageView)listItemPoster.findViewById(R.id.imageView_poster);
        if(currentPoster.getOrgPic() == null)
            image.setImageResource(R.drawable.profile);
        else
            Picasso.get().load(currentPoster.getOrgDoc()).into(image);
        //image.setImageResource(currentWorker.getPicture());

        TextView name = (TextView) listItemPoster.findViewById(R.id.textViewposter);
        name.setText(currentPoster.getRPname());

        TextView Orgname = (TextView) listItemPoster.findViewById(R.id.textView6poster);
        Orgname.setText(currentPoster.getOrgName());

        return listItemPoster;
    }
}
