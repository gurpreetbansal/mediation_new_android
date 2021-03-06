package com.example.meditationapp.javaActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bruce.pickerview.LoopScrollListener;
import com.bruce.pickerview.LoopView;
import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldtextView;
import com.example.meditationapp.ModelClasses.GetResponseSetVoice;
import com.example.meditationapp.ModelClasses.GetResponseSetVoiceData;
import com.example.meditationapp.ModelClasses.GetVoiceData;
import com.example.meditationapp.ModelClasses.GetVoiceResponse;
import com.example.meditationapp.ModelClasses.SetVoiceModelClass;
import com.example.meditationapp.R;
import com.example.meditationapp.adapter.VoiceAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.blackbox_vision.wheelview.view.DatePickerPopUpWindow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoiceSelect_Activity extends AppCompatActivity {

    ImageView img_back_tool;
    AppCompatImageView toggle_on, toggle_off;
    LinearLayout ll_alice_bg, ll_tiff_bg, ll_kevin_bg;
    ApiInterface apiInterface;
    GetVoiceResponse resource;
    RecyclerView recyclerView;
    List<GetVoiceData> voiceData;
    CustomBoldtextView time;
    CustomBoldtextView img_next;
    Integer[] id_unselected = {R.mipmap.alice_bg_two, R.mipmap.signup_fb_bg, R.mipmap.blue_curve_bg};
    Integer[] id_selected = {R.mipmap.alice_bg, R.mipmap.signup_fb_bg_two, R.mipmap.blue_curve_bg_two};
    int mHour, mMinute;
    MediaPlayer mediaPlayer;
    SetVoiceModelClass setVoiceModelClass = new SetVoiceModelClass();
    GetResponseSetVoice getResponseSetVoice;
    List<GetResponseSetVoiceData> setVoiceData;
    String voice_id = "", mypreference = "mypref", user_id = "user_id", userID, status = "0";
    List<String> voiceId = new ArrayList<>();
    RelativeLayout progress_bar;
    private static long back_pressed;
//    private LoopView loopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_select_activity);

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        userID = pref.getString(user_id, "");

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);


        img_next = findViewById(R.id.img_next);
        recyclerView = findViewById(R.id.voice_select_rv);
        time = findViewById(R.id.voice_select_time);
        toggle_on = findViewById(R.id.toggleButton_on);
        toggle_off = findViewById(R.id.toggleButton_off);
        progress_bar = findViewById(R.id.voice_select_prog);
//        loopView=findViewById(R.id.loop_view);



        progress_bar.setVisibility(View.VISIBLE);

        if (mMinute < 10) {
            time.setText(String.valueOf(mHour) + ":0" + String.valueOf(mMinute));
        } else {
            time.setText(String.valueOf(mHour) + ":" + String.valueOf(mMinute));
        }


        retrofitGetVoice();

        img_back_tool = (ImageView) findViewById(R.id.img_back_tool);

        img_back_tool.setVisibility(View.INVISIBLE);

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voice_id.equals("")) {
                    Toast.makeText(VoiceSelect_Activity.this, "One voice must be seletced", Toast.LENGTH_SHORT).show();
                } else {
                    voiceId.add(String.valueOf(voiceData.get(Integer.valueOf(voice_id)).getId()));
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    setVoiceModelClass.setUserId(userID);
                    Log.e("userID", userID + "----" + voiceId);
                    setVoiceModelClass.setVoiceId(voiceId);
                    setVoiceModelClass.setUserTime(time.getText().toString() + ":00");
                    setVoiceModelClass.setStatus(status);

                    retrofitSetVoice(setVoiceModelClass);
                }

            }
        });
//        img_back_tool.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cat = new Intent(VoiceSelect_Activity.this, LogoutActivity.class);
//                startActivity(cat);
//                finish();
//            }
//        });


        LinearGradient shade = new LinearGradient(0f, 0f, 0f, time.getTextSize(), getResources().getColor(R.color.time_color_a), getResources().getColor(R.color.time_color_b), Shader.TileMode.CLAMP);
        time.getPaint().setShader(shade);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(VoiceSelect_Activity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                time.setText(hourOfDay + ":" + minute);
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();


                View dialogView = getLayoutInflater().inflate(R.layout.time_picker_dialog, null);
                final BottomSheetDialog dialog = new BottomSheetDialog(VoiceSelect_Activity.this);
                dialog.setContentView(dialogView);
                dialog.show();
//
//                final Dialog dialog = new Dialog(VoiceSelect_Activity.this);
//
//                dialog.setContentView(R.layout.time_picker_dialog);
//
                final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.time_picker);
                TextView cancel_txt = (TextView) dialog.findViewById(R.id.timePicker_txt_cancel);
                TextView done_txt = (TextView) dialog.findViewById(R.id.timePicker_txt_done);

