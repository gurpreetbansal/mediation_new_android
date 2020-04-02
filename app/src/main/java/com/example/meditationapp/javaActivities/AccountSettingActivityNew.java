package com.example.meditationapp.javaActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSettingActivityNew extends BaseActivity {
    ImageView btn_back;
    String userID,socialType;
    ApiInterface apiInterface;
    String mypreference = "mypref", user_id = "user_id",social_type = "social_type";
    private static final String EMAIL = "email", GOOGLE = "google", FACEBOOK = "facebook";
    CustomBoldtextView tv_email, firstname_edit, password_edit, firstname_change, password_change, save_changes, password_title;
    LinearLayout new_password_container;
    GetProfileResponse resource;
    RelativeLayout progress_rl;
    CustomBoldEditText tv_firstname, tv_password, tv_new_password;
    String path;
//    File savedFile = null;
    Uri uri;
    //    Dialog dialog;
//    SimpleDraweeView profile_image;
    String mediaPath, postPath;

    private static final int CAMERA_REQUEST = 1888;
    //    ImageView imageView;
    CircleImageView imageView;
    ImageView camera_icn;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.account_two_fragment);

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = pref.getString(user_id, "");
        socialType = pref.getString(social_type,"");

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
        camera_icn = findViewById(R.id.account_two_frag__icn_camera);

        if (socialType.equals(GOOGLE) || socialType.equals(FACEBOOK)){
            password_edit.setVisibility(View.GONE);
        }

        camera_icn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                            // Create the File where the photo should go
                            try {
                                File file = null;
                                file = createImageFile();
//                                Toast.makeText(AccountSettingActivityNew.this, file.toString(), Toast.LENGTH_SHORT).show();
                                // Continue only if the File was successfully created
                                Uri photoURI = FileProvider.getUriForFile(AccountSettingActivityNew.this,
                                        getApplicationContext().getPackageName() + ".provider",
                                        file);
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            } catch (Exception ex) {
                                // Error occurred while creating the File
                                Toast.makeText(AccountSettingActivityNew.this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

//                        startActivityForResult(cameraIntent, CAMERA_REQUEST);


                    }
                } else {
                    if (ContextCompat.checkSelfPermission
                            (AccountSettingActivityNew.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                AccountSettingActivityNew.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    }
                }
            }
        });

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

                if (tv_password.getText().toString().equals("") && tv_new_password.getText().toString().equals("")) {
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
                if (!tv_password.getText().toString().equals("") || !tv_new_password.getText().toString().equals("")) {
                    if (validatePassword(tv_new_password.getText().toString(), tv_new_password, "password must be atleast 6 characters")) {
                        return;
                    }
                }
                File file = new File(path);
                RequestBody fileReqBody = RequestBody.create(MediaType.parse("*image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
                progress_rl.setVisibility(View.VISIBLE);

                SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                userID = pref.getString(user_id, "");

                RequestBody.create(MediaType.parse("text/plain"), "image-type");
                RequestBody userId = RequestBody.create(MediaType.parse("text/plain"),userID);
                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),tv_firstname.getText().toString());
                RequestBody oldPaswword = RequestBody.create(MediaType.parse("text/plain"),tv_password.getText().toString());
                RequestBody newPasword = RequestBody.create(MediaType.parse("text/plain"),tv_new_password.getText().toString());

                retrofitEditProfileData(userId, name , oldPaswword, newPasword,part);
//                retrofitEditProfileData(userID, tv_firstname.getText().toString(), tv_password.getText().toString(), tv_new_password.getText().toString(),part);
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
                        if (!resource.getData().getProfile().equals("")) {
                            Picasso.get()
                                    .load(resource.getData().getProfile())
                                    .into(imageView);
//                            Log.e("assss",resource.getData().getProfile()+"assa");
                        }
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

//    public void retrofitEditProfileData(final String userID, final String firstName, final String old_password, final String new_password, MultipartBody.Part part)

    public void retrofitEditProfileData(final RequestBody userID, final RequestBody firstName, final RequestBody old_password, final RequestBody new_password, MultipartBody.Part part) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetEditProfileResponse> call = apiInterface.editProfile(userID, firstName, firstName, old_password, new_password,part);
        call.enqueue(new Callback<GetEditProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetEditProfileResponse> call, @NotNull Response<GetEditProfileResponse> response) {
                if (response.isSuccessful()) {

                    Log.e("data",userID+"-"+firstName+"-"+old_password+"-"+new_password+"-");
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
                        Log.e("testa", getEditProfileresources.getSuccess().toString());
                        progress_rl.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(AccountSettingActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("testb", "b");
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
            if (data != null) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(path));
            }
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir, imageFileName + ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        path = image.getAbsolutePath();
        return image;
    }
}
