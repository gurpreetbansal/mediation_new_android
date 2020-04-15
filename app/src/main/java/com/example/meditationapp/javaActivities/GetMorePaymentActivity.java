package com.example.meditationapp.javaActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.R;
import com.google.gson.JsonObject;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class GetMorePaymentActivity extends AppCompatActivity {

    private static final int PAY_PAL_REQUEST_CODE = 121;
    private CustomBoldtextView txt_terms_get,txt_year;

    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.CLIENT_ID);


    String amount = "";

    @Override
    protected void onDestroy() {
        stopService(new Intent(GetMorePaymentActivity.this,PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getmore_activity);

        txt_terms_get=findViewById(R.id.txt_terms_get);
        txt_year=findViewById(R.id.txt_year);

        txt_terms_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(GetMorePaymentActivity.this,TermsAndConditionActivity.class);
               startActivity(intent);
            }
        });
        Intent intent = new Intent(GetMorePaymentActivity.this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(intent);


      txt_year.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              processPayment();
          }
      });




    }

    private void processPayment() {


        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal("150"),"USD",
                "Donate for Edmtdev",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(GetMorePaymentActivity.this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAY_PAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAY_PAL_REQUEST_CODE){

            if (resultCode == Activity.RESULT_OK){

                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        String aa = confirm.toJSONObject().toString(4);
                        Log.e("DETAILS :  ",""+confirm +aa);
//                        String name = confirm.toJSONObject().getString("name");
//                        String email = confirm.toJSONObject().getString("email_id");
//                        String id = confirm.toJSONObject().getString("id");
//                        Log.e("NAME",name);
//                        Log.e("EMAil",email);
//                        Log.e("ID",id);

                        Log.i("TAG", confirm.toJSONObject().toString(4));
                        Log.i("TAG", confirm.getPayment().toJSONObject().toString(4));//                        String PaymentDetail = configuration.toJsonObject.toString(4);
//                        startActivity(new Intent(GetMorePaymentActivity.this,GetMorePaymentActivity.class));
                        Toast.makeText(this, "Payment Confirmation info received from PayPal", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (requestCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
