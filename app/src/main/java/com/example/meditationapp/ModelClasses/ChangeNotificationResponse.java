package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeNotificationResponse {

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
    private ChangeNotificationData data;

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

    public ChangeNotificationData getData() {
        return data;
    }

    public void setData(ChangeNotificationData data) {
        this.data = data;
    }

}

class ChangeNotificationData {

    @SerializedName("is_notification")
    @Expose
    private Integer isNotification;

    public Integer getIsNotification() {
        return isNotification;
    }

    public void setIsNotification(Integer isNotification) {
        this.isNotification = isNotification;
    }

}
