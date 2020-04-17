package com.example.meditationapp.utilityClasses;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class NatureSoundService extends Service {

    private static final String TAG = "NatureSoundService";
    MediaPlayer mplayer;
    String song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Toast.makeText(this, "Service startedN...", Toast.LENGTH_SHORT).show();
        Log.e("playerN", "onCreate() , service started...");

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
//        song = intent.getStringExtra("nature_song");
//        if (song != null) {
//            try {
//                if (mplayer != null) {
//                    mplayer.release();
//                    mplayer = null;
//                }
//                mplayer = new MediaPlayer();
//                mplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mplayer.setDataSource(song);
//                mplayer.prepareAsync();
//                mplayer.setLooping(true);
//                mplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mplayer.start();
//                        Log.e("player", "play");
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return Service.START_STICKY;
        if (intent != null) {

            if (intent.getStringExtra("player").equals("Play")) {
                song = intent.getStringExtra("nature_song");
                if (song != null) {
                    try {
                        if (mplayer != null) {
                            mplayer.release();
                            mplayer = null;
                        }
                        mplayer = new MediaPlayer();
                        mplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mplayer.setDataSource(song);
                        mplayer.prepareAsync();
                        mplayer.setLooping(true);
                        mplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mplayer.start();
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
                if (mplayer != null) {
                    if (mplayer.isPlaying()) {
                        pause();
                    }
                }
            }
            //
            if (intent.getStringExtra("player").equals("Resume")) {
                if (mplayer != null) {
                    if (!mplayer.isPlaying()) {
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
//        Toast.makeText(this, "Service stoppedN...", Toast.LENGTH_SHORT).show();
        Log.e("player", "onCreate() , service stopped...");
    }

    @Override
    public void onLowMemory() {
        Log.e("player", "onLowMemory()");
    }

    public void play() {
        if (mplayer != null) {
            mplayer.start();
            Log.e("player", "play");
        }
    }

    public void pause() {
        if (mplayer != null) {
            mplayer.pause();
            Log.e("player", "pause");
        }
    }

    public void release() {
        if (mplayer != null) {
            mplayer.release();
            Log.e("player", "release");
        }
    }

    public void stop() {
        if (mplayer != null) {
            mplayer.stop();
            Log.e("player", "stop");
        }
    }

}
