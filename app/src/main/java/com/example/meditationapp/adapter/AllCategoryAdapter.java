package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.AllCatModelClasses.CategoryAndRecomendedData;
import com.example.meditationapp.ModelClasses.AllCatModelClasses.CategoryDataModelClass;
import com.example.meditationapp.ModelClasses.SessionModelClass;
import com.example.meditationapp.ModelClasses.SoundModel.SoundScapeModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.MyViewHolder> {

    Context context;
    List<SessionModelClass> sessionModelClasses;

    public AllCategoryAdapter(Context context, List<SessionModelClass> sessionModelClasses) {
        this.context = context;
        this.sessionModelClasses = sessionModelClasses;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_scape_layout_rv, parent, false);
        return new AllCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.MyViewHolder holder, final int position) {

        final SessionModelClass sessionModelClasse = sessionModelClasses.get(position);

//        Picasso.get().load(sessionModelClasse.getImage()).into(holder.backImageView);
        Picasso.get().load(sessionModelClasse.getImages()).into(holder.backImageView);

        holder.backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return sessionModelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView, lockImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backImageView = itemView.findViewById(R.id.sound_scape_back_IV);
//            lockImageView=itemView.findViewById(R.id.sound_scape_back_lock_IV);


        }
    }
}
