package com.attila.szelei.nasaapiapp.model;

import com.google.gson.annotations.SerializedName;

public class NasaApiList {
    @SerializedName("endpoint")
    private String endpoint;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
