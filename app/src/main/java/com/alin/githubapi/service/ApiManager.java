package com.alin.githubapi.service;

import com.alin.githubapi.interfaces.HTTPRequest;
import com.alin.githubapi.interfaces.WebCalls;
import com.alin.githubapi.models.dto.response.ReposResponse;
import com.alin.githubapi.models.network.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {

    private static ApiManager instance = new ApiManager();
    private HTTPRequest httpRequest;

    private ApiManager() {

    }

    public static ApiManager getInstance() {
        return instance;
    }

    public void fetchRepos(String query,String sortType, String order, final HTTPRequest httpCallbacks) {
        this.httpRequest = httpCallbacks;
        Retrofit retrofit = APIClient.getClient();
        WebCalls apiService = retrofit.create(WebCalls.class);

        httpRequest.onRequest();
        apiService.fetchUserRepos(query,sortType,order).enqueue(new Callback<ReposResponse>() {

            @Override
            public void onResponse(Call<ReposResponse> call, Response<ReposResponse> response) {

                httpRequest.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<ReposResponse> call, Throwable t) {

                httpRequest.onError(t.getMessage());
            }
        });
    }

}
