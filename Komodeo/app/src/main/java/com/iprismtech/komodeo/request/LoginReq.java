package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginReq {
    /*{
        "email_id":"khadeer.md@iprismtech.com",
            "password":"123456"
    }*/
    @SerializedName("email_id")
    @Expose
    public String email_id;

    @SerializedName("password")
    @Expose
    public String password;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email_id", email_id)
                .append("password", password)
                .toString();
    }
}
