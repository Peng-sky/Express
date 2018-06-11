package com.example.peng.express.Util;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.peng.express.Service.DemoIntentService;
import com.example.peng.express.Service.DemoPushService;
import com.igexin.sdk.PushManager;

public class GTApplication extends Application {

    private static final String TAG = "GetuiSdkDemo";

    private static DemoHandler handler;

    /**
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView == null)
     */
    public static StringBuilder payloadData = new StringBuilder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "DemoApplication onCreate");

        PushManager.getInstance().initialize(getApplicationContext(),com.example.peng.express.Service.DemoPushService.class);
        PushManager.getInstance().initialize(getApplicationContext(), com.example.peng.express.Service.DemoIntentService.class);

        if (handler == null) {
            handler = new DemoHandler();
        }
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public static class DemoHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    payloadData.append( msg.obj);
                    System.out.println(payloadData);
                    break;
                case 1:
                    break;
                case 2:
                    payloadData.append(msg.obj);
                    System.out.println(payloadData);
                    break;
            }
        }
    }
}
