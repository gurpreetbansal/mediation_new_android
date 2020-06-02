package com.example.meditationapp.JavaFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.myapplication.fragment.PromoFragment;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetProfileResponse;
import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.GetMorePaymentActivity;
import com.example.meditationapp.javaActivities.SettingActivity;
import com.example.meditationapp.javaActivities.SubscriptionActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private CircleImageView userProfileIV;
    private CustomBoldtextView userNameTV, txt_upgrade, txt_email;
    private LinearLayout ll_setting;
    CallbackManager callbackManager;
    LoginManager loginManager;
    //    String  mypreference = "mypref",user_name="name", img="profile_photo",email="email";
    String userID;
    String mypreference = "mypref", user_id = "user_id";
    ApiInterface apiInterface;
    GetProfileResponse resource;
    private LinearLayout progressLL, allInfoLL;
    ImageView payment_premium;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.account_three_fragment, container, false);

        userProfileIV = view.findViewById(R.id.accountFragment_userProfileIV);
        userNameTV = view.findViewById(R.id.accountFragment_userNameTV);
        ll_setting = view.findViewById(R.id.ll_setting);
        txt_email = view.findViewById(R.id.accountThree_txt_email);
        payment_premium = view.findViewById(R.id.payment_premium);

        SharedPreferences pref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = pref.getString(user_id, "");

        retrofitGetProfileData(userID);


        txt_upgrade = view.findViewById(R.id.txt_upgrade);
        progressLL = view.findViewById(R.id.accountFragment_progressLL);
        allInfoLL = view.findViewById(R.id.accountFragment_AllDetailsLL);

        progressLL.setVisibility(View.VISIBLE);

        payment_premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubscriptionActivity.class);
//                intent.putExtra("colorcode", "1");
                startActivity(intent);
            }
        });

        txt_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Fragment someFragment = new PromoFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, someFragment ); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
                Intent intent = new Intent(getActivity(), SubscriptionActivity.class);
//                intent.putExtra("colorcode", "1");
                startActivity(intent);

            }
        });

        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), SettingActivity.class));

            }
        });

        return view;

    }

    public void retrofitGetProfileData(final String userID) {
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

                        progressLL.setVisibility(View.GONE);
                        allInfoLL.setVisibility(View.VISIBLE);

                        userNameTV.setText(resource.getData().getFirstName());
                        txt_email.setText(resource.getData().getEmail());
                        if (!resource.getData().getProfile().equals("")) {
                            Picasso.get()
                                    .load(resource.getData().getProfile())
                                    .into(userProfileIV);
//                            Log.e("assss",resource.getData().getProfile()+"assa");
                        }
                    } else {
                        Toast.makeText(getActivity(), resource.getMessages(), Toast.LENGTH_SHORT).show();
                        progressLL.setVisibility(View.GONE);
                        allInfoLL.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    progressLL.setVisibility(View.GONE);
                    allInfoLL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                Log.e("Failure Response++++", t.getMessage());
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                progressLL.setVisibility(View.GONE);
                allInfoLL.setVisibility(View.VISIBLE);
            }
        });

    }

}
