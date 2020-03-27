package com.example.meditationapp.javaActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private CircleImageView userProfileIV;
    private CustomBoldtextView userNameTV;
    CallbackManager callbackManager;
    LoginManager loginManager;
    String  mypreference = "mypref",user_name="name";

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.account_three_fragment, container, false);

        userProfileIV=view.findViewById(R.id.accountFragment_userProfileIV);
        userNameTV=view.findViewById(R.id.accountFragment_userNameTV);


        SharedPreferences pref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String  name = pref.getString(user_name,"");

        userNameTV.setText(name);

//        callbackManager = CallbackManager.Factory.create();
//
//        loginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                userNameTV.setText("User Id : " + loginResult.getAccessToken().getUserId());
//                String imgURL = "https://graph.facebook.com/"+loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
//                Picasso.get().load(imgURL).into(userProfileIV);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });



        return view;

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        callbackManager.onActivityResult(requestCode,resultCode,data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
