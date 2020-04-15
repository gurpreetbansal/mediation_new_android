package com.example.meditationapp.JavaFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetHomeResponse;
import com.example.meditationapp.ModelClasses.SoundModel.GetSoundAndScapeResponse;
import com.example.meditationapp.ModelClasses.SoundModel.MusicModelClass;
import com.example.meditationapp.ModelClasses.SoundModel.SoundScapeModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.CreativtyAffirmationsActivity;
import com.example.meditationapp.activities.GetMore_Activity;
import com.example.meditationapp.adapter.MusicAdapter;
import com.example.meditationapp.adapter.SoundScapeAdapter;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.example.meditationapp.javaActivities.GetMorePaymentActivity;
import com.example.meditationapp.javaActivities.RecyclerTouchListener;
import com.imarkinfotech.slowme.utilityClasses.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoundFragment extends Fragment {

    String userID;
    String mypreference = "mypref", user_id = "user_id";

    private ProgressBar progressBar;
    private RecyclerView soundScapeRV,musicRV;
    private ApiInterface apiInterface;
    private GetSoundAndScapeResponse resource;
    private LinearLayout ll_sound_search;
    private CustomBoldtextView soundScape_text,music_text;
    private List<SoundScapeModelClass> soundScapeModelClass;
    private List<MusicModelClass> musicModelClasses;

    public SoundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sound_fragment, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

        progressBar=view.findViewById(R.id.sound_progressBar);
        soundScapeRV=view.findViewById(R.id.soundFragment_soundScapeRV);
        musicRV=view.findViewById(R.id.soundFragment_musicRV);
        ll_sound_search=view.findViewById(R.id.ll_sound_search);
        soundScape_text=view.findViewById(R.id.soundScape_text);
        music_text=view.findViewById(R.id.music_text);

        progressBar.setVisibility(View.VISIBLE);
        ll_sound_search.setVisibility(View.INVISIBLE);
        soundScape_text.setVisibility(View.INVISIBLE);
        music_text.setVisibility(View.INVISIBLE);

        GridLayoutManager grid_sound = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false);
        soundScapeRV.setLayoutManager(grid_sound);

        GridLayoutManager grid_music = new GridLayoutManager(getActivity(),3,RecyclerView.VERTICAL,false);
        musicRV.setLayoutManager(grid_music);

        getData(userID);

        return view;
    }

    public void getData(String userID){

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetSoundAndScapeResponse> call = apiInterface.getMusicList(userID);

        call.enqueue(new Callback<GetSoundAndScapeResponse>() {
            @Override
            public void onResponse(Call<GetSoundAndScapeResponse> call, Response<GetSoundAndScapeResponse> response) {

                if (response.isSuccessful()){
                    resource = response.body();

                    if (resource.getSuccess()){

                        soundScapeModelClass = resource.getData().getSoundScopes();
                        musicModelClasses = resource.getData().getMusic();

                        progressBar.setVisibility(View.GONE);
                        ll_sound_search.setVisibility(View.VISIBLE);
                        soundScape_text.setVisibility(View.VISIBLE);
                        music_text.setVisibility(View.VISIBLE);

                        SoundScapeAdapter soundScapeAdapter = new SoundScapeAdapter(getActivity(),resource.getData().getSoundScopes());
                        soundScapeRV.setAdapter(soundScapeAdapter);

                        MusicAdapter musicAdapter = new MusicAdapter(getActivity(),resource.getData().getMusic());
                        musicRV.setAdapter(musicAdapter);




                     soundScapeRV.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), soundScapeRV, new RecyclerTouchListener.ClickListener() {
                         @Override
                         public void onClick(View view, int position) {

                             String nature = soundScapeModelClass.get(position).getSongs();
                             Log.e("SOUND_SCAPE_URL :  ", nature);


                             switch (position){
                                 case 0:
                                     Intent intent = new Intent(getActivity(), CreativityAffirmationActivityNew.class);
                                     intent.putExtra("song", nature);
                                     startActivity(intent);
                                     break;
                                 case 1:
                                     Intent intent1 = new Intent(getActivity(), GetMorePaymentActivity.class);
                                     startActivity(intent1);
                                     break;
                                 case 2:
                                     Intent intent2 = new Intent(getActivity(), CreativityAffirmationActivityNew.class);
                                     intent2.putExtra("song", nature);
                                     startActivity(intent2);
                                     break;
                                 case 3:
                                     Intent intent3 = new Intent(getActivity(), GetMorePaymentActivity.class);
                                     startActivity(intent3);
                                     break;
                                 case 4:
                                     Intent intent4 = new Intent(getActivity(), GetMorePaymentActivity.class);
                                     startActivity(intent4);
                                     break;
                                 case 5:
                                     Intent intent5 = new Intent(getActivity(), GetMorePaymentActivity.class);
                                     startActivity(intent5);
                                     break;
                                 case 6:
                                     Intent intent6 = new Intent(getActivity(), CreativityAffirmationActivityNew.class);
                                     intent6.putExtra("song", nature);
                                     startActivity(intent6);
                                     break;
                             }

                         }

                         @Override
                         public void onLongClick(View view, int position) {

                         }
                     }));

                        musicRV.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), musicRV, new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {

                                String natureMusic = musicModelClasses.get(position).getSongs();
                                Log.e("MUSIC_SOUND_URL :  ", natureMusic);

                                Intent intent = new Intent(getActivity(), GetMorePaymentActivity.class);
                                startActivity(intent);


                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));




                    }
                    else {
                        Toast.makeText(getActivity(), resource.getMessages(), Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<GetSoundAndScapeResponse> call, Throwable t) {

                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
