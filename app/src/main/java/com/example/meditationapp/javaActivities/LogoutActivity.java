package com.example.meditationapp.javaActivities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.LogoutModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HelpCenter_Activity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class LogoutActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private CustomBoldtextView txt_no, txt_yes, txt_help_center_quit;

    ApiInterface apiInterface;
    //    LogoutModelClass logoutModelClass = new LogoutModelClass();
    private static final String EMAIL = "email", GOOGLE = "google", FACEBOOK = "facebook";
    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;

    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quit_activity);

        txt_yes = findViewById(R.id.txt_yes);
        txt_no = findViewById(R.id.txt_no);
        txt_help_center_quit = findViewById(R.id.txt_help_center_quit);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        SharedPreferences sharedPreferences = getSharedPreferences("mypref", 0);
        final String userid = sharedPreferences.getString("user_id", "");
        final String socialType = sharedPreferences.getString("social_type", "");

//        IntentFilter intentFilter=new IntentFilter();
//        intentFilter.addAction("com.package.ACTION_LOGOUT");
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                finish();
//            }
//        },intentFilter);

        txt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("socialtype", socialType);

                if (socialType.equals(GOOGLE)) {
                    googleLogout();
                    Log.e("logout", "google");
                }

                if (socialType.equals(FACEBOOK)) {
                    getFacebookLogout();
                    Log.e("logout", "facebook");
                }

                if (socialType.equals(EMAIL)) {
                    getLogout(userid);
                    Log.e("logout", "email");
                }
                Log.e("logout", socialType);

            }
        });

        txt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_help_center_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogoutActivity.this, HelpCenter_Activity.class);
                startActivity(intent);

            }
        });

    }

    private void getFacebookLogout(){

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions", null
                , HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id", null);
                    editor.putString("social_type", null);
                    editor.apply();

                Toast.makeText(LogoutActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogoutActivity.this, LoginActivityNew.class));
                finishAffinity();

                LoginManager.getInstance().logOut();
            }
        }).executeAsync();

    }


    private void getLogout(String userid) {

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<LogoutModelClass> call = apiInterface.getLogout(userid);

        call.enqueue(new Callback<LogoutModelClass>() {
            @Override
            public void onResponse(Call<LogoutModelClass> call, Response<LogoutModelClass> response) {

                if (response.isSuccessful()) {

                    LogoutModelClass logoutModelClass = response.body();

                    if (logoutModelClass.getSuccess()) {

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user_id", null);
                        editor.putString("social_type", null);
                        editor.apply();

                        Log.e("logout", "logout");

                        Toast.makeText(LogoutActivity.this, logoutModelClass.getMessages(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogoutActivity.this, LoginActivityNew.class));
                        finishAffinity();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutModelClass> call, Throwable t) {
                Toast.makeText(LogoutActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                t.printStackTrace();
                Log.e("logout", "logout error");
            }
        });

    }


    public void googleLogout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id", null);
                    editor.putString("social_type", null);
                    editor.apply();
                    startActivity(new Intent(LogoutActivity.this, LoginActivityNew.class));
                    finishAffinity();
                    Toast.makeText(LogoutActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    Log.e("logout", "google logout");
                } else {
                    Toast.makeText(LogoutActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("logout", "google logout error");
    }

    public void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Log.e("data", account.getEmail().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}
