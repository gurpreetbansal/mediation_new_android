package com.example.meditationapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.FavoritesModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesCategoryAdapter extends RecyclerView.Adapter<FavoritesCategoryAdapter.MyViewHolder> {

    Context context;
    List<FavoritesModelClass> favoritesModelClasses;

    public FavoritesCategoryAdapter(Context context, List<FavoritesModelClass> favoritesModelClasses) {
        this.context=context;
        this.favoritesModelClasses = favoritesModelClasses;

    }

    @NonNull
    @Override
    public FavoritesCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_catageory_layout,parent,false);
        return new FavoritesCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritesCategoryAdapter.MyViewHolder holder, final int position) {

        final FavoritesModelClass favoritesModelClass = favoritesModelClasses.get(position);
        holder.cat_textView.setText(favoritesModelClass.getName());
        Picasso.get().load(favoritesModelClass.getImage()).into(holder.backImageView);



    }

    @Override
    public int getItemCount() {
        return favoritesModelClasses.size();
//
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView;
        CustomBoldtextView cat_textView;
        RelativeLayout backRL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backRL=itemView.findViewById(R.id.backFavView_RL);
            backImageView=itemView.findViewById(R.id.recyclerListCat_back_FavImg);
            cat_textView=itemView.findViewById(R.id.recyclerListCat_FavTextView);


        }
    }
}
