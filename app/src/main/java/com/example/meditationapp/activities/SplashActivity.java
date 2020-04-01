package com.example.meditationapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.LoginActivityNew;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

public class SplashActivity extends AppCompatActivity {

    String  mypreference = "mypref",user_id = "user_id",email = "email";
    AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.getStatusBarColor();
        ContextCompat.getColor(this, R.color.white);


        setContentView(R.layout.splash_activity);



        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        pref.getString(user_id,"");
        pref.getString(email,"");

        if (pref.getString(user_id, "").equals("")){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(SplashActivity.this,LoginActivityNew.class);
                    startActivity(intent);
                    finish();

                }
            },3000);

        }
        else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();


                }
            },3000);

        }

//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
//                updateWithTocken(newAccessToken);
//            }
//        };
//
//        updateWithTocken(AccessToken.getCurrentAccessToken());



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        },2500);
    }

    private void updateWithTocken(AccessToken currentAccessTocken){

        if (currentAccessTocken != null){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            },3000);

        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(SplashActivity.this, LoginActivityNew.class);
                    startActivity(intent);
                    finish();

                }
            },3000);
        }

    }
}
