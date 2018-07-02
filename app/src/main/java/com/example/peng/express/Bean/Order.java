package com.example.peng.express.Bean;

public class Order {
    private String user_phone;
    private String tracking_number;
    private String sender;
    private String sender_address;
    private String sender_phone;
    private String addressee;
    private String direction_phone;
    private String add_address;
    private String product_type;
    private String product_weight;
    private String collect_price;
    private String product_amount;

    public Order(String tracking_number, String sender, String sender_phone, String sender_address, String addressee, String direction_phone, String add_address, String product_type, String product_weight, String collect_price, String product_amount) {
        this.tracking_number = tracking_number;
        this.sender = sender;
        this.sender_phone = sender_phone;
        this.sender_address = sender_address;
        this.addressee = addressee;
        this.direction_phone = direction_phone;
        this.add_address = add_address;
        this.product_type = product_type;
        this.product_weight = product_weight;
        this.collect_price = collect_price;
        this.product_amount = product_amount;
    }

    public Order() {
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender_address() {
        return sender_address;
    }

    public void setSender_address(String sender_address) {
        this.sender_address = sender_address;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getDirection_phone() {
        return direction_phone;
    }

    public void setDirection_phone(String direction_phone) {
        this.direction_phone = direction_phone;
    }

    public String getAdd_address() {
        return add_address;
    }

    public void setAdd_address(String add_address) {
        this.add_address = add_address;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(String product_weight) {
        this.product_weight = product_weight;
    }

    public String getCollect_price() {
        return collect_price;
    }

    public void setCollect_price(String collect_price) {
        this.collect_price = collect_price;
    }

    public String getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(String product_amount) {
        this.product_amount = product_amount;
    }
}