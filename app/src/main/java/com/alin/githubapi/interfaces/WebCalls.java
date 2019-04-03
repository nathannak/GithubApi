package com.alin.githubapi.interfaces;

import com.alin.githubapi.models.dto.response.ReposResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebCalls {

    @GET("repositories")
    Call<ReposResponse> fetchUserRepos(@Query(value ="q", encoded=true) String query,@Query("sort") String sort,@Query("order") String order);

}
