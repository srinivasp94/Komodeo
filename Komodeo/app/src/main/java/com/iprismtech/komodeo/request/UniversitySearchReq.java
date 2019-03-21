package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UniversitySearchReq {
    @SerializedName("keyword")
    @Expose
    public String keyword;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("keyword", keyword).toString();
    }

}
