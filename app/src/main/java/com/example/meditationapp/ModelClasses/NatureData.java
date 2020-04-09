package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NatureData {

    @SerializedName("nature_id")
    @Expose
    private Integer natureId;
    @SerializedName("nature_name")
    @Expose
    private String natureName;
    @SerializedName("nature_image")
    @Expose
    private String natureImage;
    @SerializedName("nature_sound")
    @Expose
    private String natureSound;
    @SerializedName("nature_date")
    @Expose
    private String natureDate;
    @SerializedName("nature_status")
    @Expose
    private Integer natureStatus;
    @SerializedName("songs")
    @Expose
    private String songs;
    @SerializedName("images")
    @Expose
    private String images;

    public Integer getNatureId() {
        return natureId;
    }

    public void setNatureId(Integer natureId) {
        this.natureId = natureId;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getNatureImage() {
        return natureImage;
    }

    public void setNatureImage(String natureImage) {
        this.natureImage = natureImage;
    }

    public String getNatureSound() {
        return natureSound;
    }

    public void setNatureSound(String natureSound) {
        this.natureSound = natureSound;
    }

    public String getNatureDate() {
        return natureDate;
    }

    public void setNatureDate(String natureDate) {
        this.natureDate = natureDate;
    }

    public Integer getNatureStatus() {
        return natureStatus;
    }

    public void setNatureStatus(Integer natureStatus) {
        this.natureStatus = natureStatus;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

}
