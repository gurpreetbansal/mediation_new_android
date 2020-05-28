package com.example.meditationapp.ModelClasses.SoundModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BothDataSoundAndScapes {

    @SerializedName("SoundScopes")
    @Expose
    private List<SoundScapeModelClass> soundScopes = null;
    @SerializedName("Music")
    @Expose
    private List<MusicModelClass> music = null;

    public List<SoundScapeModelClass> getSoundScopes() {
        return soundScopes;
    }

    public void setSoundScopes(List<SoundScapeModelClass> soundScopes) {
        this.soundScopes = soundScopes;
    }

    public List<MusicModelClass> getMusic() {
        return music;
    }

    public void setMusic(List<MusicModelClass> music) {
        this.music = music;
    }

}
