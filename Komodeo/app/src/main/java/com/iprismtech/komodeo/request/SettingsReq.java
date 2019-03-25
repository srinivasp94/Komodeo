package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SettingsReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("major")
    @Expose
    public String major;
    @SerializedName("payment_preference")
    @Expose
    public String paymentPreference;
    @SerializedName("default_lat")
    @Expose
    public String defaultLat;
    @SerializedName("default_lng")
    @Expose
    public String defaultLng;
    @SerializedName("default_address")
    @Expose
    public String defaultAddress;
    @SerializedName("comments")
    @Expose
    public String comments;
    @SerializedName("likes")
    @Expose
    public String likes;
    @SerializedName("friend_requests")
    @Expose
    public String friendRequests;
    @SerializedName("events")
    @Expose
    public String events;
    @SerializedName("event_cancel")
    @Expose
    public String eventCancel;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("firstName", firstName).append("lastName", lastName).append("mobile", mobile).append("major", major).append("paymentPreference", paymentPreference).append("defaultLat", defaultLat).append("defaultLng", defaultLng).append("defaultAddress", defaultAddress).append("comments", comments).append("likes", likes).append("friendRequests", friendRequests).append("events", events).append("eventCancel", eventCancel).append("profilePic", profilePic).append("token", token).toString();
    }
}
