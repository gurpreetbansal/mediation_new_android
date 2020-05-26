package com.example.meditationapp.JavaFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.RecordCategoryModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.adapter.InterestAdapter;
import com.example.meditationapp.adapter.RecordcCategoryAdapter;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;
import com.example.meditationapp.javaActivities.FavoritesActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecordFragmentNew extends Fragment {

    String userID;
    String mypreference = "mypref", user_id = "user_id";
    RecyclerView recyclerView;
    CustomBoldtextView playall;
    RecordCategoryModelClass resource;

    public RecordFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_recording_fragment, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

        recyclerView = view.findViewById(R.id.recording_categoryRecycler);
        playall = view.findViewById(R.id.recording_playall);

        playall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreativityAffirmationActivityNew.class);
//                intent.putExtra("demo","https://clientstagingdev.com/meditation/public/voice/1586425636.mp3");
                intent.putStringArrayListExtra("playlist", (ArrayList<String>) resource.getData().getPlayall());
                startActivity(intent);
            }
        });

        LinearLayoutManager llManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llManager);

        Log.e("id", userID);

        getData(userID);

        return view;
    }

    public void getData(String user_id) {
        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<RecordCategoryModelClass> call = apiInterface.getRecorCategory(user_id);
        call.enqueue(new Callback<RecordCategoryModelClass>() {
            @Override
            public void onResponse(Call<RecordCategoryModelClass> call, Response<RecordCategoryModelClass> response) {
                if (response.isSuccessful()) {
                    resource = response.body();
                    if (resource.getSuccess()) {

                        RecordcCategoryAdapter adapter = new RecordcCategoryAdapter(getActivity(), resource.getData().getCategory());
                        recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), resource.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecordCategoryModelClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
