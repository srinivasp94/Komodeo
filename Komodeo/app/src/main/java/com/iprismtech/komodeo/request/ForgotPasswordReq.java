package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForgotPasswordReq {
    @SerializedName("email_id")
    @Expose
    public String emailId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("emailId", emailId).toString();
    }
}
