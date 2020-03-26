package com.example.meditationapp.Api;

import com.example.meditationapp.ModelClasses.CategoriesModelClass;
import com.example.meditationapp.ModelClasses.ForgetPasswordModel;
import com.example.meditationapp.ModelClasses.GetCategoriesModelClass;
import com.example.meditationapp.ModelClasses.GetEditProfileResponse;
import com.example.meditationapp.ModelClasses.GetProfileResponse;
import com.example.meditationapp.ModelClasses.GetResponsePricyAndPolicy;
import com.example.meditationapp.ModelClasses.GetResponseSubscription;
import com.example.meditationapp.ModelClasses.GetResponseTermsAndCondition;
import com.example.meditationapp.ModelClasses.LoginModelClass;
import com.example.meditationapp.ModelClasses.LoginSendData;
import com.example.meditationapp.ModelClasses.LogoutModelClass;
import com.example.meditationapp.ModelClasses.SignupModelClass;
import com.example.meditationapp.ModelClasses.SignupSendData;
import com.example.meditationapp.ModelClasses.SubscriptionModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("auth/register")
    Call<SignupModelClass> signup(@Body SignupSendData sendData);

    @POST("auth/login")
    Call<LoginModelClass> login(@Body LoginSendData loginSendData);

    @POST("auth/getProfile")
    Call<GetProfileResponse> getProfile(@Query("user_id") String userId);

    @POST("auth/editProfile")
    Call<GetEditProfileResponse> editProfile(@Query("user_id") String userId, @Query("first_name") String firstName,
                                             @Query("last_name") String lastName, @Query("old_password") String password,
                                             @Query("new_password") String new_password);

    @POST("auth/logout")
    Call<LogoutModelClass>  getLogout(@Query("user_id") String userid);

    @GET("auth/termsCondtions")
    Call<GetResponseTermsAndCondition> termsAndCondition();

    @GET("auth/privacyPolicy")
    Call<GetResponsePricyAndPolicy> privacyAndPolicy();

    @POST("auth/checkSubscription")
    Call<GetResponseSubscription> subscription(@Body SubscriptionModelClass subscriptionModelClass);

    @POST("auth/forgotPassword")
    Call<ForgetPasswordModel> forgetPassword(@Query("email")String email);

    @POST("collections/getContentsInfo")
    Call<GetCategoriesModelClass> getCategory(@Query("user_id") String userId,
                                                    @Query("type_id") String typeId);


}
