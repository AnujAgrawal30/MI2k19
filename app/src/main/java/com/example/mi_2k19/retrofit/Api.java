package com.example.mi_2k19.retrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "https:api.moodi.org";

    @GET("/blog")
    Call<List<Hero>> getHeroes();


    @Multipart
    @POST("/api/post/")
    Call<ResponseBody> postFile(
            @Part("id") RequestBody id,
            @Part("bloger_name") RequestBody bloger_name,
            @Part MultipartBody.Part pic_url,
            @Part("types") RequestBody types,
            @Part("College") RequestBody college,
            @Part("bloger_topic") RequestBody bloger_topic,
            @Part("bloger_blog") RequestBody bloger_blog,
            @Part("bloger_status") RequestBody bloger_status,
            @Part MultipartBody.Part bloger_pic,
            @Part("fblink") RequestBody fblink,
            @Part("instalink") RequestBody instalink);


    @GET("blog/")
    Call<List<User>> getUsers();

    @GET("/cities")
    Call<List<Cities>> getCities();

    @GET("/colleges/{url}")
    Call<List<College>> getCollege(@Path("url") String id);

    @GET("college/{url}")
    Call<List<Student>> getStudent(@Path("url") String id);

}