package com.iprismtech.komodeo.pojo;

import java.util.List;

public class CommentsPostPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","user_id":"1","class_id":"1","university_id":"1","discussion_id":"1","comment":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","created_on":"2019-03-13 02:06:05","first_name":"Khadeer","last_name":"Mohammed","profile_pic":"storage/1.jpg"}]
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
         * id : 1
         * user_id : 1
         * class_id : 1
         * university_id : 1
         * discussion_id : 1
         * comment : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * created_on : 2019-03-13 02:06:05
         * first_name : Khadeer
         * last_name : Mohammed
         * profile_pic : storage/1.jpg
         */

        private String id;
        private String user_id;
        private String class_id;
        private String university_id;
        private String discussion_id;
        private String comment;
        private String created_on;
        private String first_name;
        private String last_name;
        private String profile_pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getUniversity_id() {
            return university_id;
        }

        public void setUniversity_id(String university_id) {
            this.university_id = university_id;
        }

        public String getDiscussion_id() {
            return discussion_id;
        }

        public void setDiscussion_id(String discussion_id) {
            this.discussion_id = discussion_id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
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
    }
}
