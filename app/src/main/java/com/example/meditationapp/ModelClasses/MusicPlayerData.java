package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicPlayerData {

    @SerializedName("nature")
    @Expose
    private List<MusicPlayerNature> nature = null;

    public List<MusicPlayerNature> getNature() {
        return nature;
    }

    public void setNature(List<MusicPlayerNature> nature) {
        this.nature = nature;
    }

}
