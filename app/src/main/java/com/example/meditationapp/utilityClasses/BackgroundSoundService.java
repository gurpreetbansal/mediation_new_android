package com.example.meditationapp.utilityClasses;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.meditationapp.R;
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew;

import java.io.IOException;
import java.util.ArrayList;

public class BackgroundSoundService extends Service {

    private static final String TAG = "BackgroundSoundService";
    MediaPlayer mediaPlayer;
    String song, time;
    ArrayList<String> playlist;
    Boolean start=false;
    int i = 1;
    //    int durationTimer;
    private boolean isPrepared;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("player", "onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Toast.makeText(this, "Service startedM...", Toast.LENGTH_SHORT).show();
        Log.e("playerM", "onCreate() , service started...");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {

            if (intent.getStringExtra("player").equals("Play")) {
                song = intent.getStringExtra("main_song");
                playlist = intent.getStringArrayListExtra("playlist");
//                durationTimer = intent.getIntExtra("duration", 0);
//                time = milliSecondsToTimer(durationTimer);
                if (song != null) {
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        mediaPlayer = MediaPlayer.create(this, R.raw.a);
//                        sendInfoBroadcast();
//                        mediaPlayer.start();
                        mediaPlayer.setDataSource(song);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                start = true;
                                CreativityAffirmationActivityNew.start();
                                mediaPlayer.start();
                                Log.e("current", String.valueOf(mediaPlayer.getCurrentPosition()));
                                sendInfoBroadcast();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if (start) {
                                    if (playlist != null) {
                                        if (playlist.size() > i) {
                                            CreativityAffirmationActivityNew.pause();
                                            Intent m_intent = new Intent(BackgroundSoundService.this, BackgroundSoundService.class);
                                            m_intent.putExtra("main_song", playlist.get(i));
                                            Log.e(String.valueOf(i), playlist.get(i));
                                            m_intent.putStringArrayListExtra("playlist", playlist);
                                            m_intent.putExtra("player", "Play");
                                            startService(m_intent);
                                            start = false;

//                                    try {
//                                        mediaPlayer.setDataSource(playlist.get(i + 1));
//                                        mediaPlayer.prepareAsync();
//                                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                                            @Override
//                                            public void onPrepared(MediaPlayer mp) {
//                                                mediaPlayer.start();
//                                                sendInfoBroadcast();
//                                            }
//                                        });
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                            i++;
                                        } else {
                                            CreativityAffirmationActivityNew.pause();
                                            start = false;
                                        }
                                    } else {
                                        CreativityAffirmationActivityNew.pause();
                                        start = false;
                                    }
                                }
                            }
                        });
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
//            if (intent.getStringExtra("player").equals("Change")) {
//                song = intent.getStringExtra("main_song");
//                if (song != null) {
//                    try {
//                        if (mediaPlayer != null) {
//                            mediaPlayer.release();
//                            mediaPlayer = null;
//                        }
//                        mediaPlayer = new MediaPlayer();
//                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        mediaPlayer.setDataSource(song);
//                        mediaPlayer.prepareAsync();
//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mp) {
//                                mediaPlayer.start();
//                                sendInfoBroadcast();
//                                Log.e("player", "play");
//                            }
//                        });
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            //
            if (intent.getStringExtra("player").equals("ACTION_SEND_INFO")) {
                sendInfoBroadcast();
            }
            //
            if (intent.getStringExtra("player").equals("ACTION_SEEK_TO")) {
                int time = intent.getIntExtra("seek_to", 0);
                seekTo(time);
            }

            if (intent.getStringExtra("player").equals("Seek_backward")) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                }
            }

            if (intent.getStringExtra("player").equals("Seek_forward")) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
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
//        Toast.makeText(this, "Service stoppedM...", Toast.LENGTH_SHORT).show();
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

    private void seekTo(int time) {

        mediaPlayer.seekTo(time);

        Intent updateIntent = new Intent();
        updateIntent.putExtra("GUI_UPDATE_ACTION", "GUI_UPDATE_ACTION");
        updateIntent.putExtra("ACTUAL_TIME_VALUE_EXTRA", mediaPlayer.getCurrentPosition() / 1000);
        updateIntent.putExtra("TOTAL_TIME_VALUE_EXTRA", mediaPlayer.getDuration() / 1000);
        sendBroadcast(updateIntent);
    }

    private void sendInfoBroadcast() {
        if (mediaPlayer == null)
            return;

        Log.e("test", "++++++++working" + mediaPlayer.getCurrentPosition());

        Intent updateIntent = new Intent("GUI_UPDATE_ACTION");
        updateIntent.putExtra("ACTUAL_TIME_VALUE_EXTRA", mediaPlayer.getCurrentPosition() / 1000);
        updateIntent.putExtra("TOTAL_TIME_VALUE_EXTRA", mediaPlayer.getDuration() / 1000);
//        updateIntent.putExtra("msg", "msg");
        LocalBroadcastManager.getInstance(this).sendBroadcast(updateIntent);
    }

}
