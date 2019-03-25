package com.iprismtech.komodeo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserProfileRequest {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public UserProfilePojo response;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

}
