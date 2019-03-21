package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommunityMembersReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("class_id")
    @Expose
    public String classId;
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("classId", classId).append("count", count).append("token", token).toString();
    }
}
