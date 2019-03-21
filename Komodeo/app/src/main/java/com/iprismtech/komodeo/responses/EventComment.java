package com.iprismtech.komodeo.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventComment {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("profile_pic")
    @Expose
    public Object profilePic;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("created_on")
    @Expose
    public String createdOn;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("firstName", firstName).append("lastName", lastName).append("profilePic", profilePic).append("comment", comment).append("createdOn", createdOn).toString();
    }
}
