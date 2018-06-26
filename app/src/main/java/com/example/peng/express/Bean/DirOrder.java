package com.example.peng.express.Bean;

import java.util.List;

public class DirOrder {

    public List<Body> bodyList;

    public static class Body {

        private String track_number;
        private String sender_name;
        private String sender_phone;
        private String sender_address;
        private String dir_name;
        private String dir_phone;
        private String dir_address;
        private String send_time;
        private String remarks;
        private String state;

        public Body(String track_number, String sender_name, String sender_phone, String sender_address, String dir_name, String dir_phone, String dir_address, String remarks, String state) {
            this.track_number = track_number;
            this.sender_name = sender_name;
            this.sender_phone = sender_phone;
            this.sender_address = sender_address;
            this.dir_name = dir_name;
            this.dir_phone = dir_phone;
            this.dir_address = dir_address;
            this.remarks = remarks;
            this.state = state;

        }

        public String getTrack_number() {
            return track_number;
        }

        public void setTrack_number(String track_number) {
            this.track_number = track_number;
        }

        public String getSender_name() {
            return sender_name;
        }

        public void setSender_name(String sender_name) {
            this.sender_name = sender_name;
        }

        public String getSender_phone() {
            return sender_phone;
        }

        public void setSender_phone(String sender_phone) {
            this.sender_phone = sender_phone;
        }

        public String getSender_address() {
            return sender_address;
        }

        public void setSender_address(String sender_address) {
            this.sender_address = sender_address;
        }

        public String getDir_name() {
            return dir_name;
        }

        public void setDir_name(String dir_name) {
            this.dir_name = dir_name;
        }

        public String getDir_phone() {
            return dir_phone;
        }

        public void setDir_phone(String dir_phone) {
            this.dir_phone = dir_phone;
        }

        public String getDir_address() {
            return dir_address;
        }

        public void setDir_address(String dir_address) {
            this.dir_address = dir_address;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
