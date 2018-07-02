package com.example.peng.express.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.Order;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class SchoolSendDetialsActivity extends AppCompatActivity {
    private TextView track_number,username,phone,address,dir_name,dir_phone,dir_address;
    private Button add_to_my_order;
    private ImageView back;
    private ProgressDialog dlg;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order_details);
        Intent intent = new Intent();
        intent = getIntent();
        String json = intent.getStringExtra("school_details");

        Gson gson = new Gson();
        Order order = gson.fromJson(json,new TypeToken<Order>(){}.getType());

        initView();
        setTxt(order);
    }

    private void setTxt(Order order) {
        track_number.setText(order.getTracking_number());
        username.setText(order.getSender());
        phone.setText(order.getSender_phone());
        address.setText(order.getSender_address());
        dir_name.setText(order.getAddressee());
        dir_phone.setText(order.getDirection_phone());
        dir_address.setText(order.getAdd_address());
        track_number.setText(order.getTracking_number());

    }

    private void initView() {
        track_number = findViewById(R.id.track_number);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        dir_name = findViewById(R.id.dir_phone);
        dir_phone = findViewById(R.id.dir_phone);
        dir_address = findViewById(R.id.dir_address);
        add_to_my_order = findViewById(R.id.add_to_my_order);
        add_to_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=intent = getIntent();
                String json = intent.getStringExtra("school_details");
                sendOrderToService(json);
                finish();
            }
        });
        back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
    }

    private void sendOrderToService(final String json) {
        dlg = new ProgressDialog(this);
        dlg.setIcon(R.mipmap.logo);
        dlg.setMessage("加载中请稍后");
        dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置水平进度条
        dlg.show();
        RequestBody body = RequestBody.create(JSON,json);
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(IP+"Order")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String result = jsonObject.optString("msg");
                    if (result.equals("1")){
                        //Toast 必须在主线程中进行
                        //Toast 显示需要出现在一个线程的消息队列中.... 很隐蔽
                        //因为toast的实现需要在activity的主线程才能正常工作，
                        // 所以传统的非主线程不能使toast显示在actvity上，通过Handler可以使自定义线程运行于Ui主线程
                        Looper.prepare();
                        Toast.makeText(SchoolSendDetialsActivity.this,"订单创建成功",Toast.LENGTH_LONG).show();
                        dlg.dismiss();
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
