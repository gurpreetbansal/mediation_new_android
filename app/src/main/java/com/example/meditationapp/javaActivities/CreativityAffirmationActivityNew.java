package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.R;
import com.example.meditationapp.activities.MainActivity;
import com.example.meditationapp.utilityClasses.BackgroundSoundService;
import com.example.meditationapp.utilityClasses.NatureSoundService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class CreativityAffirmationActivityNew extends AppCompatActivity {


    LinearLayout ll_options, img_vol_bar;
    ConstraintLayout ll_options_cl;
    ImageView musicbtn, back_btn;
    static AppCompatImageView player_play;
    CustomBoldtextView player_timer;
    String song;
    int total_duration, current_time;
    Boolean playing;
    CircularSeekBar circularSeekBar;
    private boolean blockGUIUpdate;
    //    private GuiReceiver receiver;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creativity_two_fragment);

//        SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
//        playing = preferences.getBoolean("playing", false);

        ll_options = findViewById(R.id.ll_options);
        musicbtn = findViewById(R.id.img_music);
        img_vol_bar = findViewById(R.id.img_vol_bar);
        player_play = findViewById(R.id.player_play);
        player_timer = findViewById(R.id.player_timer);
        back_btn = findViewById(R.id.img_back_four);
        ll_options_cl = findViewById(R.id.ll_options_cl);
        circularSeekBar = findViewById(R.id.seekbar);

        song = getIntent().getStringExtra("song");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        circularSeekBar.setMax(total_duration);

        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            int time;

            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                time = (int) progress;
//
                circularSeekBar.setProgress(this.time);
                if (fromUser)
                    player_timer.setText(getTimeString(time));
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                unblockGUIUpdate();
                Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
                m_intent.putExtra("seek_to", time * 1000);
                m_intent.putExtra("player", "ACTION_SEEK_TO");
                startService(m_intent);
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                blockGUIUpdate = true;
            }
        });

        CreativityAffirmationActivityNew.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int mCurrentPosition = current_time;
//                player_timer.setText(String.valueOf(current_time));
                circularSeekBar.setProgress(mCurrentPosition);

                Intent intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
                intent.putExtra("player", "ACTION_SEND_INFO");
                startService(intent);

                handler.postDelayed(this, 1000);
            }
        });

        if (isMyServiceRunning(BackgroundSoundService.class)) {
            player_play.setImageResource(R.mipmap.pause);
            Intent m_intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
            m_intent.putExtra("main_song", song);
            m_intent.putExtra("player", "Change");
            startService(m_intent);
            playing = true;
        } else {
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
                if (ll_options_cl.getVisibility() == View.VISIBLE) {
                    ll_options_cl.setVisibility(View.INVISIBLE);
                    if (img_vol_bar.getVisibility() == View.VISIBLE) {
                        img_vol_bar.setVisibility(View.INVISIBLE);
                    }
                    if (isMyServiceRunning(NatureSoundService.class)) {
                        stopService(new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class));
                    }
                } else {
                    ll_options_cl.setVisibility(View.VISIBLE);
                }
            }
        });

        ll_options_cl.setOnClickListener(new View.OnClickListener() {
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
//                        player_timer.setText("Paused");
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
//                        player_timer.setText("Resumed");
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

    private void unblockGUIUpdate() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blockGUIUpdate = false;
            }
        }, 150);
    }

    private static String getTimeString(int totalTime) {
        long s = totalTime % 60;
        long m = (totalTime / 60) % 60;
        long h = totalTime / 3600;

        String stringTotalTime;
        if (h != 0)
            stringTotalTime = String.format("%02d:%02d:%02d", h, m, s);
        else
            stringTotalTime = String.format("%02d:%02d", m, s);
        return stringTotalTime;
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
    protected void onResume() {
        super.onResume();

        Log.e("reume", "resume______");

        LocalBroadcastManager.getInstance(CreativityAffirmationActivityNew.this).registerReceiver(
                Newreceiver, new IntentFilter("GUI_UPDATE_ACTION"));

        Intent intent = new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class);
        intent.putExtra("player", "ACTION_SEND_INFO");
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(CreativityAffirmationActivityNew.this, NatureSoundService.class));
        stopService(new Intent(CreativityAffirmationActivityNew.this, BackgroundSoundService.class));
        if (Newreceiver != null) {
            unregisterReceiver(Newreceiver);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Newreceiver != null) {
            unregisterReceiver(Newreceiver);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("playing", playing);
        editor.apply();
    }

    public static void pause() {
        player_play.setImageResource(R.mipmap.player_sound_play);
    }
    //

    private BroadcastReceiver Newreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            current_time = intent.getIntExtra("ACTUAL_TIME_VALUE_EXTRA", 0);
            total_duration = intent.getIntExtra("TOTAL_TIME_VALUE_EXTRA", 0);
//            Log.e("time", String.valueOf(intent.getIntExtra("ACTUAL_TIME_VALUE_EXTRA", 200)));
//            Log.e("duration", String.valueOf(intent.getIntExtra("TOTAL_TIME_VALUE_EXTRA", 200)));
            String time = getTimeString(current_time);

            if (circularSeekBar != null) {
                circularSeekBar.setProgress(current_time);
                circularSeekBar.setMax(total_duration);
            }
            if (player_timer != null) {
                player_timer.setText(time);
            }
        }
    };
}
