package com.example.peng.express.Activity;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

public class DemoApplication extends Application {
    private static final String TAG = "GetuiSdkDemo";

    public static StringBuilder payloadData = new StringBuilder();
    private static DemoHandler handler;
    public static MainActivity demoActivity;



    public static class DemoHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (demoActivity != null) {
                        payloadData.append((String) msg.obj);
                        payloadData.append("\n");
                    }
                    break;

                case 1:
                    if (demoActivity != null) {

                    }
                    break;
            }
        }
    }
}
