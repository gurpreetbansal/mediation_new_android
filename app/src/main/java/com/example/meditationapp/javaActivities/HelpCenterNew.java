package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.Custom_Widgets.CustomRegularTextView;
import com.example.meditationapp.ModelClasses.GetSupportResponse;
import com.example.meditationapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpCenterNew extends BaseActivity {

    EditText title;
    CustomBoldEditText description;
    CustomRegularTextView submit;
    CustomBoldtextView title_txt_support;
    ImageView img_back_support;
    ApiInterface apiInterface;
    String userID;
    String mypreference = "mypref", user_id = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_fragment);

        title = findViewById(R.id.ed_addtitle);
        description = findViewById(R.id.ed_add_des);
        submit = findViewById(R.id.txt_sumbit);
        title_txt_support=findViewById(R.id.title_txt_support);
        img_back_support=findViewById(R.id.img_back_support);

        img_back_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HelpCenterNew.this,SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        title_txt_support.setText(getString(R.string.help_center));

        SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuery(userID, title.getText().toString(), description.getText().toString());
            }
        });
    }

    public void submitQuery(String userID, String title, String description) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetSupportResponse> call = apiInterface.sendQuery(userID, title, description);

        call.enqueue(new Callback<GetSupportResponse>() {
            @Override
            public void onResponse(Call<GetSupportResponse> call, Response<GetSupportResponse> response) {
                GetSupportResponse resource = response.body();
                assert resource != null;
                if (resource.getSuccess()) {
                    Toast.makeText(HelpCenterNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(HelpCenterNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSupportResponse> call, Throwable t) {
                Toast.makeText(HelpCenterNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
