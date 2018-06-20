package com.example.peng.express.Bean;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String phone_number;
    private String password;
    private String sex;
    private String birthday;
    private String email;
    private String addressPUA;
    private String address_details;
    private String user_type;

    public User(String phone, String username, String password, String sex, String birthday, String email, String province, String address,String user_type) {
        this.phone_number = phone;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.addressPUA = province;
        this.address_details = address;
        this.user_type = user_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressPUA() {
        return addressPUA;
    }

    public void setAddressPUA(String addressPUA) {
        this.addressPUA = addressPUA;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

}
