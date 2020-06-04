package com.example.meditationapp.javaActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.UserPayModel.GetUserPayModelClass;
import com.example.meditationapp.ModelClasses.UserPayModel.UserPayModelClass;
import com.example.meditationapp.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionActivity extends AppCompatActivity {


    private static final int MONTHLY_PAY_PAL_REQUEST_CODE = 121;
    private static final int YEARLY_PAY_PAL_REQUEST_CODE = 131;
    private CustomBoldtextView txt_terms_get, txt_year, txt_month, txt_get_more, txt_get_full;
    int monthly_package_type = 1;
    int yearly_package_type = 2;
    String song, song_id, song_name;
    String userID;
    String mypreference = "mypref", user_id = "user_id";
    private static final String PAYMENT_TYPE = "Paypal";
    ApiInterface apiInterface;
    RelativeLayout payment_rl;
    GetUserPayModelClass getUserPayModelClass;
    List<UserPayModelClass> userPayModelClasses;
    String payment_status;
    boolean check;

    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(Config.CLIENT_ID);


    String amount = "";

    @Override
    protected void onDestroy() {
        stopService(new Intent(SubscriptionActivity.this, PayPalService.class));
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        txt_terms_get = findViewById(R.id.subcription_txt_terms_get);
        txt_year = findViewById(R.id.subcription_txt_year);
        txt_month = findViewById(R.id.subcription_txt_month);
        payment_rl = findViewById(R.id.subcription_payment_rl);
        txt_get_more = findViewById(R.id.subcription_txt_get_more);
        txt_get_full = findViewById(R.id.subcription_txt_get_full);

//        if (getIntent().getStringExtra("colorcode").equals("1")) {
//            payment_rl.getBackground().setTint(payment_rl.getResources().getColor(R.color.black));
//            txt_get_more.setText("Upgrade to Premium");
//            txt_get_more.setTextColor(getResources().getColor(R.color.white));
//            txt_terms_get.setTextColor(getResources().getColor(R.color.white));
//            txt_get_full.setVisibility(View.INVISIBLE);
//            txt_year.setBackgroundResource(R.drawable.curve_white_fill);
//            txt_month.setBackgroundResource(R.drawable.curve_white_fill);
//            txt_month.setTextColor(getResources().getColor(R.color.black));
//            txt_year.setTextColor(getResources().getColor(R.color.black));
//        }

        txt_terms_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this, TermsAndConditionActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = new Intent(SubscriptionActivity.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        startService(intent);

        song = getIntent().getStringExtra("song");
        song_id = getIntent().getStringExtra("nature_id");
        song_name = getIntent().getStringExtra("nature_name");

        SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = preferences.getString(user_id, "");


        txt_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                txt_year.setBackgroundResource(R.drawable.curve_darkgray_fill);
                yearlyProcessPayment();
            }
        });

        txt_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                txt_month.setBackgroundResource(R.drawable.curve_darkgray_fill);
                monthlyProcessPayment();
            }
        });
    }

    private void monthlyProcessPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal("7.99"), "USD",
                "Meditation", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(SubscriptionActivity.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        intent.putExtra("value", 1);
        startActivityForResult(intent, MONTHLY_PAY_PAL_REQUEST_CODE);
//        if (getIntent().getStringExtra("colorcode").equals("1")) {
            txt_month.setBackgroundResource(R.drawable.curve_gray_fill);
            txt_year.setBackgroundResource(R.drawable.curve_white_fill);
//        } else {
//            txt_month.setBackgroundResource(R.drawable.curve_darkgray_fill);
//            txt_year.setBackgroundResource(R.drawable.curve_gray_fill);
//        }

    }

    private void yearlyProcessPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal("150"), "USD",
                "Meditation", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(SubscriptionActivity.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        intent.putExtra("value", 2);
        startActivityForResult(intent, YEARLY_PAY_PAL_REQUEST_CODE);
//        if (getIntent().getStringExtra("colorcode").equals("1")) {
            txt_year.setBackgroundResource(R.drawable.curve_gray_fill);
            txt_month.setBackgroundResource(R.drawable.curve_white_fill);
//        } else {
//            txt_year.setBackgroundResource(R.drawable.curve_darkgray_fill);
//            txt_month.setBackgroundResource(R.drawable.curve_gray_fill);
//        }
    }

    public void payPalLogin(final String userID, String payment_type, String payment_id, String payment_amount, String payment_date,
                            String payment_plan_id, String payment_plan_name, String currency_code, String short_desp,
                            final String intent, Integer package_type) {

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetUserPayModelClass> call = apiInterface.getUserPayData(userID, payment_type, payment_id, payment_amount, payment_date,
                payment_plan_id, payment_plan_name, currency_code, short_desp, intent, package_type,"","subscription");

        call.enqueue(new Callback<GetUserPayModelClass>() {
            @Override
            public void onResponse(Call<GetUserPayModelClass> call, Response<GetUserPayModelClass> response) {

                GetUserPayModelClass resource = response.body();
                assert resource != null;
                if (resource.getSuccess()) {

//                     SoundFragment soundFragment = new SoundFragment();
//                     FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                     fragmentTransaction.replace(R.id.container,soundFragment);
//                     fragmentTransaction.addToBackStack("");
//                     fragmentTransaction.commit();

                    refreshActivity();
                    SharedPreferences sharedPreferences = getSharedPreferences("myPref", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("pref","");
                    editor.putBoolean("Payment", false);
                    editor.apply();

//                     Toast.makeText(GetMorePaymentActivity.this, "" +resource.getMessages(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SubscriptionActivity.this, "" + resource.getMessages(), Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<GetUserPayModelClass> call, Throwable t) {
                Toast.makeText(SubscriptionActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MONTHLY_PAY_PAL_REQUEST_CODE || requestCode == YEARLY_PAY_PAL_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                String result = data.getStringExtra("response");


                Log.e("RESULT  ", " " + result);

                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {

                        int value = getIntent().getExtras().getInt("value");

                        String full_details = confirm.toJSONObject().toString(4);
                        Log.e("DETAILS :  ", "" + full_details);

                        String paymentId = confirm.toJSONObject().getJSONObject("response").getString("id");
                        String payment_date = confirm.toJSONObject().getJSONObject("response").getString("create_time");
                        String intent = confirm.toJSONObject().getJSONObject("response").getString("intent");
                        String state = confirm.toJSONObject().getJSONObject("response").getString("state");
                        String amount = confirm.getPayment().toJSONObject().getString("amount");
                        String currency_code = confirm.getPayment().toJSONObject().getString("currency_code");
                        String short_desp = confirm.getPayment().toJSONObject().getString("short_description");


                        String payment_client = confirm.getPayment().toJSONObject().toString();

                        Log.e("PAYMENT_ID", "" + paymentId);
                        Log.e("PAYMENT_DATE", "" + payment_date);
                        Log.e("PAYMENT_INTENT", "" + intent);
                        Log.e("PAYMENT_STATE", "" + state);
                        Log.e("PAYMENT_AMOUNT", "" + amount);
                        Log.e("PAYMENT_CURRENCY_CODE", "" + currency_code);
                        Log.e("PAYMENT_SHORT_DESP", "" + short_desp);
                        Log.e("PAYMENT_CLIENT : ", "" + payment_client);

                        Intent intent1 = getIntent();
                        int type = intent1.getIntExtra("value", 2);

                        if (type == 1) {
                            payPalLogin(userID, PAYMENT_TYPE, paymentId, amount, payment_date, song_id, song_name, currency_code, short_desp, intent, type);

                        }
                        if (type == 2) {
                            payPalLogin(userID, PAYMENT_TYPE, paymentId, amount, payment_date, song_id, song_name, currency_code, short_desp, intent, type);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void refreshActivity() {
        Intent i = new Intent(SubscriptionActivity.this, HomeActivitynew.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}