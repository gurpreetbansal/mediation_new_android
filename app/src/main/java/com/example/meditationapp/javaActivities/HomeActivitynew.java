package com.example.meditationapp.javaActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.myapplication.fragment.RecordFragment;
import com.example.meditationapp.JavaFragment.AccountFragment;
import com.example.meditationapp.JavaFragment.LibraryFragmentNew;
import com.example.meditationapp.JavaFragment.SoundFragment;
import com.example.meditationapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivitynew extends BaseActivity {

    private LinearLayout lib,sound,record,account;
    public static ImageView img_bottom_lib,img_bottom_sound,img_bottom_record,img_bottom_account;
    private FrameLayout container;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    Boolean check;
    boolean checkValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("gernal")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        String msg = "Successfull";

                        if (!task.isSuccessful()){
                            msg = "Faild";
                        }
                        Log.e("Message",msg);
                        Toast.makeText(HomeActivitynew.this, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful()){

                            String token = task.getResult().getToken();
                            Log.e("GERNATE TOKEN :  ", token);
                        }
                        else {
                             Log.e("TOKEN IS NOT GERNATED: ",task.getException().getMessage());

                        }
                    }
                });

//        TOKEN :- cY5EUC_GSJ6pcYM2fRyHNk:APA91bH--FIucex3FIUgjaGYGvTIXJO-KRb0s9x2ismOLvyOXS6IYSLJBLD5xHnLI83mw_OCtkFbg3qEkXl9fLLlW4cBLPqWmNeWRNs9koMe8h61gk0bCVIeyHbjwxHIHroboz9qusPU

        lib = findViewById(R.id.lib);
        sound = findViewById(R.id.sound);
        record = findViewById(R.id.record);
        account = findViewById(R.id.account);
        img_bottom_lib = findViewById(R.id.img_bottom_lib);
        img_bottom_sound = findViewById(R.id.img_bottom_sound);
        img_bottom_record = findViewById(R.id.img_bottom_record);
        img_bottom_account = findViewById(R.id.img_bottom_account);
        container = findViewById(R.id.container);

        SharedPreferences sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        sharedPreferences.getString("payment","");
        check =   sharedPreferences.getBoolean("truePayment",true);

        if (check){

           check = false;

            img_bottom_lib.setVisibility(View.VISIBLE);
            img_bottom_sound.setVisibility(View.GONE);
            img_bottom_record.setVisibility(View.GONE);
            img_bottom_account.setVisibility(View.GONE);

            LibraryFragmentNew libraryFragmentNew = new LibraryFragmentNew();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,libraryFragmentNew);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();


        }
        else {
           check = true;
            paymentData();

        }


        lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_bottom_lib.setVisibility(View.VISIBLE);
                img_bottom_sound.setVisibility(View.GONE);
                img_bottom_record.setVisibility(View.GONE);
                img_bottom_account.setVisibility(View.GONE);

                LibraryFragmentNew libraryFragmentNew = new LibraryFragmentNew();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,libraryFragmentNew,TAG_FRAGMENT);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_bottom_lib.setVisibility(View.GONE);
                img_bottom_sound.setVisibility(View.VISIBLE);
                img_bottom_record.setVisibility(View.GONE);
                img_bottom_account.setVisibility(View.GONE);

                SoundFragment soundFragment = new SoundFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,soundFragment,TAG_FRAGMENT);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_bottom_lib.setVisibility(View.GONE);
                img_bottom_sound.setVisibility(View.GONE);
                img_bottom_record.setVisibility(View.VISIBLE);
                img_bottom_account.setVisibility(View.GONE);

                RecordFragment recordFragment = new RecordFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,recordFragment,TAG_FRAGMENT);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_bottom_lib.setVisibility(View.GONE);
                img_bottom_sound.setVisibility(View.GONE);
                img_bottom_record.setVisibility(View.GONE);
                img_bottom_account.setVisibility(View.VISIBLE);

                AccountFragment accountFragment = new AccountFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,accountFragment,TAG_FRAGMENT);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            Intent intent =new Intent(HomeActivitynew.this,LogoutActivity.class);
            startActivity(intent);
        }

    }

    public void paymentData(){

        SharedPreferences sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        sharedPreferences.getString("payment","");
        sharedPreferences.getBoolean("truePayment",false);

        img_bottom_lib.setVisibility(View.GONE);
        img_bottom_sound.setVisibility(View.VISIBLE);
        img_bottom_record.setVisibility(View.GONE);
        img_bottom_account.setVisibility(View.GONE);

        SoundFragment soundFragment = new SoundFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,soundFragment,TAG_FRAGMENT);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}
