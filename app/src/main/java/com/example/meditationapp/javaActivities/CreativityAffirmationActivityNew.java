package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.R;
import com.example.meditationapp.utilityClasses.BackgroundSoundService;
import com.example.meditationapp.utilityClasses.NatureSoundService;

import java.io.IOException;

public class CreativityAffirmationActivityNew extends AppCompatActivity {


    LinearLayout ll_options, img_vol_bar;
    ImageView musicbtn, back_btn;
    AppCompatImageView player_play;
    CustomBoldtextView player_timer;
    String song;
    Boolean playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creativity_two_fragment);

        SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        playing = preferences.getBoolean("playing", false);

        ll_options = findViewById(R.id.ll_options);
        musicbtn = findViewById(R.id.img_music);
        img_vol_bar = findViewById(R.id.img_vol_bar);
        player_play = findViewById(R.id.player_play);
        player_timer = findViewById(R.id.player_timer);
        back_btn = findViewById(R.id.img_back_four);

        song = getIntent().getStringExtra("song");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (isMyServiceRunning(BackgroundSoundService.class)) {
            player_timer.setText("Changed");
            player_play.setImageResource(R.mipmap.pause);
            Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
            m_intent.putExtra("main_song", song);
            m_intent.putExtra("player", "Change");
            startService(m_intent);
            playing = true;
        } else {
            player_timer.setText("Started");
            player_play.setImageResource(R.mipmap.pause);
            Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
            m_intent.putExtra("main_song", song);
            m_intent.putExtra("player", "Play");
            startService(m_intent);
            playing = true;
        }

        musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_options.getVisibility() == View.VISIBLE) {
                    ll_options.setVisibility(View.INVISIBLE);
                    if (img_vol_bar.getVisibility() == View.VISIBLE) {
                        img_vol_bar.setVisibility(View.INVISIBLE);
                    }
                    if (isMyServiceRunning(NatureSoundService.class)) {
                        stopService(new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class));
                    }
                } else {
                    ll_options.setVisibility(View.VISIBLE);
                }
            }
        });

        ll_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_vol_bar.setVisibility(View.VISIBLE);
                if (isMyServiceRunning(NatureSoundService.class)) {
                    stopService(new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class));
                } else {
                    Intent n_intent = new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class);
                    n_intent.putExtra("nature_song", "https://clientstagingdev.com/meditation/public/voice/1586425636.mp3");
                    n_intent.putExtra("player", "Play");
                    startService(n_intent);
                }
            }
        });

        player_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyServiceRunning(BackgroundSoundService.class)) {
                    if (playing) {
                        player_timer.setText("Paused");
                        player_play.setImageResource(R.mipmap.player_sound_play);
                        Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
                        m_intent.putExtra("player", "Pause");
                        startService(m_intent);
                        if (isMyServiceRunning(NatureSoundService.class)) {
                            Intent n_intent = new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class);
                            n_intent.putExtra("player", "Pause");
                            startService(n_intent);
                        }
                        playing = false;
                    } else {
                        player_timer.setText("Resumed");
                        player_play.setImageResource(R.mipmap.pause);
                        Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
                        m_intent.putExtra("player", "Resume");
                        startService(m_intent);
                        if (isMyServiceRunning(NatureSoundService.class)) {
                            Intent n_intent = new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class);
                            n_intent.putExtra("player", "Resume");
                            startService(n_intent);
                        }
                        playing = true;
                    }
                }
            }
        });

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class));
        stopService(new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("playing", playing);
        editor.apply();
    }
}
