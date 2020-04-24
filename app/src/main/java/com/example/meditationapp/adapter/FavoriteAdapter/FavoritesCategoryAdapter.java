package com.example.meditationapp.adapter.FavoriteAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.FavoritesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.SubFavoritesModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesCategoryAdapter extends RecyclerView.Adapter<FavoritesCategoryAdapter.MyViewHolder> {

    Context context;
    List<FavoritesModelClass> favoritesModelClasses;
    List<SubFavoritesModelClass> subFavoritesModelClasses;
    boolean check = true;


    public FavoritesCategoryAdapter(Context context, List<FavoritesModelClass> data) {
        this.context=context;
        this.favoritesModelClasses = data;
//        this.subFavoritesModelClasses = subFavoritesModelClasses;
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

        holder.backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Boolean check = favoritesModelClass.isSelected();

                if (check.equals(true)){
                    holder.categoryList.setVisibility(View.GONE);
                }
                else if (check.equals(false)){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                    holder.categoryList.setLayoutManager(linearLayoutManager);
                    SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(context,favoritesModelClasses.get(position).getSubFavoritesModelClasses());
                    holder.categoryList.setAdapter(subCategoryAdapter);
                    holder.categoryList.setNestedScrollingEnabled(false);

                }


            }


        });


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
        RecyclerView categoryList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            backRL=itemView.findViewById(R.id.backFavView_RL);
            categoryList = itemView.findViewById(R.id.list_RV);
            backImageView=itemView.findViewById(R.id.recyclerListCat_back_FavImg);
            cat_textView=itemView.findViewById(R.id.recyclerListCat_FavTextView);


        }
    }
}
