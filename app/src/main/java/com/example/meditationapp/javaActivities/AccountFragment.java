package com.example.meditationapp.javaActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.myapplication.fragment.PromoFragment;
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
    private CustomBoldtextView userNameTV,txt_upgrade,txt_email;
    private LinearLayout ll_setting;
    CallbackManager callbackManager;
    LoginManager loginManager;
    String  mypreference = "mypref",user_name="name", img="profile_photo",email="email";

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.account_three_fragment, container, false);

        userProfileIV=view.findViewById(R.id.accountFragment_userProfileIV);
        userNameTV=view.findViewById(R.id.accountFragment_userNameTV);
        ll_setting=view.findViewById(R.id.ll_setting);
        txt_email=view.findViewById(R.id.accountThree_txt_email);


        SharedPreferences pref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String  name = pref.getString(user_name,"");
        String image =pref.getString(img,"");
        String emails=pref.getString(email,"");

        userNameTV.setText(name);
        Picasso.get().load(image).into(userProfileIV);
        txt_email.setText(emails);

        txt_upgrade=view.findViewById(R.id.txt_upgrade);

        txt_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment someFragment = new PromoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),SettingActivity.class));

            }
        });

        return view;

    }
}
