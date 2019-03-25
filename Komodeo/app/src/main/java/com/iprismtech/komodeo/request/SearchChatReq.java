package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchChatReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("num_items")
    @Expose
    public String numItems;
    @SerializedName("keyword")
    @Expose
    public String keyword;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("numItems", numItems).append("keyword", keyword).append("token", token).toString();
    }
}
