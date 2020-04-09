package com.example.meditationapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.InterestedData;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.itemHolder> {
    Context context;
    List<InterestedData> interested;

    public InterestAdapter(Context context, List<InterestedData> interested) {
        this.context = context;
        this.interested = interested;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interested_recycleritem, parent, false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
        Picasso.get().load(interested.get(position).getImage()).into(holder.image);
        holder.name.setText(interested.get(position).getName());
        Log.e("interested", String.valueOf(interested.size()));
    }

    @Override
    public int getItemCount() {
        return interested.size();
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
