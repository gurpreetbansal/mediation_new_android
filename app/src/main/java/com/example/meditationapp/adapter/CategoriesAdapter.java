package com.example.meditationapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.CategoriesActivities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    List<CategoriesModelClass> categoriesModelClasses;
    List<String> data = new ArrayList<>();
    Context context;

    public CategoriesAdapter(List<CategoriesModelClass> categoriesModelClasses, Context context) {
        this.categoriesModelClasses = categoriesModelClasses;
        this.context=context;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categroies_recycler_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesAdapter.MyViewHolder holder, final int position) {

        final CategoriesModelClass categoriesModelClass = categoriesModelClasses.get(position);
        holder.cat_textView.setText(categoriesModelClasses.get(position).getName());
//        holder.backImageView.setImageResource(categoriesModelClasses.get(position).getFileImage());
        Picasso.get().load(categoriesModelClasses.get(position).getFileImage()).into(holder.backImageView);


         holder.backImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

//                 categoriesModelClasses.get(position).setSelected(true);

                 categoriesModelClass.setSelected(!categoriesModelClass.isSelected());
                 holder.backRL.setBackgroundColor(categoriesModelClass.isSelected() ? Color.parseColor("#32b2df")  : Color.WHITE);
             }
         });


    }

    @Override
    public int getItemCount() {
        return categoriesModelClasses == null ? 0 : categoriesModelClasses.size();
//
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView backImageView;
        CustomBoldtextView cat_textView;
        RelativeLayout backRL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            backRL=itemView.findViewById(R.id.backView_RL);
            backImageView=itemView.findViewById(R.id.recyclerListCat_back_img);
            cat_textView=itemView.findViewById(R.id.recyclerListCat_TextView);


        }
    }
}

//      data.add(String.valueOf(categoriesModelClasses.get(position).getId()));
//              Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
//
//
//              for(int i=0; i<data.size(); i++){
//        if(holder.backRL.equals(data.get(i))){
//        data.remove(i);
//        return;
//        }
//        }
