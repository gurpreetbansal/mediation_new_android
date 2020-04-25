package com.example.meditationapp.ModelClasses.FavoriteModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoritesModelClass {

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
    @SerializedName("0")
    @Expose
    private List<SubFavoritesModelClass> subFavoritesModelClasses = null;

    private boolean isSelected = true;

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

    public List<SubFavoritesModelClass> getSubFavoritesModelClasses() {
        return subFavoritesModelClasses;
    }

    public void setSubFavoritesModelClasses(List<SubFavoritesModelClass> subFavoritesModelClasses) {
        this.subFavoritesModelClasses = subFavoritesModelClasses;
    }
    public boolean setSelected(boolean selected) {
        isSelected = selected;
        return selected;
    }


    public boolean isSelected() {
        return isSelected;
    }

}
