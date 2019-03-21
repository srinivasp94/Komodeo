package com.iprismtech.komodeo.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FriendList {
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
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("registered_through")
    @Expose
    public String registeredThrough;
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
    public Object modifiedOn;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("firstName", firstName).append("lastName", lastName).append("emailId", emailId).append("password", password).append("universityId", universityId).append("registeredThrough", registeredThrough).append("comments", comments).append("likes", likes).append("friendRequests", friendRequests).append("events", events).append("eventCancel", eventCancel).append("token", token).append("iosToken", iosToken).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).toString();
    }


}
