package com.example.meditationapp.ModelClasses.UserPayModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPayModelClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("payment_users")
    @Expose
    private Integer paymentUsers;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("payment_amount")
    @Expose
    private Integer paymentAmount;
    @SerializedName("payment_date")
    @Expose
    private String paymentDate;
    @SerializedName("payment_plan_id")
    @Expose
    private Integer paymentPlanId;
    @SerializedName("payment_plan_name")
    @Expose
    private String paymentPlanName;
    @SerializedName("currency_code")
    @Expose
    private Object currencyCode;
    @SerializedName("short_description")
    @Expose
    private Object shortDescription;
    @SerializedName("coupen")
    @Expose
    private Object coupen;
    @SerializedName("intent")
    @Expose
    private Object intent;
    @SerializedName("payment_refund_date")
    @Expose
    private String paymentRefundDate;
    @SerializedName("payment_refund_id")
    @Expose
    private Object paymentRefundId;
    @SerializedName("payment_refund_amount")
    @Expose
    private Object paymentRefundAmount;
    @SerializedName("payment_status")
    @Expose
    private Integer paymentStatus;
    @SerializedName("package_type")
    @Expose
    private Integer packageType;
    @SerializedName("subscription")
    @Expose
    private Object subscription;
    @SerializedName("screen_name")
    @Expose
    private Object screenName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentUsers() {
        return paymentUsers;
    }

    public void setPaymentUsers(Integer paymentUsers) {
        this.paymentUsers = paymentUsers;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Integer paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public String getPaymentPlanName() {
        return paymentPlanName;
    }

    public void setPaymentPlanName(String paymentPlanName) {
        this.paymentPlanName = paymentPlanName;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Object getCoupen() {
        return coupen;
    }

    public void setCoupen(Object coupen) {
        this.coupen = coupen;
    }

    public Object getIntent() {
        return intent;
    }

    public void setIntent(Object intent) {
        this.intent = intent;
    }

    public String getPaymentRefundDate() {
        return paymentRefundDate;
    }

    public void setPaymentRefundDate(String paymentRefundDate) {
        this.paymentRefundDate = paymentRefundDate;
    }

    public Object getPaymentRefundId() {
        return paymentRefundId;
    }

    public void setPaymentRefundId(Object paymentRefundId) {
        this.paymentRefundId = paymentRefundId;
    }

    public Object getPaymentRefundAmount() {
        return paymentRefundAmount;
    }

    public void setPaymentRefundAmount(Object paymentRefundAmount) {
        this.paymentRefundAmount = paymentRefundAmount;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    public Object getSubscription() {
        return subscription;
    }

    public void setSubscription(Object subscription) {
        this.subscription = subscription;
    }

    public Object getScreenName() {
        return screenName;
    }

    public void setScreenName(Object screenName) {
        this.screenName = screenName;
    }

//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("payment_users")
//    @Expose
//    private Integer paymentUsers;
//    @SerializedName("payment_type")
//    @Expose
//    private String paymentType;
//    @SerializedName("payment_id")
//    @Expose
//    private String paymentId;
//    @SerializedName("payment_amount")
//    @Expose
//    private Integer paymentAmount;
//    @SerializedName("payment_date")
//    @Expose
//    private String paymentDate;
//    @SerializedName("payment_plan_id")
//    @Expose
//    private Integer paymentPlanId;
//    @SerializedName("payment_plan_name")
//    @Expose
//    private String paymentPlanName;
//    @SerializedName("currency_code")
//    @Expose
//    private Object currencyCode;
//    @SerializedName("short_description")
//    @Expose
//    private Object shortDescription;
//    @SerializedName("intent")
//    @Expose
//    private Object intent;
//    @SerializedName("payment_refund_date")
//    @Expose
//    private String paymentRefundDate;
//    @SerializedName("payment_refund_id")
//    @Expose
//    private Object paymentRefundId;
//    @SerializedName("payment_refund_amount")
//    @Expose
//    private Object paymentRefundAmount;
//    @SerializedName("payment_status")
//    @Expose
//    private Integer paymentStatus;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getPaymentUsers() {
//        return paymentUsers;
//    }
//
//    public void setPaymentUsers(Integer paymentUsers) {
//        this.paymentUsers = paymentUsers;
//    }
//
//    public String getPaymentType() {
//        return paymentType;
//    }
//
//    public void setPaymentType(String paymentType) {
//        this.paymentType = paymentType;
//    }
//
//    public String getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(String paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public Integer getPaymentAmount() {
//        return paymentAmount;
//    }
//
//    public void setPaymentAmount(Integer paymentAmount) {
//        this.paymentAmount = paymentAmount;
//    }
//
//    public String getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(String paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//
//    public Integer getPaymentPlanId() {
//        return paymentPlanId;
//    }
//
//    public void setPaymentPlanId(Integer paymentPlanId) {
//        this.paymentPlanId = paymentPlanId;
//    }
//
//    public String getPaymentPlanName() {
//        return paymentPlanName;
//    }
//
//    public void setPaymentPlanName(String paymentPlanName) {
//        this.paymentPlanName = paymentPlanName;
//    }
//
//    public Object getCurrencyCode() {
//        return currencyCode;
//    }
//
//    public void setCurrencyCode(Object currencyCode) {
//        this.currencyCode = currencyCode;
//    }
//
//    public Object getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(Object shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public Object getIntent() {
//        return intent;
//    }
//
//    public void setIntent(Object intent) {
//        this.intent = intent;
//    }
//
//    public String getPaymentRefundDate() {
//        return paymentRefundDate;
//    }
//
//    public void setPaymentRefundDate(String paymentRefundDate) {
//        this.paymentRefundDate = paymentRefundDate;
//    }
//
//    public Object getPaymentRefundId() {
//        return paymentRefundId;
//    }
//
//    public void setPaymentRefundId(Object paymentRefundId) {
//        this.paymentRefundId = paymentRefundId;
//    }
//
//    public Object getPaymentRefundAmount() {
//        return paymentRefundAmount;
//    }
//
//    public void setPaymentRefundAmount(Object paymentRefundAmount) {
//        this.paymentRefundAmount = paymentRefundAmount;
//    }
//
//    public Integer getPaymentStatus() {
//        return paymentStatus;
//    }
//
//    public void setPaymentStatus(Integer paymentStatus) {
//        this.paymentStatus = paymentStatus;
//    }

}
