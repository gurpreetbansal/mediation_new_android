package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.FavoritesModelClass;
import com.example.meditationapp.ModelClasses.FavoriteModelClass.GetFavoritesModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.adapter.FavoritesCategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favListRV;
    String cat_ID;
    String userID;
    String mypreference = "mypref", user_id = "user_id";
    ApiInterface apiInterface;
    List<FavoritesModelClass> favoritesModelClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favorites_fragment);

        favListRV = findViewById(R.id.favList_RV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoritesActivity.this,LinearLayoutManager.VERTICAL,false);
        favListRV.setLayoutManager(linearLayoutManager);

        SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

//        cat_ID = getIntent().getStringExtra("cat_id");

    }

    public void getCategoryData(String userID){

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetFavoritesModelClass> call = apiInterface.getFavorites(user_id);

        call.enqueue(new Callback<GetFavoritesModelClass>() {
            @Override
            public void onResponse(Call<GetFavoritesModelClass> call, Response<GetFavoritesModelClass> response) {

                if (response.isSuccessful()){

                    GetFavoritesModelClass resorce = response.body();
                    assert resorce != null;
                    if (resorce.getSuccess()){

                        FavoritesCategoryAdapter favoritesCategoryAdapter = new FavoritesCategoryAdapter(FavoritesActivity.this,favoritesModelClasses);
                        favListRV.setAdapter(favoritesCategoryAdapter);

                        favListRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), favListRV, new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {

                                Toast.makeText(FavoritesActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
                    }
                    else {
                        Toast.makeText(FavoritesActivity.this, ""+resorce.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(FavoritesActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetFavoritesModelClass> call, Throwable t) {
                Toast.makeText(FavoritesActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
