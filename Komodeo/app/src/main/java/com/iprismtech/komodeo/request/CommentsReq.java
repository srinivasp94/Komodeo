package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommentsReq {
    @SerializedName("discussion_id")
    @Expose
    public String discussionId;
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("discussionId", discussionId).append("count", count).append("token", token).toString();
    }

}
