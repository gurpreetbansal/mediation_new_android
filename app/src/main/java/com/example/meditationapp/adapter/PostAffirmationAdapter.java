package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.PostAffirmationData;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAffirmationAdapter extends RecyclerView.Adapter<PostAffirmationAdapter.itemView> {

    Context context;
    List<PostAffirmationData> data;
    OnitemClickListener onitemClickListener;

    public interface OnitemClickListener {
        void onItemClick(int position);

        void onLongClick(int position);

        void onfavourite(int position, boolean b);

    }

    public void setOnitemClickListener(OnitemClickListener clickListener) {
        onitemClickListener = clickListener;
    }

    public PostAffirmationAdapter(Context context, List<PostAffirmationData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.affirmationitem, parent, false);
        return new itemView(view, onitemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull itemView holder, int position) {
        Picasso.get()
                .load(R.mipmap.weight_loss_bg_row)
                .into(holder.ll_first);

        holder.affirmation_text.setText(data.get(position).getSongsTitle());

        if (data.get(position).getFavriteStatus().equals(0)) {
            holder.grey_heart.setVisibility(View.VISIBLE);
            holder.pink_heart.setVisibility(View.GONE);
        } else {
            holder.grey_heart.setVisibility(View.GONE);
            holder.pink_heart.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class itemView extends RecyclerView.ViewHolder {

        CustomBoldtextView affirmation_text;
        AppCompatImageView ll_first;
        ImageView grey_heart, pink_heart;

        public itemView(@NonNull View itemView, final OnitemClickListener listener) {
            super(itemView);

            affirmation_text = itemView.findViewById(R.id.affirmation_text);
            ll_first = itemView.findViewById(R.id.ll_first);
            pink_heart = itemView.findViewById(R.id.pink_heart);
            grey_heart = itemView.findViewById(R.id.grey_heart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onLongClick(position);
                        }
                    }
                    return false;
                }
            });

            pink_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            pink_heart.setVisibility(View.GONE);
                            grey_heart.setVisibility(View.VISIBLE);
                            listener.onfavourite(position, false);
                        }
                    }
                }
            });

            grey_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            pink_heart.setVisibility(View.VISIBLE);
                            grey_heart.setVisibility(View.GONE);
                            listener.onfavourite(position, true);
                        }
                    }
                }
            });

        }
    }
}
