package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriptionModelClass {

    @SerializedName("is_subscription")
    @Expose
    private Integer isSubscription;

    public Integer getIsSubscription() {
        return isSubscription;
    }

    public void setIsSubscription(Integer isSubscription) {
        this.isSubscription = isSubscription;
    }


}
