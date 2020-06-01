package com.example.meditationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.NatureData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.CreativtyAffirmationsActivity;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.example.meditationapp.javaActivities.GetMorePaymentActivity;
import com.example.meditationapp.javaActivities.Test;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NatureAdapter extends RecyclerView.Adapter<NatureAdapter.itemHolder> {
    Context context;
    List<NatureData> nature;

    public NatureAdapter(Context context, List<NatureData> nature) {
        this.context = context;
        this.nature = nature;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nature_recycleritem, parent, false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemHolder holder, final int position) {
        Picasso.get().load(nature.get(position).getImages()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (nature.getData().getSoundScopes().get(position).getLockUnlockStatus().equals(0)){
//
//                    Intent intent = new Intent(getActivity(), GetMorePaymentActivity.class);
//                    intent.putExtra("song", natureMusic);
//                    intent.putExtra("nature_id", nature_id);
//                    intent.putExtra("nature_name", nature_name);
//                    startActivity(intent);
//                }
//                else if (resource.getData().getSoundScopes().get(position).getLockUnlockStatus().equals(1)){
//
//                    Intent intent = new Intent(getActivity(), CreativityAffirmationActivityNew.class);
//                    intent.putExtra("song", natureMusic);
//                    intent.putExtra("nature_id", nature_id);
//                    intent.putExtra("nature_name", nature_name);
//                    startActivity(intent);
//                }

                if (nature.get(position).getLockUnlockStatus().equals(0)) {
                    Intent intent = new Intent(context, GetMorePaymentActivity.class);
                    intent.putExtra("colorcode", "0");
                    intent.putExtra("song", nature.get(position).getSongs());
                    intent.putExtra("nature_id", nature.get(position).getNatureId());
                    intent.putExtra("nature_name", nature.get(position).getNatureName());
                    holder.itemView.getContext().startActivity(intent);
                } else if (nature.get(position).getLockUnlockStatus().equals(1)) {
                    Intent intent = new Intent(context, CreativityAffirmationActivityNew.class);
                    intent.putExtra("song", nature.get(position).getSongs());
                    holder.itemView.getContext().startActivity(intent);
                }

            }
        });
        Log.e("songs", nature.get(position).getSongs());
    }

    @Override
    public int getItemCount() {
        return nature.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image;

        public itemHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.nature_lib_drop);
        }
    }
}
