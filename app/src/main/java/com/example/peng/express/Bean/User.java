package com.example.peng.express.Bean;

public class User{
    private int id;
    private String username;
    private String phone_number;
    private String password;
    private String sex;
    private String birthday;
    private String email;
    private String addressPUA;
    private String address_details;

    public User(String phone, String username, String password, String sex, String birthday, String email, String province, String address) {
        this.phone_number = phone;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.addressPUA = province;
        this.address_details = address;
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
}
