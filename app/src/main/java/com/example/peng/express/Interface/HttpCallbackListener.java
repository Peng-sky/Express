package com.example.peng.express.Interface;

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
