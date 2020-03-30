package com.example.meditationapp.javaActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.meditationapp.activities.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.ModelClasses.SignupModelClass;
import com.example.meditationapp.ModelClasses.SignupSendData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.VoiceSelect_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivityNew extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    LinearLayout ll_facebook;
    TextView txt_login, txt_sign_up;
    String email_txt, password_txt, name_txt, social_id, social_type, device_type = "Android", device_token, response;
    ApiInterface apiInterface;
    private SignupSendData sendData;
    CustomBoldEditText ed_email, ed_name, ed_password;

    private static final String TAG = null;
    private LinearLayout signupActivity_ll_google;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;
    public static final int SIGN_IN = 1;
//    private static final int REQ_CODE = 1;
//    GoogleSignInClient mGoogleSignInClient;
//    SignInButton signInButton;

    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email", GOOGLE= "google", FACEBOOK = "facebook";
    LinearLayout ll_login_facebook;

    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_two_activity);

        // setStatusBaseWhiteMain();
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCanceledOnTouchOutside(false);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        ed_email = findViewById(R.id.signup__email);
        ed_name = findViewById(R.id.signup__name);
        ed_password = findViewById(R.id.signup__password);

//        ll_facebook = (LinearLayout) findViewById(R.id.ll_facebook);
        txt_login = (TextView) findViewById(R.id.signup_txt_login);
        txt_sign_up = (TextView) findViewById(R.id.signup__txt_sign_up);
//        ll_facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent voice = new Intent(SignupActivityNew.this, VoiceSelect_Activity.class);
//                startActivity(voice);
//            }
//        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voice = new Intent(SignupActivityNew.this, LoginActivityNew.class);
                startActivity(voice);
            }
        });


        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_txt = ed_name.getText().toString();
                email_txt = ed_email.getText().toString();
                password_txt = ed_password.getText().toString();

                Log.e("email", email_txt);


                if (validateName(name_txt, ed_name, "name is reuired")) {
                    return;
                }
                if (validateName(email_txt, ed_email, "email is required")) {
                    return;
                }
                if (validateName(password_txt, ed_password, "password is required")) {
                    return;
                }
                if (validatePassword(password_txt, ed_password, "pssword must be atleast 6 characters")) {
                    return;
                }

                sendData = new SignupSendData();
                sendData.setFirstName(name_txt);
                sendData.setEmail(email_txt);
                sendData.setPassword(password_txt);
                sendData.setDeviceType(device_type);
                sendData.setDeviceToken(UUID.randomUUID().toString());
//                Log.e("email+", sendData.getEmail());
                retrofitData();

                showDialog();
            }
        });

//        signInButton=findViewById(R.id.signinwithGogle);
        signupActivity_ll_google = findViewById(R.id.signup_ll_google);

        signupActivity_ll_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);

            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button_facebook_signup);
        ll_login_facebook = findViewById(R.id.ll_facebook_signup);
//            loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

//                Intent intent=new Intent(SignupActivityNew.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//                Toast.makeText(SignupActivityNew.this, ""+loginResult, Toast.LENGTH_SHORT).show();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.e("Signup Activity", response.toString());

                                try {
                                    String email = object.getString("email");
                                    Intent intent = new Intent(SignupActivityNew.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(SignupActivityNew.this, "Login Successfully" + email, Toast.LENGTH_SHORT).show();

                                    Log.e("RESULT EMAIL", email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                );

                Bundle parameter = new Bundle();
                parameter.putString("fields", "email");
                request.setParameters(parameter);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(SignupActivityNew.this, "Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignupActivityNew.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FACEBOOK ERROR", error.toString());

            }
        });


        ll_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });


    }

    public void retrofitData() {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<SignupModelClass> call = apiInterface.signup(sendData);
        call.enqueue(new Callback<SignupModelClass>() {
            @Override
            public void onResponse(Call<SignupModelClass> call, Response<SignupModelClass> response) {

                if (response.isSuccessful()) {
                    SignupModelClass resource = response.body();
                    Toast.makeText(SignupActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    assert resource != null;
                    if (resource.getSuccess()) {
                        String code = resource.getCode();
                        String msg = resource.getMessages();

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user_id", resource.getData().getUserId());
                        editor.apply();

//                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
//                        pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//                        pref.getString(user_id,"");

                        startActivity(new Intent(SignupActivityNew.this, VoiceSelect_Activity.class));
                        Toast.makeText(SignupActivityNew.this, msg, Toast.LENGTH_SHORT).show();

                        hideDialog();

                        Log.e("Success Response++++", code + " " + msg+" "+resource.getData().getUserId());
                    } else {
                        Toast.makeText(SignupActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();

                        hideDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupModelClass> call, Throwable t) {
                Log.e("Failure Response++++", t.getMessage());
                Toast.makeText(SignupActivityNew.this, t.toString(), Toast.LENGTH_SHORT).show();
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

        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, "" + connectionResult, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            showDialog();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            sendData = new SignupSendData();
            sendData.setFirstName(account.getDisplayName());
            sendData.setEmail(account.getEmail());
            sendData.setDeviceType(device_type);
            sendData.setDeviceToken(UUID.randomUUID().toString());
            sendData.setSocialType(GOOGLE);
            sendData.setSocialId(account.getId());

            Log.e("socialid",account.getId());
            retrofitData();

//            startActivity(new Intent(SignupActivityNew.this, SettingActivity.class));


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(MainActivity.this, "Logout sucess"+task, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}
