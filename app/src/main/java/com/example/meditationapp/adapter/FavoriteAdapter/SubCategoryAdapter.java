package com.example.meditationapp.adapter.FavoriteAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.FavoritesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.SubFavoritesModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.FavoritesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryAdapter  extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    Context context;
    List<SubFavoritesModelClass> subFavoritesModelClasses;

    public SubCategoryAdapter(Context context, List<SubFavoritesModelClass> subFavoritesModelClasses) {
        this.context=context;
        this.subFavoritesModelClasses = subFavoritesModelClasses;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_sub_fav_cat_layout,parent,false);
        return new SubCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubCategoryAdapter.MyViewHolder holder, final int position) {

        final SubFavoritesModelClass subFavoritesModelClass = subFavoritesModelClasses.get(position);
        holder.cat_textView.setText(subFavoritesModelClass.getSongsTitle());
//        Picasso.get().load(subFavoritesModelClass.getFavriteStatus()).into(holder.hurtIV);
        Picasso.get().load(subFavoritesModelClass.getSongs()).into(holder.songIV);

    }

    @Override
    public int getItemCount() {
        return subFavoritesModelClasses.size();
//
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView hurtIV,songIV;
        CustomBoldtextView cat_textView;
        RelativeLayout backRL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hurtIV = itemView.findViewById(R.id.hurt_IV);
            songIV = itemView.findViewById(R.id.recyclerListCat_playIV);
            cat_textView = itemView.findViewById(R.id.recyclerListCat_playTV);




        }
    }
}
