package com.example.peng.express.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;
import static com.example.peng.express.Activity.WriteUserInfoActivity.JSON;

public class ScOrderDetialActivity extends AppCompatActivity {
    private TextView username,phone,express_company,track_number,take_add,address,reach_time,title;
    private ImageView back;
    private Button add_to_my_order;
    private List<SchoolOrder.Body> bodyList;
    private String sc_phone,track;
    private ProgressDialog dlg;
    private String details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_order_details);
        Intent intent = getIntent();
        Gson gson = new Gson();
        details = intent.getStringExtra("details");
        bodyList = gson.fromJson(details,new TypeToken<List<SchoolOrder.Body>>(){}.getType());
        initView();

        username.setText(bodyList.get(0).getUsername());
        phone.setText(bodyList.get(0).getPhone_number());
        express_company.setText(bodyList.get(0).getExpress_company());
        track_number.setText(bodyList.get(0).getTrack_number());
        take_add.setText(bodyList.get(0).getTake_add());
        address.setText(bodyList.get(0).getAddress());
        reach_time.setText(bodyList.get(0).getState());
    }

    private void initView() {
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        express_company = findViewById(R.id.express_company);
        track_number = findViewById(R.id.track_number);
        take_add = findViewById(R.id.take_add);
        address = findViewById(R.id.address);
        reach_time = findViewById(R.id.reach_time);
        add_to_my_order = findViewById(R.id.add_to_my_order);
        title = findViewById(R.id.title);
        title.setText("取件详情");
        add_to_my_order.setText("确认");
        back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        add_to_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrderToService(details);
            }
        });
    }

    private void sendOrderToService(final String json) {
        dlg = new ProgressDialog(ScOrderDetialActivity.this);
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
                .url(IP+"insetSC")
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
                        Looper.prepare();
                        Toast.makeText(ScOrderDetialActivity.this,"订单创建成功",Toast.LENGTH_LONG).show();
                        dlg.dismiss();
                        Intent intent = new Intent(ScOrderDetialActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ScOrderDetialActivity.this, "订单创建失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
