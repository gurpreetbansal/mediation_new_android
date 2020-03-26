package com.example.meditationapp.javaActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetEditProfileResponse;
import com.example.meditationapp.ModelClasses.GetProfileResponse;
import com.example.meditationapp.R;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSettingActivityNew extends BaseActivity {
    ImageView btn_back;
    String userID;
    ApiInterface apiInterface;
    String mypreference = "mypref", user_id = "user_id";
    CustomBoldtextView tv_email, firstname_edit, password_edit, firstname_change, password_change, save_changes, password_title;
    LinearLayout new_password_container;
    GetProfileResponse resource;
    RelativeLayout progress_rl;
    CustomBoldEditText tv_firstname, tv_password, tv_new_password;
//    Dialog dialog;
//    SimpleDraweeView profile_image;

    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.account_two_fragment);

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = pref.getString(user_id, "");

        btn_back = findViewById(R.id.img_account_back);
        tv_firstname = findViewById(R.id.account_two_frag__first_name);
        tv_email = findViewById(R.id.account_two_frag__email);
        tv_password = findViewById(R.id.account_two_frag__password);
        tv_new_password = findViewById(R.id.account_two_frag__new_password);
        progress_rl = findViewById(R.id.account_two_frag__prog_rl);
        firstname_change = findViewById(R.id.account_two_frag__first_name_change);
        firstname_edit = findViewById(R.id.account_two_frag__first_name_edit);
        password_change = findViewById(R.id.account_two_frag__password_change);
        password_edit = findViewById(R.id.account_two_frag__password_edit);
        password_title = findViewById(R.id.account_two_frag__password_title);
        save_changes = findViewById(R.id.account_two_frag__done);
        new_password_container = findViewById(R.id.account_two_frag__new_password_container);
//        profile_image = findViewById(R.id.account_two_frag__profile_image);
        imageView = findViewById(R.id.account_two_frag__profile_image_temp);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                }
//                else
//                {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            }
//        });

        firstname_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_firstname.setEnabled(true);
                firstname_edit.setVisibility(View.GONE);
                firstname_change.setVisibility(View.VISIBLE);
            }
        });

        firstname_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_firstname.setEnabled(false);
                firstname_edit.setVisibility(View.VISIBLE);
                firstname_change.setVisibility(View.GONE);
            }
        });

        password_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                tv_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());


                tv_password.setEnabled(true);
                password_edit.setVisibility(View.GONE);
                password_change.setVisibility(View.VISIBLE);
                password_title.setText(R.string.old_password);
                new_password_container.setVisibility(View.VISIBLE);
                tv_new_password.setEnabled(true);
            }
        });

        password_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_password.getText().toString().equals("") && tv_new_password.getText().toString().equals("")){
                    password_title.setText(R.string.password);
                    new_password_container.setVisibility(View.GONE);
                }

                password_edit.setVisibility(View.VISIBLE);
                password_change.setVisibility(View.GONE);
                tv_password.setEnabled(false);
                tv_new_password.setEnabled(false);
            }
        });

        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePassword(tv_new_password.getText().toString(), tv_new_password, "password must be atleast 6 characters")) {
                    return;
                }
                progress_rl.setVisibility(View.VISIBLE);
                retrofitEditProfileData(userID, tv_firstname.getText().toString(), tv_password.getText().toString(),tv_new_password.getText().toString());
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (isNetworkConnected()) {
            progress_rl.setVisibility(View.VISIBLE);
            retrofitGetProfileData(userID);
        } else {
            Toast.makeText(this, "Internet not connected", Toast.LENGTH_SHORT).show();
        }
    }

    public void retrofitGetProfileData(String userID) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetProfileResponse> call = apiInterface.getProfile(userID);

        call.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful()) {
                    resource = response.body();
                    assert resource != null;
                    Log.e("success", resource.getMessages());
                    if (resource.getSuccess()) {
                        tv_firstname.setText(resource.getData().getFirstName());
                        tv_email.setText(resource.getData().getEmail());
                        progress_rl.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(AccountSettingActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                Log.e("Failure Response++++", t.getMessage());
                Toast.makeText(AccountSettingActivityNew.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void retrofitEditProfileData(final String userID, final String firstName, String old_password, String new_password) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetEditProfileResponse> call = apiInterface.editProfile(userID, firstName, firstName, old_password,new_password);
        call.enqueue(new Callback<GetEditProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetEditProfileResponse> call, @NotNull Response<GetEditProfileResponse> response) {
                if (response.isSuccessful()) {
                    GetEditProfileResponse getEditProfileresources = response.body();

                    assert getEditProfileresources != null;
                    if (getEditProfileresources.getSuccess()) {
                        tv_firstname.setText(getEditProfileresources.getData().getFirstName());
                        tv_email.setText(getEditProfileresources.getData().getEmail());
                        tv_password.setText("");
                        new_password_container.setVisibility(View.GONE);
                        password_title.setText(R.string.password);
                        password_change.setVisibility(View.GONE);
                        password_edit.setVisibility(View.VISIBLE);
                        tv_new_password.setText("");
                        tv_new_password.setEnabled(false);
                        tv_password.setEnabled(false);
                        Toast.makeText(AccountSettingActivityNew.this, getEditProfileresources.getMessages(), Toast.LENGTH_SHORT).show();
                        progress_rl.setVisibility(View.GONE);


                    } else {
                        Toast.makeText(AccountSettingActivityNew.this, getEditProfileresources.getMessages(), Toast.LENGTH_SHORT).show();
                        progress_rl.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(AccountSettingActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                    progress_rl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetEditProfileResponse> call, Throwable t) {
                Toast.makeText(AccountSettingActivityNew.this, t.toString(), Toast.LENGTH_SHORT).show();
                progress_rl.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
