package com.iprismtech.komodeo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProfileSettingsPojo {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email_id")
    @Expose
    public String emailId;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("major")
    @Expose
    public String major;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("default_lat")
    @Expose
    public String defaultLat;
    @SerializedName("default_lng")
    @Expose
    public String defaultLng;
    @SerializedName("default_address")
    @Expose
    public String defaultAddress;
    @SerializedName("registered_through")
    @Expose
    public String registeredThrough;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("payment_preference")
    @Expose
    public String paymentPreference;
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
    @SerializedName("token")
    @Expose
    public Object token;
    @SerializedName("ios_token")
    @Expose
    public Object iosToken;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("university")
    @Expose
    public String university;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("firstName", firstName).append("lastName", lastName).append("emailId", emailId).append("mobile", mobile).append("password", password).append("profilePic", profilePic).append("major", major).append("universityId", universityId).append("defaultLat", defaultLat).append("defaultLng", defaultLng).append("defaultAddress", defaultAddress).append("registeredThrough", registeredThrough).append("bio", bio).append("paymentPreference", paymentPreference).append("comments", comments).append("likes", likes).append("friendRequests", friendRequests).append("events", events).append("eventCancel", eventCancel).append("token", token).append("iosToken", iosToken).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("university", university).toString();
    }
}
