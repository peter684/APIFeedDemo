package com.example.apifeeddemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("answers")
    Call<StackAPIResponse> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );
}
