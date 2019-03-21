package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchUsesReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("keyword")
    @Expose
    public String keyword;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("keyword", keyword).append("token", token).toString();
    }
}
