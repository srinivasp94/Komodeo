package com.iprismtech.komodeo.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class FriendList implements Parcelable {
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

    public boolean invite = false;

    protected FriendList(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        emailId = in.readString();
        password = in.readString();
        universityId = in.readString();
        registeredThrough = in.readString();
        comments = in.readString();
        likes = in.readString();
        friendRequests = in.readString();
        events = in.readString();
        eventCancel = in.readString();
        status = in.readString();
        deleteStatus = in.readString();
        createdOn = in.readString();
        invite = in.readByte() != 0;
    }

    public static final Creator<FriendList> CREATOR = new Creator<FriendList>() {
        @Override
        public FriendList createFromParcel(Parcel in) {
            return new FriendList(in);
        }

        @Override
        public FriendList[] newArray(int size) {
            return new FriendList[size];
        }
    };

    public boolean isInvite() {
        return invite;
    }

    public void setInvite(boolean invite) {
        this.invite = invite;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("emailId", emailId)
                .append("password", password)
                .append("universityId", universityId)
                .append("registeredThrough", registeredThrough)
                .append("comments", comments)
                .append("likes", likes)
                .append("friendRequests", friendRequests)
                .append("events", events)
                .append("eventCancel", eventCancel)
                .append("token", token)
                .append("iosToken", iosToken)
                .append("status", status)
                .append("deleteStatus", deleteStatus)
                .append("createdOn", createdOn)
                .append("modifiedOn", modifiedOn)
                .append("invite", invite)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//dest.writeString();
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(emailId);
        dest.writeString(password);
        dest.writeString(universityId);
        dest.writeString(registeredThrough);
        dest.writeString(comments);
        dest.writeString(likes);
        dest.writeString(friendRequests);
        dest.writeString(events);
        dest.writeString(eventCancel);
        dest.writeString(status);
        dest.writeString(deleteStatus);
        dest.writeString(createdOn);
        dest.writeByte((byte) (invite ? 1 : 0));
    }

}
