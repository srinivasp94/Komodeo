package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateEventReq {

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
    @SerializedName("event_name")
    @Expose
    public String eventName;
    @SerializedName("event_type")
    @Expose
    public String eventType;
    @SerializedName("event_date")
    @Expose
    public String eventDate;
    @SerializedName("start_time")
    @Expose
    public String startTime;
    @SerializedName("end_time")
    @Expose
    public String endTime;
    @SerializedName("location_lat")
    @Expose
    public String locationLat;
    @SerializedName("location_lng")
    @Expose
    public String locationLng;
    @SerializedName("location_address")
    @Expose
    public String locationAddress;
    @SerializedName("people_invited")
    @Expose
    public String peopleInvited;
    @SerializedName("privacy")
    @Expose
    public String privacy;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("event_payment_type")
    @Expose
    public String eventPaymentType;
    @SerializedName("per_session")
    @Expose
    public String perSession;

    @SerializedName("per_head")
    @Expose
    public String per_head;

    @SerializedName("max_group_size")
    @Expose
    public String maxGroupSize;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("token", token)
                .append("userId", userId)
                .append("universityId", universityId)
                .append("classId", classId)
                .append("eventName", eventName)
                .append("eventType", eventType)
                .append("eventDate", eventDate)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("locationLat", locationLat)
                .append("locationLng", locationLng)
                .append("locationAddress", locationAddress)
                .append("peopleInvited", peopleInvited)
                .append("privacy", privacy)
                .append("note", note)
                .append("eventPaymentType", eventPaymentType)
                .append("perSession", perSession)
                .append("per_head", per_head)
                .append("maxGroupSize", maxGroupSize)
                .toString();
    }
}
