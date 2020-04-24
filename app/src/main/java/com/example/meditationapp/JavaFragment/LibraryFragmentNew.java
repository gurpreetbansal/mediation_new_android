package com.example.meditationapp.JavaFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.myapplication.fragment.RecordFragment;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetHomeResponse;
import com.example.meditationapp.ModelClasses.HomeData;
import com.example.meditationapp.ModelClasses.InterestedData;
import com.example.meditationapp.ModelClasses.RandomData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.My_FavoritesActivity;
import com.example.meditationapp.adapter.CategoryAdapter;
import com.example.meditationapp.adapter.InterestAdapter;
import com.example.meditationapp.adapter.NatureAdapter;
import com.imarkinfotech.slowme.utilityClasses.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meditationapp.javaActivities.HomeActivitynew.img_bottom_account;
import static com.example.meditationapp.javaActivities.HomeActivitynew.img_bottom_lib;
import static com.example.meditationapp.javaActivities.HomeActivitynew.img_bottom_record;
import static com.example.meditationapp.javaActivities.HomeActivitynew.img_bottom_sound;

public class LibraryFragmentNew extends Fragment {

    CustomBoldtextView ll_weight_lib,txt_home_my_recording,txt_home_my_favourite;
    LinearLayout ll_weight_lib_two;
    RecyclerView interestRecyclerView, natureRecyclerView,categoryRecyclerView;
    String userID;
    String mypreference = "mypref", user_id = "user_id";
    ApiInterface apiInterface;
    GetHomeResponse resource;
    RelativeLayout progressBar;
    ImageView bigMainImage;
    RandomData randomData;

    public LibraryFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lib, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

        progressBar = view.findViewById(R.id.lib_frag__prog_rl);
//        ll_weight_lib = view.findViewById(R.id.ll_weight_lib);
//        ll_weight_lib_two = view.findViewById(R.id.ll_weight_lib_two);
        bigMainImage = view.findViewById(R.id.weight_lib_two_image);
        interestRecyclerView = view.findViewById(R.id.lib_interestRecyclerView);
        natureRecyclerView = view.findViewById(R.id.lib_natureRecyclerView);
        categoryRecyclerView = view.findViewById(R.id.lib_categoryRecyclerView);
        txt_home_my_recording = view.findViewById(R.id.txt_home_my_recording);
        txt_home_my_favourite = view.findViewById(R.id.txt_home_my_favourite);

        txt_home_my_recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_bottom_lib.setVisibility(View.GONE);
                img_bottom_sound.setVisibility(View.GONE);
                img_bottom_record.setVisibility(View.VISIBLE);
                img_bottom_account.setVisibility(View.GONE);

                RecordFragment recordFragment = new RecordFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,recordFragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        txt_home_my_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getActivity(), My_FavoritesActivity.class);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager llManager_interest = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        interestRecyclerView.setLayoutManager(llManager_interest);

        GridLayoutManager glManager_nature = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false);
        natureRecyclerView.setLayoutManager(glManager_nature);

        LinearLayoutManager llManager_allcat = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(llManager_allcat);

        getHomeData(userID, "2");

        return view;
    }

    public void getHomeData(String userID, String typeId) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetHomeResponse> call = apiInterface.getHome(userID, typeId);
        call.enqueue(new Callback<GetHomeResponse>() {
            @Override
            public void onResponse(Call<GetHomeResponse> call, Response<GetHomeResponse> response) {
                if (response.isSuccessful()) {
                    resource = response.body();
                    assert resource != null;
                    if (resource.getSuccess()) {

                        Picasso.get().load(resource.getData().getRandom().getImage()).into(bigMainImage);

                        InterestAdapter interestAdapter = new InterestAdapter(getActivity(),resource.getData().getInterested());
                        interestRecyclerView.setAdapter(interestAdapter);
                        Log.e("interest",String.valueOf(resource.getData().getInterested().size()));

                        NatureAdapter natureAdapter = new NatureAdapter(getActivity(),resource.getData().getNature());
                        natureRecyclerView.setAdapter(natureAdapter);
                        Log.e("nature",String.valueOf(resource.getData().getNature().size()));

                        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),resource.getData().getCategories());
                        categoryRecyclerView.setAdapter(categoryAdapter);
                        Log.e("interest",String.valueOf(resource.getData().getCategories().size()));

                        progressBar.setVisibility(View.GONE);
                    }
                }else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetHomeResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
