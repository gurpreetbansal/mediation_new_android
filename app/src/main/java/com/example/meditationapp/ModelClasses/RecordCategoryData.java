package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecordCategoryData {

    @SerializedName("playall")
    @Expose
    private List<String> playall = null;
    @SerializedName("category")
    @Expose
    private List<RecordingCategory> category = null;

    public List<String> getPlayall() {
        return playall;
    }

    public void setPlayall(List<String> playall) {
        this.playall = playall;
    }

    public List<RecordingCategory> getCategory() {
        return category;
    }

    public void setCategory(List<RecordingCategory> category) {
        this.category = category;
    }

}
