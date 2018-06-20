package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.peng.express.R;
import com.igexin.sdk.IPushCore;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class SchoolCourierActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_courier);
        sendTransmissionTemplate();
    }

    public void sendTransmissionTemplate(){
        OkHttpUtils
                .get()
                .url(IP+"sendNewOrder")
                .build();
    }
}
