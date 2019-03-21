package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchClassesReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("keyword")
    @Expose
    public String keyword;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("keyword", keyword).toString();
    }
}
