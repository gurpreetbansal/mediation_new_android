package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeData {
    @SerializedName("random")
    @Expose
    private RandomData random;
    @SerializedName("interested")
    @Expose
    private List<InterestedData> interested = null;
    @SerializedName("categories")
    @Expose
    private List<CategoryData> categories = null;
    @SerializedName("mystuff")
    @Expose
    private List<MystuffData> mystuff = null;
    @SerializedName("nature")
    @Expose
    private List<NatureData> nature = null;

    public RandomData getRandom() {
        return random;
    }

    public void setRandom(RandomData random) {
        this.random = random;
    }

    public List<InterestedData> getInterested() {
        return interested;
    }

    public void setInterested(List<InterestedData> interested) {
        this.interested = interested;
    }

    public List<CategoryData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryData> categories) {
        this.categories = categories;
    }

    public List<MystuffData> getMystuff() {
        return mystuff;
    }

    public void setMystuff(List<MystuffData> mystuff) {
        this.mystuff = mystuff;
    }

    public List<NatureData> getNature() {
        return nature;
    }

    public void setNature(List<NatureData> nature) {
        this.nature = nature;
    }
}
