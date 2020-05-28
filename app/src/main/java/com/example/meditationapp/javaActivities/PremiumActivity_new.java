package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.R;

public class PremiumActivity_new extends AppCompatActivity {

    CustomBoldtextView txt_terms_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getmore_activity);

        txt_terms_get = findViewById(R.id.txt_terms_get);

        txt_terms_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PremiumActivity_new.this,TermsAndConditionActivity.class);
                startActivity(intent);
            }
        });
    }
}
