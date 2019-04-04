package com.iprismtech.komodeo.pojo;

import java.util.List;

public class ChatSearchPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"194","sender_id":"10","receiver_id":"1","message":"Send","created_on":"02 April 2019 02:08 PM","modified_on":null,"first_name":"rekha","last_name":"s","profile_pic":"storage/profile_pics/5c9cb68491f69.png","major":"","email_id":"rekha@test.com","mobile":"","original_receiver_id":"10"},{"id":"187","sender_id":"13","receiver_id":"1","message":"Else","created_on":"02 April 2019 02:05 PM","modified_on":null,"first_name":"sweety","last_name":"s","profile_pic":"storage/profile_pics/5ca33405b439c.png","major":"","email_id":"sweety@test.com","mobile":"","original_receiver_id":"13"},{"id":"212","sender_id":"1","receiver_id":"14","message":"test","created_on":"02 April 2019 06:04 PM","modified_on":null,"first_name":"Khadeer","last_name":"Mohammed","profile_pic":"storage/profile_pics/5ca1d9249ad9c.png","major":"finance","email_id":"khadeer.md@iprismtech.com","mobile":"9123456789","original_receiver_id":"14"},{"id":"193","sender_id":"1","receiver_id":"2","message":"Send","created_on":"02 April 2019 02:07 PM","modified_on":null,"first_name":"Khadeer","last_name":"Mohammed","profile_pic":"storage/profile_pics/5ca1d9249ad9c.png","major":"finance","email_id":"khadeer.md@iprismtech.com","mobile":"9123456789","original_receiver_id":"2"},{"id":"192","sender_id":"1","receiver_id":"3","message":"Send","created_on":"02 April 2019 02:07 PM","modified_on":null,"first_name":"Khadeer","last_name":"Mohammed","profile_pic":"storage/profile_pics/5ca1d9249ad9c.png","major":"finance","email_id":"khadeer.md@iprismtech.com","mobile":"9123456789","original_receiver_id":"3"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 194
         * sender_id : 10
         * receiver_id : 1
         * message : Send
         * created_on : 02 April 2019 02:08 PM
         * modified_on : null
         * first_name : rekha
         * last_name : s
         * profile_pic : storage/profile_pics/5c9cb68491f69.png
         * major :
         * email_id : rekha@test.com
         * mobile :
         * original_receiver_id : 10
         */

        private String id;
        private String sender_id;
        private String receiver_id;
        private String message;
        private String created_on;
        private Object modified_on;
        private String first_name;
        private String last_name;
        private String profile_pic;
        private String major;
        private String email_id;
        private String mobile;
        private String original_receiver_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSender_id() {
            return sender_id;
        }

        public void setSender_id(String sender_id) {
            this.sender_id = sender_id;
        }

        public String getReceiver_id() {
            return receiver_id;
        }

        public void setReceiver_id(String receiver_id) {
            this.receiver_id = receiver_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOriginal_receiver_id() {
            return original_receiver_id;
        }

        public void setOriginal_receiver_id(String original_receiver_id) {
            this.original_receiver_id = original_receiver_id;
        }
    }
}
