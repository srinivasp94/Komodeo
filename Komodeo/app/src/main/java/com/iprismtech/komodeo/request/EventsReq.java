package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventsReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("class_id")
    @Expose
    public String classId;
    @SerializedName("event_type")
    @Expose
    public String eventType;
    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("count")
    @Expose
    public String count;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("userId", userId).append("universityId", universityId).append("classId", classId).append("eventType", eventType).append("section", section).append("count", count).toString();
    }

}
