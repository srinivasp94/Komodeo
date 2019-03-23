package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddClassReq {

    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("subject_name")
    @Expose
    public String subjectName;
    @SerializedName("course_name")
    @Expose
    public String courseName;
    @SerializedName("user_id")
    @Expose
    public String userId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("universityId", universityId).append("subjectName", subjectName).append("courseName", courseName).append("userId", userId).toString();
    }
}
