package com.iprismtech.komodeo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ChatMsgSendReq {
    @SerializedName("sender_id")
    @Expose
    public String senderId;
    @SerializedName("receiver_id")
    @Expose
    public String receiverId;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("num_items")
    @Expose
    public String numItems;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("senderId", senderId).append("receiverId", receiverId).append("message", message).append("numItems", numItems).append("token", token).toString();
    }
}
