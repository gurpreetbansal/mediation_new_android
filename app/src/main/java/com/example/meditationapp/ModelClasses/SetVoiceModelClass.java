package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SetVoiceModelClass {
    @SerializedName("voice_id")
    @Expose
    private List<String> voiceId = null;
    @SerializedName("user_time")
    @Expose
    private String userTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public List<String> getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(List<String> voiceId) {
        this.voiceId = voiceId;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
