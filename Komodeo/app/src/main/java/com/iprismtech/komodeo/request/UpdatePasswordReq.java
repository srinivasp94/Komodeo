package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdatePasswordReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("password")
    @Expose
    public String password;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("password", password).toString();
    }

}
