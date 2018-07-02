package com.example.peng.express.Bean;

import java.util.List;

public class JsonAna {

    public List<SchoolOrder.Body> schoolOrderList;

    public List<Order> orderList;

    public List<SchoolOrder.Body> getBodyList() {
        return schoolOrderList;
    }

    public void setBodyList(List<SchoolOrder.Body> bodyList) {
        this.schoolOrderList = bodyList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