//                Window window = dialog.getWindow();
//                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                window.setGravity(Gravity.BOTTOM);

                assert cancel_txt != null;
                cancel_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                assert timePicker != null;
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int i, int i1) {

//                        Toast.makeText(VoiceSelect_Activity.this, "Time:"+i+ ":"+ i1, Toast.LENGTH_SHORT).show();
//                       time.setText(""+i + ":"+ i1 + "AM");

                        if(i>=0 && i<12){
                            time.setText(""+i + ":"+ i1 + " AM");
                        } else {
                            if(i == 12){
                                time.setText(""+i + ":"+ i1 + " PM");
                            } else{
                                i = i -12;
                                time.setText(""+i + ":"+ i1 + " PM");
                            }
                        }
                    }
                });
                assert done_txt != null;
                done_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });



                dialog.show();

            }
        });


        toggle_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "0";
                toggle_off.setVisibility(View.GONE);
                toggle_on.setVisibility(View.VISIBLE);
            }
        });

        toggle_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "1";
                toggle_off.setVisibility(View.VISIBLE);
                toggle_on.setVisibility(View.GONE);
            }
        });
    }



    public void retrofitGetVoice() {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetVoiceResponse> call = apiInterface.getVoiceResponse();
        call.enqueue(new Callback<GetVoiceResponse>() {
            @Override
            public void onResponse(Call<GetVoiceResponse> call, Response<GetVoiceResponse> response) {

                if (response.isSuccessful()) {
                    resource = response.body();
                    assert resource != null;
                    Log.e("success", resource.getMessages());
                    if (resource.getSuccess()) {
                        voiceData = resource.getData();
                        recyclerView.setLayoutManager(new LinearLayoutManager(VoiceSelect_Activity.this));
                        VoiceAdapter adapter = new VoiceAdapter(voiceData, VoiceSelect_Activity.this, id_selected, id_unselected);
                        recyclerView.setAdapter(adapter);

                        progress_bar.setVisibility(View.GONE);

                        adapter.setOnitemClickListener(new VoiceAdapter.OnitemClickListener() {
                            @Override
                            public void startPlayer(String url, int position, Boolean pause) {
                                voice_id = String.valueOf(position);
                                if (!pause) {
                                    try {
                                        if (mediaPlayer != null) {
                                            mediaPlayer.release();
                                            mediaPlayer = null;
                                        }
                                        mediaPlayer = new MediaPlayer();

                                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                        mediaPlayer.setDataSource(url);
                                        mediaPlayer.prepareAsync();
                                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mp) {
                                                mediaPlayer.start();
                                                Log.e("play", "play");
                                            }
                                        });
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    mediaPlayer.pause();
                                    Log.e("pause", "pause");
                                }

                            }
                        });

                    } else {
                        progress_bar.setVisibility(View.GONE);
                        Toast.makeText(VoiceSelect_Activity.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetVoiceResponse> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
                Toast.makeText(VoiceSelect_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrofitSetVoice(SetVoiceModelClass setVoice) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<GetResponseSetVoice> call = apiInterface.setVoice(setVoice);
        call.enqueue(new Callback<GetResponseSetVoice>() {
            @Override
            public void onResponse(Call<GetResponseSetVoice> call, Response<GetResponseSetVoice> response) {

                if (response.isSuccessful()) {
                    getResponseSetVoice = response.body();
                    assert getResponseSetVoice != null;
                    Log.e("success", getResponseSetVoice.getMessages());
                    if (resource.getSuccess()) {
                        setVoiceData = getResponseSetVoice.getData();
//                        Toast.makeText(VoiceSelect_Activity.this, getResponseSetVoice.getCode(), Toast.LENGTH_SHORT).show();
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("mypref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("voice_selected", true);
                        editor.apply();

                        Log.e("response_success", response.body().getMessages());

                        Intent cat = new Intent(VoiceSelect_Activity.this, CategoriesActivities.class);
                        startActivity(cat);
                    }
                }
                Log.e("response", response.body().getMessages());
            }

            @Override
            public void onFailure(Call<GetResponseSetVoice> call, Throwable t) {
                Toast.makeText(VoiceSelect_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("response_fail", t.getMessage());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
