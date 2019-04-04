package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iprismtech.komodeo.pojo.ForgotPasswordPojo;
import com.iprismtech.komodeo.pojo.UserProfilePojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForgotPasswordReq {
    @SerializedName("email_id")
    @Expose
    public String emailId;
    public ForgotPasswordPojo response;


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("emailId", emailId).toString();
    }
}
