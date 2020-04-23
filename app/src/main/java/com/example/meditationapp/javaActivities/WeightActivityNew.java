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
import com.example.meditationapp.ModelClasses.GetAffirmation;
import com.example.meditationapp.ModelClasses.GetProfileResponse;
import com.example.meditationapp.ModelClasses.PostAffirmation;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.WeightActivity;
import com.example.meditationapp.adapter.AffirmationAdapter;
import com.example.meditationapp.adapter.PostAffirmationAdapter;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeightActivityNew extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView add_affirmation;
    ApiInterface apiInterface;
    GetAffirmation resource;
    String userID, categoryId;
    PostAffirmation postAffirmation;
    MultipartBody.Part part = null;
    String mypreference = "mypref", user_id = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_fragment);

        recyclerView = findViewById(R.id.affirmation_recyclerView);
        add_affirmation = findViewById(R.id.add_affirmation);

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = pref.getString(user_id, "");

        getAffirmation("191", "47");

        add_affirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), "191");
                RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "title");
                RequestBody categoryId = RequestBody.create(MediaType.parse("text/plain"), "47");
//                RequestBody.create(MediaType.parse("text/plain"), "image-type");

                addAffirmation(userId, title, categoryId, part);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(WeightActivityNew.this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void getAffirmation(String userID, String categoryId) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetAffirmation> call = apiInterface.requestAffirmation(userID, categoryId);
        call.enqueue(new Callback<GetAffirmation>() {
            @Override
            public void onResponse(Call<GetAffirmation> call, Response<GetAffirmation> response) {
                if (response.isSuccessful()) {
                    resource = response.body();
                    assert resource != null;
                    if (resource.getSuccess()) {
                        AffirmationAdapter adapter = new AffirmationAdapter(WeightActivityNew.this, resource.getData());
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(WeightActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WeightActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAffirmation> call, Throwable t) {
                Toast.makeText(WeightActivityNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addAffirmation(RequestBody userId, RequestBody title, RequestBody categoryId, MultipartBody.Part part) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<PostAffirmation> call = apiInterface.postAffirmation(userId, title, categoryId, part);

        call.enqueue(new Callback<PostAffirmation>() {
            @Override
            public void onResponse(Call<PostAffirmation> call, Response<PostAffirmation> response) {

                if (response.isSuccessful()) {
                    postAffirmation = response.body();
                    assert postAffirmation != null;
                    if (postAffirmation.getSuccess()) {
                        PostAffirmationAdapter adapter = new PostAffirmationAdapter(WeightActivityNew.this, postAffirmation.getData());
                        recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(WeightActivityNew.this, postAffirmation.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WeightActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostAffirmation> call, Throwable t) {
                Toast.makeText(WeightActivityNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
