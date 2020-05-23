package com.example.meditationapp.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.ModelClasses.MusicPlayerNature;
import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.example.meditationapp.utilityClasses.NatureSoundService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerNatureAdapter extends RecyclerView.Adapter<PlayerNatureAdapter.itemView> {

    Context context;
    List<MusicPlayerNature> nature;

    public PlayerNatureAdapter(Context context, List<MusicPlayerNature> nature) {
        this.context = context;
        this.nature = nature;
    }

    @NonNull
    @Override
    public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playe_nature_item, parent, false);
        return new itemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemView holder, int position) {
        Picasso.get().load(nature.get(position).getIcon()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreativityAffirmationActivityNew.img_vol_bar.setVisibility(View.VISIBLE);
                if (isMyServiceRunning(NatureSoundService.class)) {
                    holder.itemView.getContext().stopService(new Intent(context, NatureSoundService.class));
                } else {
                    Intent n_intent = new Intent(context, NatureSoundService.class);
                    n_intent.putExtra("nature_song", "https://clientstagingdev.com/meditation/public/voice/1586425636.mp3");
                    n_intent.putExtra("player", "Play");
                    holder.itemView.getContext().startService(n_intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return nature.size();
    }

    public class itemView extends RecyclerView.ViewHolder {

        AppCompatImageView image;

        public itemView(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_one);

        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
