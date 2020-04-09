package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoryData;
import com.example.meditationapp.ModelClasses.InterestedData;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.itemHolder> {

    Context context;
    List<CategoryData> categoryData;

    public CategoryAdapter(Context context, List<CategoryData> categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @NonNull
    @Override
    public CategoryAdapter.itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interested_recycleritem, parent, false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.itemHolder holder, int position) {
        Picasso.get().load(categoryData.get(position).getImage()).into(holder.image);
        holder.name.setText(categoryData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image;
        CustomBoldtextView name;

        public itemHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.interested_recyclerimage);
            name = itemView.findViewById(R.id.interested_recyclername);
        }
    }
}
