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
import com.example.meditationapp.ModelClasses.SoundModel.SoundScapeModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.MyViewHolder> {

    Context context;
    List<CategoryDataModelClass> categoryDataModelClasses;

    public AllCategoryAdapter(Context context, List<CategoryDataModelClass> categoryDataModelClasses) {
        this.context = context;
        this.categoryDataModelClasses = categoryDataModelClasses;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_scape_layout_rv,parent,false);
        return new AllCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.MyViewHolder holder, final int position) {

        final CategoryDataModelClass categoryDataModelClass = categoryDataModelClasses.get(position);

        Picasso.get().load(categoryDataModelClass.getImage()).into(holder.backImageView);

        holder.backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryDataModelClasses.size();
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
