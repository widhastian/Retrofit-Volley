package com.example.retrovolley.API;

import com.example.retrovolley.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel>ardCreateData(
            @Field("judul") String judul,
            @Field("penulis") String penulis,
            @Field("tahun_terbit") String tahun_terbit
    );
}
