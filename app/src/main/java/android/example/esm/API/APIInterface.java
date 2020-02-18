package android.example.esm.API;

import android.example.esm.surveymodule.models.SurveyForm;
import android.example.esm.loginmodule.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("api/respondent/signup")
    Call<Account> createAccount(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/client/signin")
    Call<Account> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("api/respondents")
    Call<Account> getAllUsers();

    @GET("api/view-all-survey")
    Call<List<SurveyForm>> getSurveys();

}
