package com.iprismtech.komodeo.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TutorSingelRes {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public TutorResponse response;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }
}
