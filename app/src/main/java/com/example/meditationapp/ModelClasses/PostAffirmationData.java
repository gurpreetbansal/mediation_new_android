package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostAffirmationData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_delete")
    @Expose
    private Object isDelete;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("songs")
    @Expose
    private String songs;
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
    @SerializedName("upload_status")
    @Expose
    private Integer uploadStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Integer getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
