package com.iprismtech.komodeo.pojo;

import java.util.List;

public class SearchCommunityPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"3","first_name":"Smith","last_name":"Jhony","email_id":"smith@gmail.com","mobile":"9999888888","password":"e10adc3949ba59abbe56e057f20f883e","profile_pic":"storage/3.jpg","major":"Finance","university_id":"1","registered_through":"normal","bio":null,"payment_preference":null,"comments":"yes","likes":"yes","friend_requests":"yes","events":"yes","event_cancel":"yes","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-03-15 00:00:00","modified_on":null},{"id":"2","first_name":"Jhon","last_name":"Doe","email_id":"jhon_doe@gmail.com","mobile":null,"password":"e10adc3949ba59abbe56e057f20f883e","profile_pic":"storage/2.jpg","major":null,"university_id":"1","registered_through":"normal","bio":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","payment_preference":null,"comments":"yes","likes":"yes","friend_requests":"yes","events":"yes","event_cancel":"yes","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-03-13 02:04:03","modified_on":"2019-03-13 20:36:45"}]
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
         * id : 3
         * first_name : Smith
         * last_name : Jhony
         * email_id : smith@gmail.com
         * mobile : 9999888888
         * password : e10adc3949ba59abbe56e057f20f883e
         * profile_pic : storage/3.jpg
         * major : Finance
         * university_id : 1
         * registered_through : normal
         * bio : null
         * payment_preference : null
         * comments : yes
         * likes : yes
         * friend_requests : yes
         * events : yes
         * event_cancel : yes
         * token : null
         * ios_token : null
         * status : 1
         * delete_status : 1
         * created_on : 2019-03-15 00:00:00
         * modified_on : null
         */

        private String id;
        private String first_name;
        private String last_name;
        private String email_id;
        private String mobile;
        private String password;
        private String profile_pic;
        private String major;
        private String university_id;
        private String registered_through;
        private Object bio;
        private Object payment_preference;
        private String comments;
        private String likes;
        private String friend_requests;
        private String events;
        private String event_cancel;
        private Object token;
        private Object ios_token;
        private String status;
        private String delete_status;
        private String created_on;
        private Object modified_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getUniversity_id() {
            return university_id;
        }

        public void setUniversity_id(String university_id) {
            this.university_id = university_id;
        }

        public String getRegistered_through() {
            return registered_through;
        }

        public void setRegistered_through(String registered_through) {
            this.registered_through = registered_through;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public Object getPayment_preference() {
            return payment_preference;
        }

        public void setPayment_preference(Object payment_preference) {
            this.payment_preference = payment_preference;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getFriend_requests() {
            return friend_requests;
        }

        public void setFriend_requests(String friend_requests) {
            this.friend_requests = friend_requests;
        }

        public String getEvents() {
            return events;
        }

        public void setEvents(String events) {
            this.events = events;
        }

        public String getEvent_cancel() {
            return event_cancel;
        }

        public void setEvent_cancel(String event_cancel) {
            this.event_cancel = event_cancel;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getIos_token() {
            return ios_token;
        }

        public void setIos_token(Object ios_token) {
            this.ios_token = ios_token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
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
    }
}
