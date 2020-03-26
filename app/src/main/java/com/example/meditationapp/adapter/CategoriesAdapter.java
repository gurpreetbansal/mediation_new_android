package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    List<CategoriesModelClass> categoriesModelClasses;

    public CategoriesAdapter(List<CategoriesModelClass> categoriesModelClasses) {
        this.categoriesModelClasses = categoriesModelClasses;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categroies_recycler_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MyViewHolder holder, int position) {

        holder.cat_textView.setText(categoriesModelClasses.get(position).getName());
//        holder.backImageView.setImageResource(categoriesModelClasses.get(position).getFileImage());
        Picasso.get().load(categoriesModelClasses.get(position).getFileImage()).into(holder.backImageView);




    }

    @Override
    public int getItemCount() {
        return categoriesModelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView backImageView;
        CustomBoldtextView cat_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backImageView=itemView.findViewById(R.id.recyclerListCat_back_img);
            cat_textView=itemView.findViewById(R.id.recyclerListCat_TextView);

        }
    }
}
