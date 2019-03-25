package com.iprismtech.komodeo.pojo;

import java.util.List;

public class SearchCommunityPojo {


    /**
     * status : true
     * message : Data fetched successfully!
     * community : [{"id":"3","first_name":"Khadeer","last_name":"Mohammed","email_id":"smith@gmail.com","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","profile_pic":"storage/profile_pics/5c98791d4ad2f.","major":"Finance","university_id":"1","default_lat":"65465","default_lng":"78.65464","default_address":"madhapur hyderabad","registered_through":"normal","bio":"helloooooo","payment_preference":"Paypal","comments":"yes","likes":"no","friend_requests":"no","events":"no","event_cancel":"no","token":null,"ios_token":null,"status":"1","delete_status":"1","created_on":"2019-03-15 00:00:00","modified_on":"2019-03-25 12:15:49","friend":"yes","friend_request_sent":"no","friend_request_ignored":"no"}]
     */

    private boolean status;
    private String message;
    private List<CommunityBean> community;

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

    public List<CommunityBean> getCommunity() {
        return community;
    }

    public void setCommunity(List<CommunityBean> community) {
        this.community = community;
    }

    public static class CommunityBean {
        /**
         * id : 3
         * first_name : Khadeer
         * last_name : Mohammed
         * email_id : smith@gmail.com
         * mobile : 9999999999
         * password : e10adc3949ba59abbe56e057f20f883e
         * profile_pic : storage/profile_pics/5c98791d4ad2f.
         * major : Finance
         * university_id : 1
         * default_lat : 65465
         * default_lng : 78.65464
         * default_address : madhapur hyderabad
         * registered_through : normal
         * bio : helloooooo
         * payment_preference : Paypal
         * comments : yes
         * likes : no
         * friend_requests : no
         * events : no
         * event_cancel : no
         * token : null
         * ios_token : null
         * status : 1
         * delete_status : 1
         * created_on : 2019-03-15 00:00:00
         * modified_on : 2019-03-25 12:15:49
         * friend : yes
         * friend_request_sent : no
         * friend_request_ignored : no
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
        private String default_lat;
        private String default_lng;
        private String default_address;
        private String registered_through;
        private String bio;
        private String payment_preference;
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
        private String modified_on;
        private String friend;
        private String friend_request_sent;
        private String friend_request_ignored;

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

        public String getDefault_lat() {
            return default_lat;
        }

        public void setDefault_lat(String default_lat) {
            this.default_lat = default_lat;
        }

        public String getDefault_lng() {
            return default_lng;
        }

        public void setDefault_lng(String default_lng) {
            this.default_lng = default_lng;
        }

        public String getDefault_address() {
            return default_address;
        }

        public void setDefault_address(String default_address) {
            this.default_address = default_address;
        }

        public String getRegistered_through() {
            return registered_through;
        }

        public void setRegistered_through(String registered_through) {
            this.registered_through = registered_through;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getPayment_preference() {
            return payment_preference;
        }

        public void setPayment_preference(String payment_preference) {
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

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getFriend() {
            return friend;
        }

        public void setFriend(String friend) {
            this.friend = friend;
        }

        public String getFriend_request_sent() {
            return friend_request_sent;
        }

        public void setFriend_request_sent(String friend_request_sent) {
            this.friend_request_sent = friend_request_sent;
        }

        public String getFriend_request_ignored() {
            return friend_request_ignored;
        }

        public void setFriend_request_ignored(String friend_request_ignored) {
            this.friend_request_ignored = friend_request_ignored;
        }
    }
}
