package com.example.meditationapp.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetVoiceData;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VoiceAdapter extends RecyclerView.Adapter<VoiceAdapter.ItemView> {
    List<GetVoiceData> voiceData;
    Context context;
    Integer[] id_selected;
    Integer[] id_unselected;
    int index = 0, a = 0;
    Boolean pause = false;

    OnitemClickListener onitemClickListener;

    public interface OnitemClickListener {
        void startPlayer(String url, int position, Boolean pause);
    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public VoiceAdapter(List<GetVoiceData> voiceData, Context context, Integer[] id_selected, Integer[] id_unselected) {
        this.voiceData = voiceData;
        this.context = context;
        this.id_selected = id_selected;
        this.id_unselected = id_unselected;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voice_data_item, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemView holder, final int position) {

        if (a >= 1) {
            if (index == position) {
                holder.ll.setBackgroundResource(id_selected[position]);
            } else {
                holder.ll.setBackgroundResource(id_unselected[position]);
                holder.play_image.setImageResource(R.mipmap.play_btn);
            }
        }

        holder.name.setText(voiceData.get(position).getName());

        Picasso.get()
                .load(voiceData.get(position).getFlag())
                .into(holder.flag_image);

        Picasso.get()
                .load(voiceData.get(position).getImage())
                .into(holder.image);


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a > voiceData.size()) {
                    if (index == position) {
                        if (!pause) {
                            pause = true;
                        }else {
                            pause=false;
                        }
                        Log.e("pause a","pause true");
                    } else {
                        pause = false;

                        Log.e("pause a","pause false");
                    }
                }

                index = position;
                notifyDataSetChanged();
                if (pause){
                    holder.play_image.setImageResource(R.mipmap.play_btn);
                }else {
                    holder.play_image.setImageResource(R.mipmap.pause);
                }

                String url = voiceData.get(position).getVoices();
                if (onitemClickListener != null) {
                    onitemClickListener.startPlayer(url, position,pause);
                }

            }
        });

        a++;
        Log.e("value",String.valueOf(a));
        Log.e("value",String.valueOf(voiceData.size()));

    }

    @Override
    public int getItemCount() {
        return voiceData.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        LinearLayout ll;
        CustomBoldtextView name;
        CircleImageView image;
        ImageView flag_image, play_image;

        public ItemView(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.voice_data_item_bg);
            name = itemView.findViewById(R.id.voice_data_item_name);
            image = itemView.findViewById(R.id.voice_data_item_img);
            flag_image = itemView.findViewById(R.id.voice_data_item_flag_img);
            play_image = itemView.findViewById(R.id.voice_data_item_play_img);
        }

    }

}
