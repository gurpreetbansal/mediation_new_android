package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVoiceData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("voices")
    @Expose
    private String voices;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GetVoiceData withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetVoiceData withName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GetVoiceData withImage(String image) {
        this.image = image;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public GetVoiceData withFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public String getVoices() {
        return voices;
    }

    public void setVoices(String voices) {
        this.voices = voices;
    }

    public GetVoiceData withVoices(String voices) {
        this.voices = voices;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GetVoiceData withStatus(Integer status) {
        this.status = status;
        return this;
    }
}
