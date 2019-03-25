package com.iprismtech.komodeo.pojo;

import java.util.List;

public class NotificationsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"5","title":"fgfdg","description":"dfgdfg","sent_to":"user","created_on":"2019-03-22 18:31:08"},{"id":"4","title":"Parking","description":"dfggdfg","sent_to":"user","created_on":"2018-12-24 11:32:24"},{"id":"3","title":"Parking","description":"dfggdfg","sent_to":"user","created_on":"2018-12-24 11:18:33"},{"id":"1","title":"Lorem ipsum dolor sit amet","description":"consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","sent_to":"user","created_on":"2018-10-29 01:05:08"}]
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
         * id : 5
         * title : fgfdg
         * description : dfgdfg
         * sent_to : user
         * created_on : 2019-03-22 18:31:08
         */

        private String id;
        private String title;
        private String description;
        private String sent_to;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSent_to() {
            return sent_to;
        }

        public void setSent_to(String sent_to) {
            this.sent_to = sent_to;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
