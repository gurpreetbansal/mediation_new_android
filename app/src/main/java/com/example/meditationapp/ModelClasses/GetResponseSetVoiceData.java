package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResponseSetVoiceData {
    @SerializedName("user_voice_id")
    @Expose
    private Integer userVoiceId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("voice_id")
    @Expose
    private String voiceId;
    @SerializedName("user_time")
    @Expose
    private String userTime;
    @SerializedName("on_off_status")
    @Expose
    private Integer onOffStatus;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getUserVoiceId() {
        return userVoiceId;
    }

    public void setUserVoiceId(Integer userVoiceId) {
        this.userVoiceId = userVoiceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public Integer getOnOffStatus() {
        return onOffStatus;
    }

    public void setOnOffStatus(Integer onOffStatus) {
        this.onOffStatus = onOffStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
