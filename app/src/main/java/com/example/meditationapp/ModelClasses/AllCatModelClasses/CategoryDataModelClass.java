package com.example.meditationapp.ModelClasses.AllCatModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDataModelClass {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("cat_id")
    @Expose
    private Object catId;
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
    @SerializedName("affirmation_songs")
    @Expose
    private Object affirmationSongs;
    @SerializedName("affirmation_images")
    @Expose
    private Object affirmationImages;
    @SerializedName("affirmation_duration")
    @Expose
    private Object affirmationDuration;
    @SerializedName("affirmation_title")
    @Expose
    private Object affirmationTitle;
    @SerializedName("affirmation_discription")
    @Expose
    private Object affirmationDiscription;
    @SerializedName("affirmation_favrites")
    @Expose
    private Object affirmationFavrites;
    @SerializedName("paymentStatus")
    @Expose
    private Integer paymentStatus;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getCatId() {
        return catId;
    }

    public void setCatId(Object catId) {
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

    public Object getAffirmationSongs() {
        return affirmationSongs;
    }

    public void setAffirmationSongs(Object affirmationSongs) {
        this.affirmationSongs = affirmationSongs;
    }

    public Object getAffirmationImages() {
        return affirmationImages;
    }

    public void setAffirmationImages(Object affirmationImages) {
        this.affirmationImages = affirmationImages;
    }

    public Object getAffirmationDuration() {
        return affirmationDuration;
    }

    public void setAffirmationDuration(Object affirmationDuration) {
        this.affirmationDuration = affirmationDuration;
    }

    public Object getAffirmationTitle() {
        return affirmationTitle;
    }

    public void setAffirmationTitle(Object affirmationTitle) {
        this.affirmationTitle = affirmationTitle;
    }

    public Object getAffirmationDiscription() {
        return affirmationDiscription;
    }

    public void setAffirmationDiscription(Object affirmationDiscription) {
        this.affirmationDiscription = affirmationDiscription;
    }

    public Object getAffirmationFavrites() {
        return affirmationFavrites;
    }

    public void setAffirmationFavrites(Object affirmationFavrites) {
        this.affirmationFavrites = affirmationFavrites;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
