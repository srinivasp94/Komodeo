package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookEventReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("event_id")
    @Expose
    public String eventId;
    @SerializedName("event_creator_user_id")
    @Expose
    public String eventCreatorUserId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("userId", userId).append("eventId", eventId).append("eventCreatorUserId", eventCreatorUserId).toString();
    }
}
