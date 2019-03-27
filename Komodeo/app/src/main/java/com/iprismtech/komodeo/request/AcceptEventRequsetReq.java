package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AcceptEventRequsetReq {

    @SerializedName("event_member_id")
    @Expose
    public String eventMemberId;
    @SerializedName("event_id")
    @Expose
    public String eventId;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("eventMemberId", eventMemberId).append("eventId", eventId).append("token", token).toString();
    }
}
