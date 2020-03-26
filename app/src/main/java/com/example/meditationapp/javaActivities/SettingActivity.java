package com.example.meditationapp.javaActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.LogoutModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HelpCenter_Activity;
import com.example.meditationapp.activities.NotificationActivity;
import com.example.meditationapp.activities.SupportActivity;

public class SettingActivity extends BaseActivity {

    private CustomBoldtextView txt_logout, txt_privacy, txt_terms_condition, txt_help_center, txt_support, txt_account, txt_notification;
    private ImageView img_account_back;

    private ApiInterface apiInterface;
    LogoutModelClass logoutModelClass = new LogoutModelClass();


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

                startActivity(new Intent(SettingActivity.this, SupportActivity.class));

            }
        });


    }

}
