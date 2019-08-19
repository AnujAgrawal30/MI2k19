package com.example.mi_2k19.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "https:api.moodi.org";

    @GET("/blog")
    Call<List<Hero>> getHeroes();
}