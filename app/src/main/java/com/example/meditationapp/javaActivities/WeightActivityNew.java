package com.example.meditationapp.javaActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditationapp.Api.ApiInterface;
import com.example.meditationapp.Api.RetrofitClientInstance;
import com.example.meditationapp.Custom_Widgets.CustomBoldEditText;
import com.example.meditationapp.ModelClasses.GetAffirmation;
import com.example.meditationapp.ModelClasses.PostAffirmation;
import com.example.meditationapp.R;
import com.example.meditationapp.adapter.AffirmationAdapter;
import com.example.meditationapp.adapter.PostAffirmationAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeightActivityNew extends AppCompatActivity {

    RecyclerView getrecyclerView, postrecyclerView;
    ImageView add_affirmation;
    ApiInterface apiInterface;
    GetAffirmation resource;
    String shared_userID;
    CustomBoldEditText edittext_affirmationtitle;
    PostAffirmation postAffirmation;
    MultipartBody.Part parta;
    String cat_ID, titlea, songUrl;
    String path, songsID;
    String favourite;
    File file;
    String mypreference = "mypref", user_id = "user_id";
//    private String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "AudioRecording.3gp";

    public static final int REQUEST_AUDIO_PERMISSION_CODE = 0;
    MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_fragment);

        getrecyclerView = findViewById(R.id.getaffirmation_recyclerView);
        postrecyclerView = findViewById(R.id.postaffirmation_recyclerView);
        add_affirmation = findViewById(R.id.add_affirmation);
        edittext_affirmationtitle = findViewById(R.id.edittext_affirmationtitle);

        getrecyclerView.setVisibility(View.VISIBLE);
        postrecyclerView.setVisibility(View.GONE);

        try {
            file = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SharedPreferences pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        shared_userID = pref.getString(user_id, "");
        cat_ID = getIntent().getStringExtra("category_id");

        getAffirmation(shared_userID, cat_ID);

        add_affirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parta = null;
                titlea = edittext_affirmationtitle.getText().toString();
                RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), shared_userID);
                RequestBody title = RequestBody.create(MediaType.parse("text/plain"), titlea);
                RequestBody categoryId = RequestBody.create(MediaType.parse("text/plain"), cat_ID);
                RequestBody songsId = RequestBody.create(MediaType.parse("text/plain"), "");
                RequestBody fav = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(0));

                Log.e("data", shared_userID + "-" + titlea + "-" + cat_ID + "-");

                addAffirmation(userId, title, categoryId, songsId, fav, parta);
