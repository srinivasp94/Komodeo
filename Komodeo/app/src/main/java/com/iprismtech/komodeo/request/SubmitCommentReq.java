package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubmitCommentReq {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("class_id")
    @Expose
    public String classId;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("discussion_id")
    @Expose
    public String discussionId;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("classId", classId).append("universityId", universityId).append("discussionId", discussionId).append("comment", comment).append("token", token).toString();
    }

}
