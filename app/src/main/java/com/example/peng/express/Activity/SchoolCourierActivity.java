package com.example.peng.express.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.Bean.User;
import com.example.peng.express.Fragment.AllOrderFragment;
import com.example.peng.express.Fragment.MyOrderFragment;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igexin.sdk.PushManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class SchoolCourierActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView myOrder,allOrder;
    private Fragment f1,f2;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private static final String TAG = "GTApplication";

    private static DemoHandler handler;
    private User user;

    /**PushManager
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView == null)
     */
    public static StringBuilder payloadData = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "DemoApplication onCreate");
        PushManager.getInstance().initialize(getApplicationContext(),com.example.peng.express.Service.DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), com.example.peng.express.Service.DemoIntentService.class);

        if (handler == null) {
            handler = new DemoHandler();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_courier);
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        myOrder = findViewById(R.id.myOrder);
        myOrder.setOnClickListener(this);
        allOrder = findViewById(R.id.allOrder);
        allOrder.setOnClickListener(this);

        f1 = new AllOrderFragment();
        transaction.replace(R.id.fragment,f1);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        manager =getSupportFragmentManager();
        transaction = manager.beginTransaction();

        myOrder.setTextColor(getResources().getColor(R.color.sc_text));
        allOrder.setTextColor(getResources().getColor(R.color.sc_text));

        switch (v.getId()){
            case R.id.allOrder:
                allOrder.setTextColor(getResources().getColor(R.color.title));
                /**
                 * 为了防止重叠，需要点击之前先移除其他Fragment
                 */
                hideFragment(transaction);
                f1 = new AllOrderFragment();
                transaction.replace(R.id.fragment, f1);
                transaction.commit();
                break;
            case R.id.myOrder:
                myOrder.setTextColor(getResources().getColor(R.color.title));
                /**
                 * 为了防止重叠，需要点击之前先移除其他Fragment
                 */
                hideFragment(transaction);
                f2 = new MyOrderFragment();
                transaction.replace(R.id.fragment, f2);
                transaction.commit();
                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
            transaction.remove(f1);
        }
        if (f2 != null) {
            //transaction.hide(f2);
            transaction.remove(f2);
        }
    }

    public static List<String> getData(String data){

        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(data);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
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
                    List<String> list = getData(String.valueOf(payloadData));
                    Log.d(TAG, "handleMessage: payload = "+payloadData);
//                    Gson gson = new Gson();
//
//                    List<SchoolOrder.Body> bodyList = gson.fromJson(String.valueOf(payloadData),new TypeToken<List<SchoolOrder.Body>>(){}.getType());
////                    SchoolOrder.Body body = gson.fromJson(String.valueOf(payloadData),new TypeToken<SchoolOrder.Body>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        Gson gson = new Gson();
                        SchoolOrder.Body body= gson.fromJson(list.get(i),new TypeToken<SchoolOrder.Body>(){}.getType());
                        openNotification(body.getAddress(),body,i);
                    }
                    break;
                case 1:
                    String cid = (String) msg.obj;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String phone = sharedPreferences.getString("phone",null);
                    Log.i(TAG, "handleMessage: "+msg.obj+"手机号为"+phone);
                    updateUserCid(cid,phone);
                    break;
            }
        }
    }

    public static void updateUserCid(final String cid, final String phone) {
        OkHttpUtils.get()
                .url(IP+"updateUserCid")
                .addParams("cid",cid)
                .addParams("phone",phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String msg = jsonObject.optString("msg");
                            if (msg.equals("1")) {
                                Log.i(TAG, "onResponse: 修改cid成功"+phone+"对应的cid为"+cid);
                            } else {
                                Log.i(TAG, "onResponse: 修改cid失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void openNotification(String message,SchoolOrder.Body body,int id){
        Intent resultIntent = new Intent(SchoolCourierActivity.this,AcceptScOrderActivity.class);
        resultIntent.putExtra("body", body);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("您有一条新订单")
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setAutoCancel(true)
                .setFullScreenIntent(resultPendingIntent,true);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(id,mBuilder.build());
    }
}
