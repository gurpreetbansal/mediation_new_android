package com.example.meditationapp.javaActivities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.LoadingBar.ProgressDialogClass;
import com.example.meditationapp.ModelClasses.GetResponseTermsAndCondition;
import com.example.meditationapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionActivity extends BaseActivity {

    private CustomBoldtextView title_txt_view;
    private WebView webView;
    private ImageView img_back_terms;
    private ProgressDialog progressDialog;
    private ApiInterface apiInterface;

    String url = "https://selfpause.com/terms-conditions/";
//    TermsAndConditionModelClass termsAndConditionModelClass=new TermsAndConditionModelClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_conditions_fragment);

        title_txt_view=findViewById(R.id.title_txt_view);
        webView=findViewById(R.id.webView_Terms_Txt_view);
        img_back_terms=findViewById(R.id.img_back_terms);

        img_back_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        showWebPage();
        progressDialog = new ProgressDialog(TermsAndConditionActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        showDialog();

//        webView.loadUrl("https://selfpause.com/terms-conditions/");
//        setContentView(webView);

        apiInterface= RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetResponseTermsAndCondition> call=apiInterface.termsAndCondition();

        call.enqueue(new Callback<GetResponseTermsAndCondition>() {
            @Override
            public void onResponse(Call<GetResponseTermsAndCondition> call, Response<GetResponseTermsAndCondition> response) {

                GetResponseTermsAndCondition getResponseTermsAndCondition=response.body();

                title_txt_view.setText(getResponseTermsAndCondition.getData().getTitle());

                hideDialog();

//                description_Txt_view.setText(getResponseTermsAndCondition.getData().getDescription());

            }

            @Override
            public void onFailure(Call<GetResponseTermsAndCondition> call, Throwable t) {

                hideDialog();

            }
        });


    }


    private void showWebPage(){


        progressDialog = new ProgressDialog(TermsAndConditionActivity.this);
        progressDialog.setMessage("Loading website........");
        progressDialog.setCanceledOnTouchOutside(false);
        webView.setWebViewClient(new ProgressDialogClass(progressDialog,getApplicationContext()));
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);

//            WebSettings webSettings = webView.getSettings();
//            webSettings.setJavaScriptEnabled(true);
//            webView.loadUrl("https://selfpause.com/terms-conditions/");

//            hideDialog();

    }

    public void showDialog() {

        if(progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideDialog();
    }
}
