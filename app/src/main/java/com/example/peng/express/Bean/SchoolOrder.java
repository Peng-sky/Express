package com.example.peng.express.Bean;

public class SchoolOrder {
    private String track_number;
    private String express_company;
    private String phone;
    private String address;
    private String sms;
    private String remark;

    public SchoolOrder(){}

    public SchoolOrder(String number, String express_company, String phones, String smss, String commits, String remarks) {
        this.track_number = number;
        this.express_company = express_company;
        this.phone = phones;
        this.address = smss;
        this.sms = commits;
        this.remark = remarks;
    }


    public String getTrack_number() {
        return track_number;
    }

    public void setTrack_number(String track_number) {
        this.track_number = track_number;
    }

    public String getExpress_company() {
        return express_company;
    }

    public void setExpress_company(String express_company) {
        this.express_company = express_company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
