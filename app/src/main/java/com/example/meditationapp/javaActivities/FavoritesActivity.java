package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.FavoritesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.GetFavoritesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.SubFavoritesModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.adapter.FavoriteAdapter.FavoritesCategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favListRV;
    String cat_ID;
    //    String userID = "287";
    String userID;
    String mypreference = "mypref", user_id = "user_id";
    ApiInterface apiInterface;
    List<FavoritesModelClass> favoritesModelClasses;
    List<SubFavoritesModelClass> subFavoritesModelClasses;
    ImageView img_back_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favorites_fragment);

        favListRV = findViewById(R.id.favList_RV);
        img_back_two = findViewById(R.id.img_back_two);

        SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

        img_back_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        cat_ID = getIntent().getStringExtra("cat_id");

        getCategoryData(userID);
    }

    public void getCategoryData(String userID) {

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetFavoritesModelClass> call = apiInterface.getFavorites(userID);

        call.enqueue(new Callback<GetFavoritesModelClass>() {
            @Override
            public void onResponse(Call<GetFavoritesModelClass> call, Response<GetFavoritesModelClass> response) {

                if (response.isSuccessful()) {

                    GetFavoritesModelClass resorce = response.body();
                    assert resorce != null;
                    if (resorce.getSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoritesActivity.this);
                        favListRV.setLayoutManager(linearLayoutManager);
                        FavoritesCategoryAdapter favoritesCategoryAdapter = new FavoritesCategoryAdapter(FavoritesActivity.this, resorce.getData());
                        favListRV.setAdapter(favoritesCategoryAdapter);

//                        Toast.makeText(FavoritesActivity.this, ""+resorce.getMessages(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(FavoritesActivity.this, "" + resorce.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FavoritesActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetFavoritesModelClass> call, Throwable t) {
                Toast.makeText(FavoritesActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
