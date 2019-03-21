package com.iprismtech.komodeo.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClassList {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("class_id")
    @Expose
    public String classId;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("subject_name")
    @Expose
    public String subjectName;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("icon")
    @Expose
    public Object icon;
    @SerializedName("color_code")
    @Expose
    public String colorCode;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    @SerializedName("modified_on")
    @Expose
    public Object modifiedOn;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("classId", classId).append("createdOn", createdOn).append("subjectName", subjectName).append("title", title).append("icon", icon).append("colorCode", colorCode).append("deleteStatus", deleteStatus).append("modifiedOn", modifiedOn).toString();
    }
}

