package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class uploadCredentialsReq {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("class_id")
    @Expose
    public String classId;
    @SerializedName("images")
    @Expose
    public List<ImageList> images = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).append("userId", userId).append("classId", classId).append("images", images).toString();
    }

    public class ImageList {
        @SerializedName("image")
        @Expose
        public String image;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("image", image).toString();
        }
    }
}
