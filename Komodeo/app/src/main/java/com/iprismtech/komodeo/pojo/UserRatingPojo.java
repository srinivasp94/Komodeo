package com.iprismtech.komodeo.pojo;

import java.util.List;

public class UserRatingPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","mobile":"9959840085","password":"e10adc3949ba59abbe56e057f20f883e","profile_pic":"storage/profile_pics/5c975488004bd.png","major":"Financehdhshshs","university_id":"1","default_lat":"17.5654","default_lng":"78.65464","default_address":"madhapur,hyderabad","registered_through":"normal","bio":"I m very bad","payment_preference":"Paypal","comments":"no","likes":"yes","friend_requests":"yes","events":"yes","event_cancel":"no","token":"asdfsdfg5df65fhgh6kldfdf6552df56fgjiofgf5gfg96f5gfgjfgofkgoffdsfsf748r","ios_token":"","status":"1","delete_status":"1","created_on":"2019-02-15 12:29:08","modified_on":"2019-03-24 15:27:28","ratings":"3.2","credentials":[{"id":"1","user_id":"1","class_id":"1","image":"storage/5c8a076fd2079.png","created_on":"2019-03-14 13:19:03"},{"id":"2","user_id":"1","class_id":"1","image":"storage/5c8a076fd21a3.png","created_on":"2019-03-14 13:19:03"}],"reviews":[{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"1","given_by":"1","user_id":"1","ratings":"5","reviews":"Lorem ipsum dolor sit amet","created_on":"2019-03-14 12:34:32"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"2","given_by":"1","user_id":"1","ratings":"4","reviews":"gdfgdgdg","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"3","given_by":"1","user_id":"1","ratings":"3","reviews":"gdgd","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"4","given_by":"2","user_id":"1","ratings":"3","reviews":"dgsdg","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"5","given_by":"2","user_id":"1","ratings":"1","reviews":"Lorem ipsum dolor sit amet","created_on":"2019-03-14 15:38:21"}],"rating_details":[{"rate1":"1","rate2":"0","rate3":"2","rate4":"1","rate5":"1","rate1_ps":"20.0000","rate2_ps":"0.0000","rate3_ps":"40.0000","rate4_ps":"20.0000","rate5_ps":"20.0000"}]}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

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

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * first_name : Khadeerte
         * last_name : Mohammed12
         * email_id : khadeer.md@iprismtech.com
         * mobile : 9959840085
         * password : e10adc3949ba59abbe56e057f20f883e
         * profile_pic : storage/profile_pics/5c975488004bd.png
         * major : Financehdhshshs
         * university_id : 1
         * default_lat : 17.5654
         * default_lng : 78.65464
         * default_address : madhapur,hyderabad
         * registered_through : normal
         * bio : I m very bad
         * payment_preference : Paypal
         * comments : no
         * likes : yes
         * friend_requests : yes
         * events : yes
         * event_cancel : no
         * token : asdfsdfg5df65fhgh6kldfdf6552df56fgjiofgf5gfg96f5gfgjfgofkgoffdsfsf748r
         * ios_token :
         * status : 1
         * delete_status : 1
         * created_on : 2019-02-15 12:29:08
         * modified_on : 2019-03-24 15:27:28
         * ratings : 3.2
         * credentials : [{"id":"1","user_id":"1","class_id":"1","image":"storage/5c8a076fd2079.png","created_on":"2019-03-14 13:19:03"},{"id":"2","user_id":"1","class_id":"1","image":"storage/5c8a076fd21a3.png","created_on":"2019-03-14 13:19:03"}]
         * reviews : [{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"1","given_by":"1","user_id":"1","ratings":"5","reviews":"Lorem ipsum dolor sit amet","created_on":"2019-03-14 12:34:32"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"2","given_by":"1","user_id":"1","ratings":"4","reviews":"gdfgdgdg","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"3","given_by":"1","user_id":"1","ratings":"3","reviews":"gdgd","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"4","given_by":"2","user_id":"1","ratings":"3","reviews":"dgsdg","created_on":"2019-03-14 00:00:00"},{"first_name":"Khadeerte","last_name":"Mohammed12","email_id":"khadeer.md@iprismtech.com","major":"Financehdhshshs","profile_pic":"storage/profile_pics/5c975488004bd.png","id":"5","given_by":"2","user_id":"1","ratings":"1","reviews":"Lorem ipsum dolor sit amet","created_on":"2019-03-14 15:38:21"}]
         * rating_details : [{"rate1":"1","rate2":"0","rate3":"2","rate4":"1","rate5":"1","rate1_ps":"20.0000","rate2_ps":"0.0000","rate3_ps":"40.0000","rate4_ps":"20.0000","rate5_ps":"20.0000"}]
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
        private String token;
        private String ios_token;
        private String status;
        private String delete_status;
        private String created_on;
        private String modified_on;
        private String ratings;
        private List<CredentialsBean> credentials;
        private List<ReviewsBean> reviews;
        private List<RatingDetailsBean> rating_details;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIos_token() {
            return ios_token;
        }

        public void setIos_token(String ios_token) {
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

        public String getRatings() {
            return ratings;
        }

        public void setRatings(String ratings) {
            this.ratings = ratings;
        }

        public List<CredentialsBean> getCredentials() {
            return credentials;
        }

        public void setCredentials(List<CredentialsBean> credentials) {
            this.credentials = credentials;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public List<RatingDetailsBean> getRating_details() {
            return rating_details;
        }

        public void setRating_details(List<RatingDetailsBean> rating_details) {
            this.rating_details = rating_details;
        }

        public static class CredentialsBean {
            /**
             * id : 1
             * user_id : 1
             * class_id : 1
             * image : storage/5c8a076fd2079.png
             * created_on : 2019-03-14 13:19:03
             */

            private String id;
            private String user_id;
            private String class_id;
            private String image;
            private String created_on;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }
        }

        public static class ReviewsBean {
            /**
             * first_name : Khadeerte
             * last_name : Mohammed12
             * email_id : khadeer.md@iprismtech.com
             * major : Financehdhshshs
             * profile_pic : storage/profile_pics/5c975488004bd.png
             * id : 1
             * given_by : 1
             * user_id : 1
             * ratings : 5
             * reviews : Lorem ipsum dolor sit amet
             * created_on : 2019-03-14 12:34:32
             */

            private String first_name;
            private String last_name;
            private String email_id;
            private String major;
            private String profile_pic;
            private String id;
            private String given_by;
            private String user_id;
            private String ratings;
            private String reviews;
            private String created_on;

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

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGiven_by() {
                return given_by;
            }

            public void setGiven_by(String given_by) {
                this.given_by = given_by;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getRatings() {
                return ratings;
            }

            public void setRatings(String ratings) {
                this.ratings = ratings;
            }

            public String getReviews() {
                return reviews;
            }

            public void setReviews(String reviews) {
                this.reviews = reviews;
            }

            public String getCreated_on() {
                return created_on;
            }

            public void setCreated_on(String created_on) {
                this.created_on = created_on;
            }
        }

        public static class RatingDetailsBean {
            /**
             * rate1 : 1
             * rate2 : 0
             * rate3 : 2
             * rate4 : 1
             * rate5 : 1
             * rate1_ps : 20.0000
             * rate2_ps : 0.0000
             * rate3_ps : 40.0000
             * rate4_ps : 20.0000
             * rate5_ps : 20.0000
             */

            private String rate1;
            private String rate2;
            private String rate3;
            private String rate4;
            private String rate5;
            private String rate1_ps;
            private String rate2_ps;
            private String rate3_ps;
            private String rate4_ps;
            private String rate5_ps;

            public String getRate1() {
                return rate1;
            }

            public void setRate1(String rate1) {
                this.rate1 = rate1;
            }

            public String getRate2() {
                return rate2;
            }

            public void setRate2(String rate2) {
                this.rate2 = rate2;
            }

            public String getRate3() {
                return rate3;
            }

            public void setRate3(String rate3) {
                this.rate3 = rate3;
            }

            public String getRate4() {
                return rate4;
            }

            public void setRate4(String rate4) {
                this.rate4 = rate4;
            }

            public String getRate5() {
                return rate5;
            }

            public void setRate5(String rate5) {
                this.rate5 = rate5;
            }

            public String getRate1_ps() {
                return rate1_ps;
            }

            public void setRate1_ps(String rate1_ps) {
                this.rate1_ps = rate1_ps;
            }

            public String getRate2_ps() {
                return rate2_ps;
            }

            public void setRate2_ps(String rate2_ps) {
                this.rate2_ps = rate2_ps;
            }

            public String getRate3_ps() {
                return rate3_ps;
            }

            public void setRate3_ps(String rate3_ps) {
                this.rate3_ps = rate3_ps;
            }

            public String getRate4_ps() {
                return rate4_ps;
            }

            public void setRate4_ps(String rate4_ps) {
                this.rate4_ps = rate4_ps;
            }

            public String getRate5_ps() {
                return rate5_ps;
            }

            public void setRate5_ps(String rate5_ps) {
                this.rate5_ps = rate5_ps;
            }
        }
    }
}
