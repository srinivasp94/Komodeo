package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GiveRatingReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("given_by")
    @Expose
    public String givenBy;
    @SerializedName("ratings")
    @Expose
    public String ratings;
    @SerializedName("reviews")
    @Expose
    public String reviews;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("userId", userId).append("givenBy", givenBy).append("ratings", ratings).append("reviews", reviews).toString();
    }
}
