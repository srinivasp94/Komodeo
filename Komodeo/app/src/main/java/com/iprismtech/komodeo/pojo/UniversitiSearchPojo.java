package com.iprismtech.komodeo.pojo;

import java.util.List;

public class UniversitiSearchPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","title":"Osmania University","location":"Hyderabad","icon":"storage/220px-Osmania_University_Logo.png","delete_status":"1","created_on":"2019-02-15 02:10:03","modified_on":null}]
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
         * title : Osmania University
         * location : Hyderabad
         * icon : storage/220px-Osmania_University_Logo.png
         * delete_status : 1
         * created_on : 2019-02-15 02:10:03
         * modified_on : null
         */

        private String id;
        private String title;
        private String location;
        private String icon;
        private String delete_status;
        private String created_on;
        private Object modified_on;

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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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
