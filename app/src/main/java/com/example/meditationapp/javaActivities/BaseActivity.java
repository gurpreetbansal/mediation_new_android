package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;

import java.net.InetAddress;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public boolean validatePassword(String name, CustomBoldEditText nameET, String err_msg) {
        if (name.length() < 6) {
            nameET.setError(err_msg);
            nameET.requestFocus();
            return true;
        }
        return false;
    }
}
