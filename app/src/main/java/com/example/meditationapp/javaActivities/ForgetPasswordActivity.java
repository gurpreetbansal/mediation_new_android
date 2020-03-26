package com.example.meditationapp.javaActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.Custom_Widgets.CustomRegularTextView;
import com.example.meditationapp.ModelClasses.ForgetPasswordModel;
import com.example.meditationapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends BaseActivity {

    private CustomBoldtextView close_send_pass;
    private CustomBoldEditText forget_pass_emailET;
    private CustomRegularTextView send_forget_pass;
    ApiInterface apiInterface;
    private ForgetPasswordModel forgetPasswordModel = new ForgetPasswordModel();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        close_send_pass=findViewById(R.id.close_send_pass);
        forget_pass_emailET=findViewById(R.id.forget_pass_email);
        send_forget_pass=findViewById(R.id.send_forget_pass);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");

        close_send_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = forget_pass_emailET.getText().toString().trim();

                if (validateName(email, forget_pass_emailET, "email is required")) {
                    return;
                }

                forgetEmailData(email);

                showDialog();

            }
        });

    }

    public void forgetEmailData(String email){

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<ForgetPasswordModel> call=apiInterface.forgetPassword(email);

        call.enqueue(new Callback<ForgetPasswordModel>() {
            @Override
            public void onResponse(Call<ForgetPasswordModel> call, Response<ForgetPasswordModel> response) {

                forgetPasswordModel = response.body();

                if (forgetPasswordModel.getSuccess()){

                    Toast.makeText(ForgetPasswordActivity.this, forgetPasswordModel.getMessages(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgetPasswordActivity.this,LoginActivityNew.class));
                    finish();

                    hideDialog();
                }
                else {
                    Toast.makeText(ForgetPasswordActivity.this, forgetPasswordModel.getMessages(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordModel> call, Throwable t) {

                Toast.makeText(ForgetPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
