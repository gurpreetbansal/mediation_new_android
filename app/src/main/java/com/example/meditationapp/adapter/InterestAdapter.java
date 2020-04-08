package com.example.meditationapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.InterestedData;
import com.example.meditationapp.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interested_recycleritem,parent,false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
        Log.e("interested",String.valueOf(interested.size()));
    }

    @Override
    public int getItemCount() {
        return interested.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {
        public itemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
