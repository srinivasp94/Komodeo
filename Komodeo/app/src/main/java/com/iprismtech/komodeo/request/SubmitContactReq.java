package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubmitContactReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image1")
    @Expose
    public String image1;
    @SerializedName("image2")
    @Expose
    public String image2;
    @SerializedName("image3")
    @Expose
    public String image3;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("description", description).append("image1", image1).append("image2", image2).append("image3", image3).append("token", token).toString();
    }
}
