package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicPlayerResponse {

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
    private MusicPlayerData data;

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

    public MusicPlayerData getData() {
        return data;
    }

    public void setData(MusicPlayerData data) {
        this.data = data;
    }

}
