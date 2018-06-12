package com.example.peng.express.Util;

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
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.peng.express.Activity.MainActivity;
import com.example.peng.express.R;
import com.igexin.sdk.PushManager;

public class GTApplication extends Application {

    private static final String TAG = "GTApplication";

    private static DemoHandler handler;

    /**PushManager
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView == null)
     */
    public static StringBuilder payloadData = new StringBuilder();

    @Override
    public void onCreate() {
        Log.d(TAG, "DemoApplication onCreate");

        PushManager.getInstance().initialize(getApplicationContext(),com.example.peng.express.Service.DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), com.example.peng.express.Service.DemoIntentService.class);

        if (handler == null) {
            handler = new DemoHandler();
        }
        super.onCreate();
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public class DemoHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    payloadData.append( msg.obj);
                    Log.d(TAG, "handleMessage: payload = "+payloadData);
                    openNotification(String.valueOf(payloadData));
                    break;
                case 1:
                    break;
            }
        }
    }

    public void openNotification(String message){
        Intent resultIntent = new Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("您有一条新订单")
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setFullScreenIntent(resultPendingIntent,true);
        mBuilder.setContentIntent(resultPendingIntent);

        int Id = 001;

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(Id,mBuilder.build());
    }
}
