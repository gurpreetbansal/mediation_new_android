package com.example.meditationapp.javaActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.meditationapp.ModelClasses.GetSocialLoginResponse;
import com.example.meditationapp.activities.LoginActivity;
import com.example.meditationapp.activities.VoiceSelect_Activity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.LoginModelClass;
import com.example.meditationapp.ModelClasses.LoginSendData;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.HomeActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityNew extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = null;
    com.example.meditationapp.Custom_Widgets.CustomBoldEditText ed_password, ed_email;
    com.example.meditationapp.Custom_Widgets.CustomRegularTextView btn_login;
    CustomBoldtextView btn_signup, txt_forgot_password;
    ApiInterface apiInterface;
    private LoginSendData loginSendData = new LoginSendData();

    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions gso;

    String email_txt, password_txt, device_type = "Android";
    ProgressDialog progressDialog;

//    private FirebaseAuth mAuth;

    //    private SignInButtonImpl signInButton;
    private ConstraintLayout loginActivity_ll_google;
    //    GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 1;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount googleSignInAccount;

    LoginButton loginButton;
    CallbackManager callbackManager;
    LoginManager loginManager;
    ConstraintLayout ll_login_facebook;
    private static final String EMAIL = "email", GOOGLE = "google", FACEBOOK = "facebook";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        mAuth = FirebaseAuth.getInstance();
//          printHashKey();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        // yJH5d541v7FJRvN+suz21LdlN5g=

        ed_email = findViewById(R.id.login__email);
        ed_password = findViewById(R.id.login__password);
        btn_login = findViewById(R.id.login__txt_log_in);
        btn_signup = findViewById(R.id.txt_sign_up);
        txt_forgot_password = findViewById(R.id.txt_forgot_password);
        loginActivity_ll_google = findViewById(R.id.loginActivity_ll_google);

//        signInButton = findViewById(R.id.login_button_google);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCanceledOnTouchOutside(false);

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

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivityNew.this, ForgetPasswordActivity.class));


            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button_facebook_login);
        ll_login_facebook = findViewById(R.id.ll_login_facebook);
//            loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

//                Intent intent=new Intent(LoginActivityNew.this,HomeActivity.class);
//                startActivity(intent);
//                finish();
//                Toast.makeText(LoginActivityNew.this, ""+loginResult.toString(), Toast.LENGTH_SHORT).show();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.e("Signup Activity", response.toString());

                                try {
                                    String email = object.getString("email");

                                    Intent intent = new Intent(LoginActivityNew.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
//                            Toast.makeText(LoginActivityNew.this, "Login Successfully " + email, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginActivityNew.this, "Login Successfully ", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("email", email);
                                    editor.apply();

                                    Log.e("RESULT EMAIL", email);
                                } catch (JSONException e) {
//                            e.printStackTrace();
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
                Toast.makeText(LoginActivityNew.this, "Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivityNew.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FACEBOOK ERROR", error.toString());

            }
        });


        ll_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail().build();
//
//        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        loginActivity_ll_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent, REQ_CODE);
                showDialog();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, REQ_CODE);

//                firebaseAuthWithGoogle(googleSignInAccount);
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
                        editor.putString("social_type", EMAIL);
                        editor.apply();

                        startActivity(new Intent(LoginActivityNew.this, HomeActivity.class));
                        Toast.makeText(LoginActivityNew.this, msg, Toast.LENGTH_SHORT).show();

                        hideDialog();
                        finish();

//                        Log.e("Success Response++++", code + " " + msg);
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

    public void socialLoginRetrofit(String socialId, final String socialType, String email, String profile, String name, String deviceType, String deviceToken) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetSocialLoginResponse> call = apiInterface.getSocialLogin(socialId, socialType, email, profile, name, deviceType, deviceToken);

        call.enqueue(new Callback<GetSocialLoginResponse>() {
            @Override
            public void onResponse(Call<GetSocialLoginResponse> call, Response<GetSocialLoginResponse> response) {

                GetSocialLoginResponse resource = response.body();
                if (response.isSuccessful()) {

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id", resource.getData().getUserId());
                    editor.putString("social_type", socialType);
                    editor.apply();

                    if (resource.getData().getUserType().equals("0")) {
                        startActivity(new Intent(LoginActivityNew.this, VoiceSelect_Activity.class));
                    } else if (resource.getData().getUserType().equals("1")) {
                        startActivity(new Intent(LoginActivityNew.this, HomeActivity.class));
                    }
                    Toast.makeText(LoginActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                    finish();
                } else {
                    Toast.makeText(LoginActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }

            }

            @Override
            public void onFailure(Call<GetSocialLoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivityNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

        Log.e("Failure Response++++", connectionResult.getErrorMessage());
        Toast.makeText(this, "" + connectionResult, Toast.LENGTH_SHORT).show();
        hideDialog();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            showDialog();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            Log.e("data", "he-----------");
        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.e("TAG++++++++", account.getEmail());
            String photoUrl = "";
            if (account.getPhotoUrl() != null) {
                photoUrl = account.getPhotoUrl().toString();
            }
            socialLoginRetrofit(account.getId(), GOOGLE, account.getEmail(), photoUrl,
                    account.getDisplayName(), device_type, UUID.randomUUID().toString());
        } catch (ApiException e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            hideDialog();
        }
    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
//                            Toast.makeText(LoginActivityNew.this, "Success" + user, Toast.LENGTH_SHORT).show();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
////                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//    }

//    public void printHashKey(){
//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    getPackageName(),
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//    }

}


// Already google sign in
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if (alreadyloggedAccount != null) {
//            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
//            onLoggedIn(alreadyloggedAccount);
//        } else {
//            Log.d(TAG, "Not logged in");
//        }
//    }