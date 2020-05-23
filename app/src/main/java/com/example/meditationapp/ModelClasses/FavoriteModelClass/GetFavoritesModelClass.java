package com.example.meditationapp.ModelClasses.FavoriteModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFavoritesModelClass {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("data")
    @Expose
    private GetFavouriteData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public GetFavouriteData getData() {
        return data;
    }

    public void setData(GetFavouriteData data) {
        this.data = data;
    }

//    @SerializedName("code")
//    @Expose
//    private String code;
//    @SerializedName("success")
//    @Expose
//    private Boolean success;
//    @SerializedName("messages")
//    @Expose
//    private String messages;
//    @SerializedName("data")
//    @Expose
//    private List<FavoritesModelClass> data = null;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public Boolean getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(Boolean success) {
//        this.success = success;
//    }
//
//    public String getMessages() {
//        return messages;
//    }
//
//    public void setMessages(String messages) {
//        this.messages = messages;
//    }
//
//    public List<FavoritesModelClass> getData() {
//        return data;
//    }
//
//    public void setData(List<FavoritesModelClass> data) {
//        this.data = data;
//    }

}
