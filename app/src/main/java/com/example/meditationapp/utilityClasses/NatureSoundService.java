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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;

public class NatureSoundService extends Service {

    private static final String TAG = "NatureSoundService";
    MediaPlayer mplayer;
    String song;
    int volume;
    AudioManager audioManager;

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
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {

            if (intent.getStringExtra("player").equals("Play")) {
                if (audioManager != null) {
                    volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, 0);
                }

                song = intent.getStringExtra("nature_song");
                if (song != null) {
                    try {
                        if (mplayer != null) {
                            mplayer.release();
                            mplayer = null;
                        }
                        mplayer = new MediaPlayer();
                        mplayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                        mplayer.setDataSource(song);
                        mplayer.prepareAsync();
                        mplayer.setLooping(true);
//                        mplayer.setVolume(50, 50);
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
            if (intent.getStringExtra("player").equals("Vol_low")) {
                if (mplayer != null) {
                    volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
//                    if (volume >= 1) {
                    if (volume <= 1) {
                        volume = 0;
                    } else {
                        volume = volume - 1;
                    }
//                    Toast.makeText(this, String.valueOf(volume), Toast.LENGTH_SHORT).show();
//                        mplayer.setVolume(volume, volume);
                    if (audioManager != null) {
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, 0);
                    }
                    sendInfoBroadcast();
//                    }
                }
            }
            //
            if (intent.getStringExtra("player").equals("Vol_high")) {
                if (mplayer != null) {
                    volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
//                    if (volume <= 99) {
                    if (volume >= 6) {
                        volume = 7;
                    } else {
                        volume = volume + 1;
                    }
//                    }
//                    Toast.makeText(this, String.valueOf(volume), Toast.LENGTH_SHORT).show();
//                    mplayer.setVolume(volume, volume);
                    if (audioManager != null) {
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, 0);
                    }
                    sendInfoBroadcast();
                }
            }
            //
            if (intent.getStringExtra("player").equals("Vol_bar")) {
                if (mplayer != null) {
                    if (mplayer.isPlaying()) {
                        volume = intent.getIntExtra("volume", 5);
                        if (audioManager != null) {
                            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, 0);
                        }
                        sendInfoBroadcast();
                    }
                }
            }

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

    private void sendInfoBroadcast() {
        if (mplayer == null)
            return;

        Intent updateIntent = new Intent("VOICE_UPDATE_ACTION");
        updateIntent.putExtra("Volume", volume);
        LocalBroadcastManager.getInstance(this).sendBroadcast(updateIntent);
    }

}
