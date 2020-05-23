package com.example.meditationapp.ModelClasses.FavoriteModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFavouriteData {

    @SerializedName("playall")
    @Expose
    private List<String> playall = null;
    @SerializedName("categories")
    @Expose
    private List<FavoritesModelClass> categories = null;

    public List<String> getPlayall() {
        return playall;
    }

    public void setPlayall(List<String> playall) {
        this.playall = playall;
    }

    public List<FavoritesModelClass> getCategories() {
        return categories;
    }

    public void setCategories(List<FavoritesModelClass> categories) {
        this.categories = categories;
    }

}
