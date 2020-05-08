package com.example.jd.net.api;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 这个代码不用动了
 */
public interface NetApi {

    @GET
    Observable<ResponseBody> get(@Url String url);

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap HashMap<String, String> queryMap);


    @POST
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String url, @QueryMap HashMap<String, String> queryMap);



}
