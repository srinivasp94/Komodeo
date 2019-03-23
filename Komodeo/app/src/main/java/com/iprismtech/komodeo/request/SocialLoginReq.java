package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SocialLoginReq {
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email_id")
    @Expose
    public String emailId;
    @SerializedName("university_id")
    @Expose
    public String universityId;
    @SerializedName("registered_through")
    @Expose
    public String registeredThrough;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName).append("emailId", emailId).append("universityId", universityId).append("registeredThrough", registeredThrough).toString();
    }

}
