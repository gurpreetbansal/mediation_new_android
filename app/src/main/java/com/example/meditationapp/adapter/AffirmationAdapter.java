package com.example.meditationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetAffirmationData;
import com.example.meditationapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AffirmationAdapter extends RecyclerView.Adapter<AffirmationAdapter.itemView> {

    Context context;
    List<GetAffirmationData> data;

//    OnViewClickListener onViewClickListener;
//
//    public interface OnViewClickListener {
//        void startRecording();
//        void stopRecording();
//    }
//
//    public void setOnViewClickListener(OnViewClickListener onViewClickListener){
//        this.onViewClickListener = onViewClickListener;
//    }

    public AffirmationAdapter(Context context, List<GetAffirmationData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.affirmationitem, parent, false);
        return new itemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemView holder, int position) {
        Picasso.get()
                .load(R.mipmap.weight_loss_bg_row)
                .into(holder.ll_first);

        holder.affirmation_text.setText(data.get(position).getSongsTitle());

//        holder.ll_first.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
////                        AppLog.logString("Start Recording");
////                        notifyDataSetChanged();
//                        if (onViewClickListener != null) {
//                            onViewClickListener.startRecording();
//                        }
//                        return true;
//                    case MotionEvent.ACTION_UP:
////                        notifyDataSetChanged();
////                        AppLog.logString("stop Recording");
//                        onViewClickListener.stopRecording();
//                        break;
//                }
//
//                return false;
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class itemView extends RecyclerView.ViewHolder {

        CustomBoldtextView affirmation_text;
        AppCompatImageView ll_first;

        public itemView(@NonNull View itemView) {
            super(itemView);

            affirmation_text = itemView.findViewById(R.id.affirmation_text);
            ll_first = itemView.findViewById(R.id.ll_first);

        }

    }
}