//                addAffirmation(shared_userID, titlea, cat_ID, "0", "0", parta);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(WeightActivityNew.this, RecyclerView.VERTICAL, false);
        getrecyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager layout = new LinearLayoutManager(WeightActivityNew.this, RecyclerView.VERTICAL, false);
        postrecyclerView.setLayoutManager(layout);

    }

    private void getAffirmation(final String userID, String categoryId) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<GetAffirmation> call = apiInterface.requestAffirmation(userID, categoryId);
        call.enqueue(new Callback<GetAffirmation>() {
            @Override
            public void onResponse(Call<GetAffirmation> call, Response<GetAffirmation> response) {
                if (response.isSuccessful()) {
                    resource = response.body();
                    assert resource != null;
                    if (resource.getSuccess()) {
                        AffirmationAdapter adapter = new AffirmationAdapter(WeightActivityNew.this, resource.getData());
                        getrecyclerView.setAdapter(adapter);

                        adapter.setOnitemClickListener(new AffirmationAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (resource.getData().get(position).getUploadStatus().equals(1)) {
                                    songUrl = resource.getData().get(position).getSongs().toString();
                                    Intent intent = new Intent(WeightActivityNew.this, CreativityAffirmationActivityNew.class);
                                    intent.putExtra("song", songUrl);
                                    startActivity(intent);
                                } else {
                                    stop();
                                }
                            }

                            @Override
                            public void onLongClick(int position) {
                                if (resource.getData().get(position).getUploadStatus().equals(1)) {
                                    Toast.makeText(WeightActivityNew.this, "Affirmation is already recorded", Toast.LENGTH_SHORT).show();
                                } else {
                                    songsID = resource.getData().get(position).getId().toString();
                                    favourite = resource.getData().get(position).getFavriteStatus().toString();
                                    titlea = resource.getData().get(position).getSongsTitle().toString();
                                    start();
                                }
                            }

                            @Override
                            public void onfavourite(int position, boolean b) {

                                parta = null;
                                if (b) {
                                    favourite = "1";
                                } else {
                                    favourite = "0";
                                }

                                titlea = resource.getData().get(position).getSongsTitle().toString();
                                songsID = resource.getData().get(position).getId().toString();
                                Log.e("pos", String.valueOf(position) + titlea + "-" + songsID);

                                RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), shared_userID);
                                RequestBody title = RequestBody.create(MediaType.parse("text/plain"), titlea);
                                RequestBody categoryId = RequestBody.create(MediaType.parse("text/plain"), cat_ID);
                                RequestBody songsId = RequestBody.create(MediaType.parse("text/plain"), songsID);
                                RequestBody fav = RequestBody.create(MediaType.parse("text/plain"), favourite);

                                addAffirmation(userId, title, categoryId, songsId, fav, parta);

                            }
                        });
                    } else {
                        Toast.makeText(WeightActivityNew.this, resource.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WeightActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAffirmation> call, Throwable t) {
                Toast.makeText(WeightActivityNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addAffirmation(RequestBody userId, RequestBody title, RequestBody categoryId, RequestBody songsId, RequestBody fav, MultipartBody.Part part) {
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);

//        Call<PostAffirmation> call = apiInterface.postAffirmation(userId, title, categoryId, fav, songsId, part);
        Call<PostAffirmation> call = apiInterface.postAffirmation(userId, title, categoryId, songsId, fav, part);


        call.enqueue(new Callback<PostAffirmation>() {
            @Override
            public void onResponse(Call<PostAffirmation> call, Response<PostAffirmation> response) {

                if (response.isSuccessful()) {
                    postAffirmation = response.body();
                    assert postAffirmation != null;
                    if (postAffirmation.getSuccess()) {
                        Toast.makeText(WeightActivityNew.this, postAffirmation.getMessages(), Toast.LENGTH_SHORT).show();
                        PostAffirmationAdapter adapter = new PostAffirmationAdapter(WeightActivityNew.this, postAffirmation.getData());
                        postrecyclerView.setAdapter(adapter);
                        getrecyclerView.setVisibility(View.GONE);
                        postrecyclerView.setVisibility(View.VISIBLE);

                        adapter.setOnitemClickListener(new PostAffirmationAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (postAffirmation.getData().get(position).getUploadStatus().equals(1)) {
                                    songUrl = postAffirmation.getData().get(position).getSongs().toString();
                                    Intent intent = new Intent(WeightActivityNew.this, CreativityAffirmationActivityNew.class);
                                    intent.putExtra("song", songUrl);
                                    startActivity(intent);
                                } else {
                                    stop();
                                }
                            }

                            @Override
                            public void onLongClick(int position) {
                                if (postAffirmation.getData().get(position).getUploadStatus().equals(1)) {
                                    Toast.makeText(WeightActivityNew.this, "Affirmation is already recorded", Toast.LENGTH_SHORT).show();
                                } else {
                                    songsID = postAffirmation.getData().get(position).getId().toString();
                                    favourite = postAffirmation.getData().get(position).getFavriteStatus().toString();
                                    titlea = postAffirmation.getData().get(position).getSongsTitle().toString();
                                    start();
                                }
                            }

                            @Override
                            public void onfavourite(int position, boolean b) {

                                parta = null;
                                if (b) {
                                    favourite = "1";
                                } else {
                                    favourite = "0";
                                }
                                titlea = postAffirmation.getData().get(position).getSongsTitle().toString();
                                songsID = postAffirmation.getData().get(position).getId().toString();
                                Log.e("pos", String.valueOf(position) + titlea + "-" + songsID);

                                RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), shared_userID);
                                RequestBody title = RequestBody.create(MediaType.parse("text/plain"), titlea);
                                RequestBody categoryId = RequestBody.create(MediaType.parse("text/plain"), cat_ID);
                                RequestBody songsId = RequestBody.create(MediaType.parse("text/plain"), songsID);
                                RequestBody fav = RequestBody.create(MediaType.parse("text/plain"), favourite);

                                addAffirmation(userId, title, categoryId, songsId, fav, parta);

                            }
                        });
                    } else {
                        Toast.makeText(WeightActivityNew.this, postAffirmation.getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WeightActivityNew.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostAffirmation> call, Throwable t) {
                Toast.makeText(WeightActivityNew.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void start() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            } else {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(path);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                try {
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    Log.e("exception", e.getMessage().toString());
                }
                mediaRecorder.start();
                Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_LONG).show();
            }
        } else {
            if (ContextCompat.checkSelfPermission
                    (WeightActivityNew.this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        WeightActivityNew.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_AUDIO_PERMISSION_CODE);
            }
        }
    }

    public void stop() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();

            if (path != null) {
                file = new File(path);
            }

//            MediaPlayer mediaPlayer = null;
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer = MediaPlayer.create(this, Uri.parse(path));
//            mediaPlayer.start();

//            Uri fileuri = Uri.fromFile(file);
//            String fileExt = MimeTypeMap.getFileExtensionFromUrl(fileuri.toString());
            Log.e("ext", file.getName().toString());

            RequestBody fileReqBody = RequestBody.create(MediaType.parse("audio/*"), file);
            parta = MultipartBody.Part.createFormData("songs", file.getName(), fileReqBody);

            RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), shared_userID);
            RequestBody title = RequestBody.create(MediaType.parse("text/plain"), titlea);
            RequestBody categoryId = RequestBody.create(MediaType.parse("text/plain"), cat_ID);
            RequestBody songsId = RequestBody.create(MediaType.parse("text/plain"), songsID);
            RequestBody fav = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(favourite));

            addAffirmation(userId, title, categoryId, songsId, fav, parta);
        }
    }

//    public void checkPermission() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);
//            }
//        } else {
//            if (ContextCompat.checkSelfPermission(WeightActivityNew.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity) WeightActivityNew.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);
//            }
//        }
//
//    }

    private File createImageFile() throws IOException {
        // Create an sound file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        String soundFileName = "MP3" + timeStamp + "_";
        File sound = new File(storageDir, soundFileName + ".mp3");
        // Save a file: path for use with ACTION_VIEW intents
        path = sound.getAbsolutePath();
        return sound;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Recording permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "Recording permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

}
