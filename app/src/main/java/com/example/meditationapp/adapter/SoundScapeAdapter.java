package com.example.meditationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.ModelClasses.SoundModel.SoundScapeModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.CreativtyAffirmationsActivity;
import com.example.meditationapp.activities.GetMore_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SoundScapeAdapter extends RecyclerView.Adapter<SoundScapeAdapter.MyViewHolder> {

    Context context;
    List<SoundScapeModelClass> soundScapeModelClasses;

    public SoundScapeAdapter(Context context, List<SoundScapeModelClass> soundScapeModelClasses) {
        this.context = context;
        this.soundScapeModelClasses = soundScapeModelClasses;
    }

    @NonNull
    @Override
    public SoundScapeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_scape_layout_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundScapeAdapter.MyViewHolder holder, final int position) {

        final SoundScapeModelClass soundScapeModelClass = soundScapeModelClasses.get(position);

        Picasso.get().load(soundScapeModelClass.getImages()).into(holder.backImageView);

        holder.backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return soundScapeModelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView,lockImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            backImageView=itemView.findViewById(R.id.sound_scape_back_IV);
//            lockImageView=itemView.findViewById(R.id.sound_scape_back_lock_IV);
        }
    }

}



