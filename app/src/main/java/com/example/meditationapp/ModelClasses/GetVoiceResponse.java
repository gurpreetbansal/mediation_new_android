package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVoiceResponse {
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
    private List<GetVoiceData> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GetVoiceResponse withCode(String code) {
        this.code = code;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public GetVoiceResponse withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public GetVoiceResponse withMessages(String messages) {
        this.messages = messages;
        return this;
    }

    public List<GetVoiceData> getData() {
        return data;
    }

    public void setData(List<GetVoiceData> data) {
        this.data = data;
    }

    public GetVoiceResponse withData(List<GetVoiceData> data) {
        this.data = data;
        return this;
    }
}
