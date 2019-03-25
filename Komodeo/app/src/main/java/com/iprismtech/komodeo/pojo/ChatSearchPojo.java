package com.iprismtech.komodeo.pojo;

import java.util.List;

public class ChatSearchPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"59","sender_id":"3","receiver_id":"1","message":"3rd user chat last","created_on":"2019-03-15 03:03:00","modified_on":null,"first_name":"Smith","last_name":"Jhony","profile_pic":"storage/3.jpg","major":"Finance","email_id":"smith@gmail.com","mobile":"9999888888"}]
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
         * id : 59
         * sender_id : 3
         * receiver_id : 1
         * message : 3rd user chat last
         * created_on : 2019-03-15 03:03:00
         * modified_on : null
         * first_name : Smith
         * last_name : Jhony
         * profile_pic : storage/3.jpg
         * major : Finance
         * email_id : smith@gmail.com
         * mobile : 9999888888
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
    }
}
