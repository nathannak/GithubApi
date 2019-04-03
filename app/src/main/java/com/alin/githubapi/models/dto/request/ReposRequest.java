package com.alin.githubapi.models.dto.request;

import java.io.Serializable;

public class ReposRequest implements Serializable
{
    private String query;

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }
}
