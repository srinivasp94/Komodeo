package com.iprismtech.komodeo.retrofitnetwork;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface APIInterface {
    @POST
    Call<Object> callPost(@Url String path, @Body Object o);

    @GET
    Call<Object> callGet(@Url String path);

    @Multipart
    @POST
    Call<String> uploadFile(@Url String path,
                            @Part MultipartBody.Part file);
}
