package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventCommentsReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("event_id")
    @Expose
    public String eventId;
    @SerializedName("count")
    @Expose
    public String count;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("eventId", eventId).append("count", count).toString();
    }

}
