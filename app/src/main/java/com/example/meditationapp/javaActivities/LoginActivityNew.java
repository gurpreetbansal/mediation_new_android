package com.example.meditationapp.javaActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.LoginModelClass;
import com.example.meditationapp.ModelClasses.LoginSendData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HomeActivity;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityNew extends BaseActivity {

    com.example.meditationapp.Custom_Widgets.CustomBoldEditText ed_password, ed_email;
    com.example.meditationapp.Custom_Widgets.CustomRegularTextView btn_login;
    CustomBoldtextView btn_signup;
    ApiInterface apiInterface;
    private LoginSendData loginSendData = new LoginSendData();

    String email_txt, password_txt, device_type = "Android";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email = findViewById(R.id.login__email);
        ed_password = findViewById(R.id.login__password);
        btn_login = findViewById(R.id.login__txt_log_in);
        btn_signup = findViewById(R.id.txt_sign_up);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivityNew.this, SignupActivityNew.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_txt = ed_email.getText().toString();
                password_txt = ed_password.getText().toString();
                loginSendData.setEmail(email_txt);
                loginSendData.setPassword(password_txt);
                loginSendData.setDeviceToken(UUID.randomUUID().toString());
                loginSendData.setDeviceType(device_type);

                if (validateName(email_txt, ed_email, "email is required")) {
                    return;
                }
                if (validateName(password_txt, ed_password, "password is required")) {
                    return;
                }
                if (validatePassword(password_txt, ed_password, "pssword must be atleast 6 characters")) {
                    return;
                }
                  showDialog();
//                    Log.e("email+", loginSendData.getEmail());
                retrofitData();
            }
        });


    }

    public void retrofitData() {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<LoginModelClass> call = apiInterface.login(loginSendData);
        call.enqueue(new Callback<LoginModelClass>() {
            @Override
            public void onResponse(@NotNull Call<LoginModelClass> call, @NotNull Response<LoginModelClass> response) {
                if (response.isSuccessful()) {
                    LoginModelClass resource = response.body();
                    assert resource != null;
                    if (resource.getSuccess()) {
                        String code = resource.getCode();
                        String msg = resource.getMessages();

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user_id", resource.getData().getUserId());
                        editor.apply();

                        startActivity(new Intent(LoginActivityNew.this, HomeActivity.class));
                        Toast.makeText(LoginActivityNew.this, msg, Toast.LENGTH_SHORT).show();

                        hideDialog();

                        Log.e("Success Response++++", code + " " + msg);
                    } else {
                        Toast.makeText(LoginActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                        hideDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModelClass> call, Throwable t) {
                Log.e("Failure Response++++", t.getMessage());
                Toast.makeText(LoginActivityNew.this, t.toString(), Toast.LENGTH_SHORT).show();

                hideDialog();
            }
        });

    }

    private boolean validateName(String name, CustomBoldEditText nameET, String err_msg) {
        if (name.isEmpty()) {
            nameET.setError(err_msg);
            nameET.requestFocus();
            return true;
        }
        return false;
    }

    public void showDialog() {

        if(progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
