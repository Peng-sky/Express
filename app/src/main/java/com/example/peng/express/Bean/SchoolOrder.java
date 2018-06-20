package com.example.peng.express.Bean;

import java.io.Serializable;
import java.util.List;

public class SchoolOrder {

    public List<Body> bodyList;

    public static class Body implements Serializable{

        private int id;
        private String username;
        private String phone_number;
        private String express_company;
        private String track_number;
        private String address;
        private String state;

        public Body(){}

        public Body( String username, String phone, String express_company, String track_number, String address, String state) {

            this.username = username;
            this.phone_number = phone;
            this.express_company = express_company;
            this.track_number = track_number;
            this.address = address;
            this.state = state;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getExpress_company() {
            return express_company;
        }

        public void setExpress_company(String express_company) {
            this.express_company = express_company;
        }

        public String getTrack_number() {
            return track_number;
        }

        public void setTrack_number(String track_number) {
            this.track_number = track_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }


}