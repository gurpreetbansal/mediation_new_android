package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.AllCatModelClasses.CategoryDataModelClass;
import com.example.meditationapp.ModelClasses.AllCatModelClasses.RecomandedModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecomendedAdapter extends RecyclerView.Adapter<RecomendedAdapter.MyViewHolder> {

    Context context;
    List<RecomandedModelClass> recomandedModelClasses;

    public RecomendedAdapter(Context context, List<RecomandedModelClass> recomandedModelClasses) {
        this.context = context;
        this.recomandedModelClasses = recomandedModelClasses;
    }

    @NonNull
    @Override
    public RecomendedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomended_layout_rv, parent, false);
        return new RecomendedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendedAdapter.MyViewHolder holder, final int position) {

        final RecomandedModelClass recomandedModelClass = recomandedModelClasses.get(position);

        Picasso.get().load(recomandedModelClass.getBanner()).into(holder.backImageView);

        holder.backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return recomandedModelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView, lockImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backImageView = itemView.findViewById(R.id.img_abun);
//            lockImageView=itemView.findViewById(R.id.sound_scape_back_lock_IV);


        }
    }
}
