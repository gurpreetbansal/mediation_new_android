package com.example.meditationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.RecordingCategory;
import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.AllCatAndRecomendedActivity;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.example.meditationapp.javaActivities.WeightActivityNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecordcCategoryAdapter extends RecyclerView.Adapter<RecordcCategoryAdapter.itemHolder> {
    Context context;
    List<RecordingCategory> category;

    public RecordcCategoryAdapter(Context context, List<RecordingCategory> category) {
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recording_recycleritem, parent, false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemHolder holder, final int position) {
        Picasso.get().load(category.get(position).getImage()).into(holder.image);
        holder.title.setText(category.get(position).getName());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer cat = category.get(position).getId();
                String cat_id = String.valueOf(cat);
                Log.e("CAT_ID", cat_id);

                Intent intent = new Intent(context, WeightActivityNew.class);
                intent.putExtra("category_id", cat_id);
                holder.itemView.getContext().startActivity(intent);

            }
        });

        holder.category_playall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreativityAffirmationActivityNew.class);
//                intent.putExtra("demo","https://clientstagingdev.com/meditation/public/voice/1586425636.mp3");
                intent.putStringArrayListExtra("playlist", (ArrayList<String>) category.get(position).getMyRecording());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image;
        CustomBoldtextView title;
        ImageView category_playall;

        public itemHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.category_img);
            title = itemView.findViewById(R.id.category_title);
            category_playall = itemView.findViewById(R.id.category_playall);
        }
    }
}
