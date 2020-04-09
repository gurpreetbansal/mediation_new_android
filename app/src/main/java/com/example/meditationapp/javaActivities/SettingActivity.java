package com.example.meditationapp.javaActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.LogoutModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HelpCenter_Activity;
import com.example.meditationapp.activities.LoginActivity;
import com.example.meditationapp.activities.NotificationActivity;
import com.example.meditationapp.activities.SupportActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SettingActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private CustomBoldtextView txt_logout, txt_privacy, txt_terms_condition, txt_help_center, txt_support, txt_account, txt_notification, txt_subscription;
    private ImageView img_account_back;

    private ApiInterface apiInterface;
    LogoutModelClass logoutModelClass = new LogoutModelClass();

    public static final int SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_fragment);

        txt_logout = findViewById(R.id.txt_logout);
        txt_privacy = findViewById(R.id.txt_privacy);
        txt_terms_condition = findViewById(R.id.txt_terms_condition);
        img_account_back = findViewById(R.id.img_account_first_back);
        txt_help_center = findViewById(R.id.txt_help_center);
        txt_support = findViewById(R.id.txt_support);
        txt_notification = findViewById(R.id.txt_notification);
        txt_subscription = findViewById(R.id.txt_sub);

        img_account_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txt_account = findViewById(R.id.txt_account);

        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LogoutActivity.class);
                startActivity(intent);

            }
        });

        txt_account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, AccountSettingActivityNew.class);
                startActivity(intent);
            }
        });

        txt_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        txt_terms_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, TermsAndConditionActivity.class);
                startActivity(intent);

            }
        });

        txt_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        txt_help_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingActivity.this, HelpCenter_Activity.class));

            }
        });

        txt_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingActivity.this, SupportActivity_new.class));

            }
        });

        txt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void goToLoginActivity() {
        startActivity(new Intent(SettingActivity.this, LoginActivityNew.class));
        finishAffinity();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
