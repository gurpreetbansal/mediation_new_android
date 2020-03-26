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
import com.example.meditationapp.ModelClasses.GetResponsePricyAndPolicy;
import com.example.meditationapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyActivity extends BaseActivity {

    private CustomBoldtextView privacy_title_txt;
    private ImageView img_privacy_back;

    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private WebView webView;
    String url = "https://selfpause.com/privacy-policy/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_fragment);

        privacy_title_txt=findViewById(R.id.privacy_title_txt);
        webView=findViewById(R.id.webView_policy_Txt_view);
        img_privacy_back=findViewById(R.id.img_privacy_back);

        img_privacy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        showWebPage();

        progressDialog = new ProgressDialog(PrivacyPolicyActivity.this);
        progressDialog.setMessage("Please wait...");
        showDialog();


        apiInterface= RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetResponsePricyAndPolicy> call=apiInterface.privacyAndPolicy();

        call.enqueue(new Callback<GetResponsePricyAndPolicy>() {
            @Override
            public void onResponse(Call<GetResponsePricyAndPolicy> call, Response<GetResponsePricyAndPolicy> response) {

                GetResponsePricyAndPolicy getResponsePricyAndPolicy=response.body();

                privacy_title_txt.setText(getResponsePricyAndPolicy.getData().getTitle());

                hideDialog();

//                String descText=getResponsePricyAndPolicy.getData().getDescription();
//                final Spanned spannedText= Html.fromHtml(descText);
//
//                privacy_description_Txt_view.setText(spannedText);
            }

            @Override
            public void onFailure(Call<GetResponsePricyAndPolicy> call, Throwable t) {

                t.printStackTrace();
                hideDialog();

            }
        });

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("https://selfpause.com/privacy-policy/");



    }

    private void showWebPage(){

        progressDialog = new ProgressDialog(PrivacyPolicyActivity.this);
        progressDialog.setMessage("Loading website.......");
        webView.setWebViewClient(new ProgressDialogClass(progressDialog,getApplicationContext()));
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
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
