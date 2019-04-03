package com.alin.githubapi.interfaces;

public interface HTTPRequest<T> {
    public void onRequest();

    public void onResponse(T response);

    public void onError(String errorMessage);
}
