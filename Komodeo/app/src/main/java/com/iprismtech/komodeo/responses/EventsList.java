package com.iprismtech.komodeo.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventsList {
    @SerializedName("id")
    @Expose
    public String id;
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
    @SerializedName("per_head")
    @Expose
    public Object perHead;
    @SerializedName("per_session")
    @Expose
    public String perSession;
    @SerializedName("max_group_size")
    @Expose
    public String maxGroupSize;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public Object modifiedOn;
    @SerializedName("profile_pic")
    @Expose
    public Object profilePic;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("ratings")
    @Expose
    public String ratings;
    @SerializedName("total_price")
    @Expose
    public String totalPrice;
    @SerializedName("date_time")
    @Expose
    public String dateTime;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("event_member_id")
    @Expose
    public String event_member_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
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
                .append("perHead", perHead)
                .append("perSession", perSession)
                .append("maxGroupSize", maxGroupSize)
                .append("createdOn", createdOn)
                .append("modifiedOn", modifiedOn)
                .append("profilePic", profilePic)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("ratings", ratings)
                .append("totalPrice", totalPrice)
                .append("dateTime", dateTime)
                .append("date", date)
                .append("event_member_id", event_member_id)
                .toString();
    }
}
