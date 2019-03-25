package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AcceptFrdRequestReq
{
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("friend_id")
    @Expose
    public String friendId;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("friendId", friendId).append("status", status).append("token", token).toString();
    }
}
