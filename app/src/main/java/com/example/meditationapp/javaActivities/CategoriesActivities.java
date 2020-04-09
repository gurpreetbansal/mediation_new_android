package com.example.meditationapp.javaActivities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.ModelClasses.GetCategoriesModelClass;
import com.example.meditationapp.ModelClasses.SetCategoriesModelClass;
import com.example.meditationapp.ModelClasses.SetCategoryResponse;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HomeActivity;
import com.example.meditationapp.adapter.CategoriesAdapter;
import com.rajesh.customcalendar.RecyclerItemClickListener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivities extends BaseActivity {
    CustomBoldtextView next_cat;
    LinearLayout ll_weight, ll_professional, ll_stress, ll_relationa, ll_athletic, ll_health, ll_fine, ll_abun;
    String value;
    ImageView img_back_tool;
    private RecyclerView recyclerView;
    String userId, typeId = "2";
    String mypreference = "mypref", user_id = "user_id";
    ApiInterface apiInterface;
    List<CategoriesModelClass> categoriesModelClasses;
    SetCategoriesModelClass setCategoriesModelClasses = new SetCategoriesModelClass();
    CategoriesAdapter categoriesAdapter;
    GetCategoriesModelClass getCategoriesModelClass;
    SetCategoryResponse setCategoryResponse = new SetCategoryResponse();

    List<Integer> data = new ArrayList<>();
    RecyclerItemClickListener mItemClickListener;
    ArrayDeque mDataset;

    ProgressDialog progressDialog;

    String text = "";
    String image = "";
    boolean clicked;


//    CategoriesModelClass categoriesModelClass=new CategoriesModelClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ategories_activity);

//        ll_weight=(LinearLayout) findViewById(R.id.ll_weight);
//        ll_professional=(LinearLayout) findViewById(R.id.ll_professional);
//        ll_stress=(LinearLayout) findViewById(R.id.ll_stress);
//        ll_relationa=(LinearLayout) findViewById(R.id.ll_relationa);
//        ll_athletic=(LinearLayout) findViewById(R.id.ll_athletic);
//        ll_health=(LinearLayout) findViewById(R.id.ll_health);
//        ll_fine=(LinearLayout) findViewById(R.id.ll_fine);
//        ll_abun=(LinearLayout) findViewById(R.id.ll_abun);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCanceledOnTouchOutside(false);
        showDialog();

        next_cat = findViewById(R.id.img_next_cat);
        img_back_tool = (ImageView) findViewById(R.id.img_back_tool);
        recyclerView = findViewById(R.id.recyclerView_cat);
//
//        ll_weight.setOnClickListener(this);
//        ll_professional.setOnClickListener(this);
//        ll_stress.setOnClickListener(this);
//        ll_relationa.setOnClickListener(this);
//        ll_athletic.setOnClickListener(this);
//        ll_health.setOnClickListener(this);
//        ll_fine.setOnClickListener(this);
//        ll_abun.setOnClickListener(this);
//        img_back_tool.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userId = pref.getString(user_id, "");

        getContentData(userId, typeId);


//        call.enqueue(new Callback<GetCategoriesModelClass>() {
//            @Override
//            public void onResponse(Call<GetCategoriesModelClass> call, Response<GetCategoriesModelClass> response) {
//
//                GetCategoriesModelClass getCategoriesModelClass=response.body();
//
////                categoriesModelClasses = new ArrayList<CategoriesModelClass>(Arrays.<CategoriesModelClass>asList((CategoriesModelClass) getCategoriesModelClass.getData()))
//
//                CategoriesAdapter categoriesAdapter=new CategoriesAdapter(categoriesModelClasses);
//                    recyclerView.setAdapter(categoriesAdapter);
//
////                GetCategoriesModelClass getCategoriesModelClass=response.body();
////
////                if (getCategoriesModelClass.getSuccess()){
////
////                    CategoriesAdapter categoriesAdapter=new CategoriesAdapter(CategoriesActivities.this,categoriesModelClassess);
////                    recyclerView.setAdapter(categoriesAdapter);
////                }
////                else {
////                    Toast.makeText(CategoriesActivities.this, ""+response.message(), Toast.LENGTH_SHORT).show();
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetCategoriesModelClass> call, Throwable t) {
//
//                Toast.makeText(CategoriesActivities.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


        next_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("list", data.toString());
                setCategoryResponse.setCategoryId(data);
                setCategoryResponse.setUserId(Integer.valueOf(userId));
                setContentData(setCategoryResponse);
            }
        });
        img_back_tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void getContentData(String userId, String typeId) {

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetCategoriesModelClass> call = apiInterface.getCategory(userId, typeId);

        call.enqueue(new Callback<GetCategoriesModelClass>() {
            @Override
            public void onResponse(final Call<GetCategoriesModelClass> call, final Response<GetCategoriesModelClass> response) {

                if (response.isSuccessful()) {

                    final GetCategoriesModelClass getCategoriesModelClass = response.body();
//                    Log.e("GET CONTENT +++++++", getCategoriesModelClass.getMessages());
                    Toast.makeText(CategoriesActivities.this, "" + getCategoriesModelClass.getMessages(), Toast.LENGTH_SHORT).show();

                    if (getCategoriesModelClass.getSuccess()) {

                        categoriesModelClasses = getCategoriesModelClass.getData();

                        hideDialog();
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CategoriesActivities.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        categoriesAdapter = new CategoriesAdapter(categoriesModelClasses, CategoriesActivities.this);
                        recyclerView.setAdapter(categoriesAdapter);

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
//                                CategoriesModelClass cmc = categoriesModelClasses.get(position);

                                Integer cmc1 = categoriesModelClasses.get(position).getId();
                                Boolean ok = categoriesModelClasses.get(position).isSelected();

//                    Log.e("CATEGORIES ID", cmc1.toString());

                                if (ok.equals(true)) {
                                    data.remove(categoriesModelClasses.get(position).getId());
//                        Toast.makeText(CategoriesActivities.this, "" + getCategoriesModelClass.getData().get(position).getName(), Toast.LENGTH_SHORT).show();

                                } else if (ok.equals(false)) {

                                    data.add(categoriesModelClasses.get(position).getId());
//                        Toast.makeText(CategoriesActivities.this, "" + getCategoriesModelClass.getData().get(position).getName(), Toast.LENGTH_SHORT).show();
                                }

                            }


                            @Override
                            public void onLongClick(View view, int position) {

                                Toast.makeText(CategoriesActivities.this, "" + getCategoriesModelClass.getData().get(position).getName(), Toast.LENGTH_SHORT).show();

                            }
                        }));


                    }
                }

            }

            @Override
            public void onFailure(Call<GetCategoriesModelClass> call, Throwable t) {
                Toast.makeText(CategoriesActivities.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

    }

    public void setContentData(final SetCategoryResponse setCategoryResponse) {

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<SetCategoriesModelClass> call = apiInterface.setCategory(setCategoryResponse);

        call.enqueue(new Callback<SetCategoriesModelClass>() {
            @Override
            public void onResponse(Call<SetCategoriesModelClass> call, Response<SetCategoriesModelClass> response) {

                if (response.isSuccessful()) {

                    setCategoriesModelClasses = response.body();

                    if (setCategoriesModelClasses.getSuccess()) {

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("category_selected", true);
                        editor.apply();
                        Log.e("list", data.toString());
                        Log.e("list", setCategoryResponse.getCategoryId().toString());

                        Intent cat = new Intent(CategoriesActivities.this, HomeActivity.class);
                        startActivity(cat);
                        finishAffinity();
                        Toast.makeText(CategoriesActivities.this, "" + setCategoriesModelClasses.getMessages(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<SetCategoriesModelClass> call, Throwable t) {

            }
        });
    }
//    public void recyclerData(){
//
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(CategoriesActivities.this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        CategoriesAdapter categoriesAdapter=new CategoriesAdapter(categoriesModelClasses);
//        recyclerView.setAdapter(categoriesAdapter);
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId() /*to get clicked view id**/) {
//            case R.id.ll_weight:
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg_three));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.profession_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//
//
//
////                value="WEIGHT_LOSS";
////                Intent ll_weight=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_weight.putExtra("CLICKED",value);
////                startActivity(ll_weight);
//                break;
//
//            case R.id.ll_professional:
//
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg_two));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//
//
////                value="PROFESSIONAL";
////                Intent ll_professional=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_professional.putExtra("CLICKED",value);
////                startActivity(ll_professional);
//
//                break;
//            case R.id.ll_stress:
//
//
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg_two));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
////                value="STRESS";
////                Intent ll_stress=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_stress.putExtra("CLICKED",value);
////                startActivity(ll_stress);
//
//                break;
//
//
//            case R.id.ll_relationa:
//
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg_two));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//
////                value="RELATION";
////                Intent ll_relationa=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_relationa.putExtra("CLICKED",value);
////                startActivity(ll_relationa);
//
//                break;
//
//            case R.id.ll_athletic:
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethis_bg_two));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//
//
//
//
////                value="ATHLETIC";
////                Intent ll_athletic=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_athletic.putExtra("CLICKED",value);
////                startActivity(ll_athletic);
//
//                break;
//
//            case R.id.ll_health:
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg_two));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
////                value="HEALTH";
////                Intent ll_health=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_health.putExtra("CLICKED",value);
////                startActivity(ll_health);
//
//                break;
//
//            case R.id.ll_fine:
//
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg_two));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abun_bg));
//
//
////                value="FINE";
////                Intent ll_fine=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_fine.putExtra("CLICKED",value);
////                startActivity(ll_fine);
//
//                break;
//
//            case R.id.ll_abun:
//                ll_weight.setBackgroundDrawable(getResources().getDrawable(R.mipmap.weight_loss_bg));
//                ll_professional.setBackgroundDrawable(getResources().getDrawable(R.mipmap.professional_bg));
//                ll_stress.setBackgroundDrawable(getResources().getDrawable(R.mipmap.stress_bg));
//                ll_relationa.setBackgroundDrawable(getResources().getDrawable(R.mipmap.relationship_bg));
//                ll_athletic.setBackgroundDrawable(getResources().getDrawable(R.mipmap.atlethic_bg));
//                ll_health.setBackgroundDrawable(getResources().getDrawable(R.mipmap.health_bg));
//                ll_fine.setBackgroundDrawable(getResources().getDrawable(R.mipmap.financial_bg));
//                ll_abun.setBackgroundDrawable(getResources().getDrawable(R.mipmap.abundance_bg_two));
////                value="ABUN";
////                Intent ll_abun=new Intent(CategoriesActivities.this,HomeActivity.class);
////                ll_abun.putExtra("CLICKED",value);
////                startActivity(ll_abun);
//
//                break;
//
//
//
//            default:
//                break;
//        }
//
//    }

    public void showDialog() {

        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
