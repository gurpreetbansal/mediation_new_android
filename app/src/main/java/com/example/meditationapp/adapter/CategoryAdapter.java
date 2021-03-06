package com.example.meditationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoryData;
import com.example.meditationapp.ModelClasses.InterestedData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.WeighTwoActivity;
import com.example.meditationapp.javaActivities.AllCatAndRecomendedActivity;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.itemHolder> implements Filterable {

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
    public void onBindViewHolder(@NonNull final CategoryAdapter.itemHolder holder, final int position) {
        Picasso.get().load(categoryData.get(position).getImage()).into(holder.image);
        holder.name.setText(categoryData.get(position).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, WeighTwoActivity.class);

                Integer cat = categoryData.get(position).getId();
                String cat_id = String.valueOf(cat);
                Log.e("CAT_ID", cat_id);

                Intent intent = new Intent(context, AllCatAndRecomendedActivity.class);
                intent.putExtra("cat_id", cat_id);
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }
    //filter method
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    categoryData = categoryData;

                } else {
                    List filteredList = new ArrayList<>();
                    for (CategoryData row : categoryData) {


                        //change this to filter according to your case
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    categoryData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = categoryData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                categoryData = (ArrayList) filterResults.values;
                notifyDataSetChanged();

            }
        };
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
