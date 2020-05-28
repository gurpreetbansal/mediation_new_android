package com.example.meditationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.SoundModel.MusicModelClass;
import com.example.meditationapp.ModelClasses.SoundModel.SoundScapeModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.GetMore_Activity;
import com.example.meditationapp.javaActivities.PremiumActivity_new;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    Context context;
    List<MusicModelClass> musicModelClasses;

    public MusicAdapter(Context context, List<MusicModelClass> musicModelClasses) {
        this.context = context;
        this.musicModelClasses = musicModelClasses;
    }

    @NonNull
    @Override
    public MusicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_layout_rv,parent,false);
        return new MusicAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MyViewHolder holder, final int position) {

        final MusicModelClass musicModelClass = musicModelClasses.get(position);

        Picasso.get().load(musicModelClass.getImages()).into(holder.backImageView);

//        holder.backImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, GetMore_Activity.class);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return musicModelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView,lockImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backImageView=itemView.findViewById(R.id.music_back_IV);
//            lockImageView=itemView.findViewById(R.id.sound_scape_back_lock_IV);


        }
    }
}
