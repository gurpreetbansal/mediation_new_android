package com.example.meditationapp.ModelClasses.FavoriteModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubFavoritesModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("songs")
    @Expose
    private String songs;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("songs_images")
    @Expose
    private String songsImages;
    @SerializedName("songs_date")
    @Expose
    private String songsDate;
    @SerializedName("songs_status")
    @Expose
    private Integer songsStatus;
    @SerializedName("favrite_status")
    @Expose
    private Integer favriteStatus;
    @SerializedName("songs_title")
    @Expose
    private String songsTitle;
    @SerializedName("songs_description")
    @Expose
    private String songsDescription;
    @SerializedName("songs_duration")
    @Expose
    private String songsDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getSongsImages() {
        return songsImages;
    }

    public void setSongsImages(String songsImages) {
        this.songsImages = songsImages;
    }

    public String getSongsDate() {
        return songsDate;
    }

    public void setSongsDate(String songsDate) {
        this.songsDate = songsDate;
    }

    public Integer getSongsStatus() {
        return songsStatus;
    }

    public void setSongsStatus(Integer songsStatus) {
        this.songsStatus = songsStatus;
    }

    public Integer getFavriteStatus() {
        return favriteStatus;
    }

    public void setFavriteStatus(Integer favriteStatus) {
        this.favriteStatus = favriteStatus;
    }

    public String getSongsTitle() {
        return songsTitle;
    }

    public void setSongsTitle(String songsTitle) {
        this.songsTitle = songsTitle;
    }

    public String getSongsDescription() {
        return songsDescription;
    }

    public void setSongsDescription(String songsDescription) {
        this.songsDescription = songsDescription;
    }

    public String getSongsDuration() {
        return songsDuration;
    }

    public void setSongsDuration(String songsDuration) {
        this.songsDuration = songsDuration;
    }
}
