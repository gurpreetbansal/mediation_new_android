package com.example.meditationapp.Api;

import com.example.meditationapp.ModelClasses.ForgetPasswordModel;
import com.example.meditationapp.ModelClasses.GetCategoriesModelClass;
import com.example.meditationapp.ModelClasses.GetEditProfileResponse;
import com.example.meditationapp.ModelClasses.GetHomeResponse;
import com.example.meditationapp.ModelClasses.GetProfileResponse;
import com.example.meditationapp.ModelClasses.GetResponsePricyAndPolicy;
import com.example.meditationapp.ModelClasses.GetResponseSetVoice;
import com.example.meditationapp.ModelClasses.GetResponseSubscription;
import com.example.meditationapp.ModelClasses.GetResponseTermsAndCondition;
import com.example.meditationapp.ModelClasses.GetSocialLoginResponse;
import com.example.meditationapp.ModelClasses.GetSupportResponse;
import com.example.meditationapp.ModelClasses.GetVoiceResponse;
import com.example.meditationapp.ModelClasses.LoginModelClass;
import com.example.meditationapp.ModelClasses.LoginSendData;
import com.example.meditationapp.ModelClasses.LogoutModelClass;
import com.example.meditationapp.ModelClasses.SetCategoriesModelClass;
import com.example.meditationapp.ModelClasses.SetCategoryResponse;
import com.example.meditationapp.ModelClasses.SetVoiceModelClass;
import com.example.meditationapp.ModelClasses.SignupModelClass;
import com.example.meditationapp.ModelClasses.SignupSendData;
import com.example.meditationapp.ModelClasses.SubscriptionModelClass;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("auth/register")
    Call<SignupModelClass> signup(@Body SignupSendData sendData);

    @POST("auth/login")
    Call<LoginModelClass> login(@Body LoginSendData loginSendData);

    @POST("auth/getProfile")
    Call<GetProfileResponse> getProfile(@Query("user_id") String userId);

    @Multipart
    @POST("auth/editProfile")
    Call<GetEditProfileResponse> editProfile(@Part("user_id") RequestBody userId, @Part("first_name") RequestBody firstName,
                                             @Part("last_name") RequestBody lastName, @Part("old_password") RequestBody password,
                                             @Part("new_password") RequestBody new_password, @Part MultipartBody.Part part);


    @POST("auth/socailLogin")
    Call<GetSocialLoginResponse> getSocialLogin(@Query("social_id") String socialId, @Query("social_type") String socialType,
                                                @Query("email") String email, @Query("profile") String profile,
                                                @Query("first_name") String name, @Query("device_type") String deviceType,
                                                @Query("device_token") String deviceToken);

    @POST("auth/logout")
    Call<LogoutModelClass> getLogout(@Query("user_id") String userid);

    @POST("collections/setVoice")
    Call<GetResponseSetVoice> setVoice(@Body SetVoiceModelClass setVoiceModelClass);

    @POST("collections/getVoice")
    Call<GetVoiceResponse> getVoiceResponse();

    @GET("auth/termsCondtions")
    Call<GetResponseTermsAndCondition> termsAndCondition();

    @GET("auth/privacyPolicy")
    Call<GetResponsePricyAndPolicy> privacyAndPolicy();

    @POST("auth/checkSubscription")
    Call<GetResponseSubscription> subscription(@Body SubscriptionModelClass subscriptionModelClass);

    @POST("auth/forgotPassword")
    Call<ForgetPasswordModel> forgetPassword(@Query("email") String email);

    //    @POST("collections/getContentsInfo")
    @POST("collections/getCategoryes")
    Call<GetCategoriesModelClass> getCategory(@Query("user_id") String userId,
                                              @Query("type_id") String typeId);

//    @POST("collections/collectCategory")
//    Call<SetCategoriesModelClass> setCategory(@Query("user_id") String userId,
//                                              @Query("category_id") List<String> catagoryID);

    @POST("collections/collectCategory")
    Call<SetCategoriesModelClass> setCategory(@Body SetCategoryResponse setCategoryResponse);

    @POST("collections/randomCategory")
    Call<GetHomeResponse> getHome(@Query("user_id") String userId, @Query("type_id") String typeId);

    @POST("collections/support")
    Call<GetSupportResponse> sendQuery(@Query("user_id") String userId, @Query("suppert_subject") String subject, @Query("support_message") String message);

}
