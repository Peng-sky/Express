package com.example.peng.express.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.peng.express.Activity.MainActivity;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igexin.sdk.PushManager;

import java.io.Serializable;

public class CheckSchoolCourierActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "DemoApplication onCreate");
//
//        PushManager.getInstance().initialize(getApplicationContext(),com.example.peng.express.Service.DemoPushService.class);
//        PushManager.getInstance().registerPushIntentService(getApplicationContext(), com.example.peng.express.Service.DemoIntentService.class);
//
//        if (handler == null) {
//            handler = new DemoHandler();
//        }
//        super.onCreate();
//    }







}
