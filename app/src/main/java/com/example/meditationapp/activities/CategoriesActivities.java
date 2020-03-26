package com.example.meditationapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meditationapp.R;

public class CategoriesActivities extends AppCompatActivity implements View.OnClickListener {
    TextView img_next_cat;
    LinearLayout ll_weight,ll_professional,ll_stress,ll_relationa,ll_athletic,ll_health,ll_fine,ll_abun;
    String value;
    ImageView img_back_tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ategories_activity);
        img_next_cat=(TextView) findViewById(R.id.img_next_cat);
        ll_weight=(LinearLayout) findViewById(R.id.ll_weight);
        ll_professional=(LinearLayout) findViewById(R.id.ll_professional);
        ll_stress=(LinearLayout) findViewById(R.id.ll_stress);
        ll_relationa=(LinearLayout) findViewById(R.id.ll_relationa);
        ll_athletic=(LinearLayout) findViewById(R.id.ll_athletic);
        ll_health=(LinearLayout) findViewById(R.id.ll_health);
        ll_fine=(LinearLayout) findViewById(R.id.ll_fine);
        ll_abun=(LinearLayout) findViewById(R.id.ll_abun);
        img_back_tool=(ImageView) findViewById(R.id.img_back_tool);

        ll_weight.setOnClickListener(this);
        ll_professional.setOnClickListener(this);
        ll_stress.setOnClickListener(this);
        ll_relationa.setOnClickListener(this);
        ll_athletic.setOnClickListener(this);
        ll_health.setOnClickListener(this);
        ll_fine.setOnClickListener(this);
        ll_abun.setOnClickListener(this);
        img_back_tool.setOnClickListener(this);

        img_next_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cat=new Intent(CategoriesActivities.this,HomeActivity.class);
                startActivity(cat);
            }
        });
        img_back_tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.ll_weight:
                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg_three));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.profession_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));



//                value="WEIGHT_LOSS";
//                Intent ll_weight=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_weight.putExtra("CLICKED",value);
//                startActivity(ll_weight);
                break;

            case R.id.ll_professional:

                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg_two));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));


//                value="PROFESSIONAL";
//                Intent ll_professional=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_professional.putExtra("CLICKED",value);
//                startActivity(ll_professional);

                break;
            case R.id.ll_stress:


                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg_two));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//                value="STRESS";
//                Intent ll_stress=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_stress.putExtra("CLICKED",value);
//                startActivity(ll_stress);

                break;


            case R.id.ll_relationa:

                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg_two));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));

//                value="RELATION";
//                Intent ll_relationa=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_relationa.putExtra("CLICKED",value);
//                startActivity(ll_relationa);

                break;

            case R.id.ll_athletic:
                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethis_bg_two));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));




//                value="ATHLETIC";
//                Intent ll_athletic=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_athletic.putExtra("CLICKED",value);
//                startActivity(ll_athletic);

                break;

            case R.id.ll_health:
                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg_two));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//                value="HEALTH";
//                Intent ll_health=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_health.putExtra("CLICKED",value);
//                startActivity(ll_health);

                break;

            case R.id.ll_fine:

                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg_two));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));


//                value="FINE";
//                Intent ll_fine=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_fine.putExtra("CLICKED",value);
//                startActivity(ll_fine);

                break;

            case R.id.ll_abun:
                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abundance_bg_two));
//                value="ABUN";
//                Intent ll_abun=new Intent(CategoriesActivities.this,HomeActivity.class);
//                ll_abun.putExtra("CLICKED",value);
//                startActivity(ll_abun);

                break;



            default:
                break;
        }

    }
}
