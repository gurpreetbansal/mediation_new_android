package com.example.meditationapp.utilityClasses;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;

import java.io.IOException;

public class BackgroundSoundService extends Service {

    private static final String TAG = "BackgroundSoundService";
    MediaPlayer mediaPlayer;
    String song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("player", "onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service started...", Toast.LENGTH_SHORT).show();
        Log.e("player", "onCreate() , service started...");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {

            if (intent.getStringExtra("player").equals("Play")) {
                song = intent.getStringExtra("main_song");
                if (song != null) {
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(song);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                                Log.e("player", "play");
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//
            if (intent.getStringExtra("player").equals("Pause")) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        pause();
                    }
                }
            }
            //
            if (intent.getStringExtra("player").equals("Resume")) {
                if (mediaPlayer != null) {
                    if (!mediaPlayer.isPlaying()) {
                        play();
                    }
                }
            }
            //

        }
        return Service.START_STICKY;
    }

    public IBinder onUnBind(Intent arg0) {
        Log.e("player", "onUnBind()");
        return null;
    }


    @Override
    public void onDestroy() {
        stop();
        release();
        Toast.makeText(this, "Service stopped...", Toast.LENGTH_SHORT).show();
        Log.e("player", "onCreate() , service stopped...");
    }

    @Override
    public void onLowMemory() {
        Log.e("player", "onLowMemory()");
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.e("player", "play");
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            Log.e("player", "pause");
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            Log.e("player", "release");
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            Log.e("player", "stop");
        }
    }

}
