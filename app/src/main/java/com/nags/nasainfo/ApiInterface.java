package com.nags.nasainfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ravi Tamada on 21/02/17.
 * www.androidhive.info
 */

public interface ApiInterface {


    @GET("AndroidNew/Recharge/RechargeDetails.php")
    Call<List<InfoModel>> getQuery(@Query("uid") String UID, @Query("pwd") String PWD, @Query("pin") String pin, @Query("days") String day, @Query("deviceID") String deviceID, @Query("category") String category);

}








