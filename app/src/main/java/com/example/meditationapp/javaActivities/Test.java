package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.meditationapp.R;
import com.example.meditationapp.utilityClasses.BackgroundSoundService;
import com.example.meditationapp.utilityClasses.MusicService;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creativity_two_fragment);


        if (isMyServiceRunning(BackgroundSoundService.class)) {
//            player_timer.setText(duration);
//            player_play.setImageResource(R.mipmap.pause);
            Intent m_intent = new Intent(Test.this, MusicService.class);
//            m_intent.putExtra("main_song", song);
            m_intent.putExtra("player", "Change");
            startService(m_intent);
//            playing = true;
        } else {
//            player_timer.setText(durationTime);
//            player_play.setImageResource(R.mipmap.pause);
            Intent m_intent = new Intent(Test.this, MusicService.class);
//            m_intent.putExtra("main_song", song);
            m_intent.putExtra("player", "Play");
            startService(m_intent);
//            playing = true;
        }

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
}
