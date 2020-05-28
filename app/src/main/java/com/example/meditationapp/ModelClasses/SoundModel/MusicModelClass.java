package com.example.meditationapp.ModelClasses.SoundModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicModelClass {

    @SerializedName("nature_id")
    @Expose
    private Integer natureId;
    @SerializedName("nature_name")
    @Expose
    private String natureName;
    @SerializedName("nature_image")
    @Expose
    private String natureImage;
    @SerializedName("icon")
    @Expose
    private Object icon;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("lock_images")
    @Expose
    private String lockImages;
    @SerializedName("nature_sound")
    @Expose
    private String natureSound;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("music_type")
    @Expose
    private String musicType;
    @SerializedName("nature_date")
    @Expose
    private String natureDate;
    @SerializedName("nature_status")
    @Expose
    private Integer natureStatus;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("lockUnlockStatus")
    @Expose
    private Integer lockUnlockStatus;
    @SerializedName("songs")
    @Expose
    private String songs;

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

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLockImages() {
        return lockImages;
    }

    public void setLockImages(String lockImages) {
        this.lockImages = lockImages;
    }

    public String getNatureSound() {
        return natureSound;
    }

    public void setNatureSound(String natureSound) {
        this.natureSound = natureSound;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getLockUnlockStatus() {
        return lockUnlockStatus;
    }

    public void setLockUnlockStatus(Integer lockUnlockStatus) {
        this.lockUnlockStatus = lockUnlockStatus;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }
}
