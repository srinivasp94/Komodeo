package com.iprismtech.komodeo.pojo;

import java.util.List;

public class PersonalChatPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"57","sender_id":"2","receiver_id":"1","message":"test Message","created_on":"2019-03-15 18:02:25","modified_on":null,"profile_pic":"storage/2.jpg","first_name":"Jhon","last_name":"Doe","user_type":"receiver"},{"id":"56","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 18:00:40","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"55","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:34:51","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"54","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:34:41","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"53","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:34:33","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"52","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:34:23","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"51","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:34:13","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"},{"id":"50","sender_id":"1","receiver_id":"2","message":"sdfsdfsdf","created_on":"2019-03-15 17:33:40","modified_on":null,"profile_pic":"storage/1.jpg","first_name":"Khadeer","last_name":"Mohammed","user_type":"sender"}]
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
         * id : 57
         * sender_id : 2
         * receiver_id : 1
         * message : test Message
         * created_on : 2019-03-15 18:02:25
         * modified_on : null
         * profile_pic : storage/2.jpg
         * first_name : Jhon
         * last_name : Doe
         * user_type : receiver
         */

        private String id;
        private String sender_id;
        private String receiver_id;
        private String message;
        private String created_on;
        private Object modified_on;
        private String profile_pic;
        private String first_name;
        private String last_name;
        private String user_type;

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

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }
    }
}
