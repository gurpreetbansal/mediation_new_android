package com.example.meditationapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditationapp.R;

public class PromoActivity extends AppCompatActivity {
ImageView img_okay;
TextView txt_terms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promo_activity);
        img_okay=(ImageView)findViewById(R.id.img_okay);
        txt_terms=(TextView) findViewById(R.id.txt_terms);
        img_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent terms=new Intent (PromoActivity.this, Terms_And_Conditions_Acxtivity.class);
                startActivity(terms);
            }
        });
    }
}
