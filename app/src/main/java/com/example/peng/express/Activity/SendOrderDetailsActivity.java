package com.example.peng.express.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class SendOrderDetailsActivity extends AppCompatActivity {
    private TextView username,phone,express_company,track_number,address,reach_time;
    private Button add_to_my_order;
    private SchoolOrder.Body body;
    private String sc_phone,track;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_order_details);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        sc_phone = sharedPreferences.getString("phone","Null");
        System.out.println(sc_phone);
        Intent intent = getIntent();
        body = (SchoolOrder.Body) intent.getSerializableExtra("body");
        username.setText(body.getUsername());
        phone.setText(body.getPhone_number());
        express_company.setText(body.getExpress_company());
        track_number.setText(body.getTrack_number());
        address.setText(body.getAddress());
        reach_time.setText(body.getState());
        add_to_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get()
                        .url(IP+"toMyList?")
                        .addParams("sc_phone",sc_phone)
                        .addParams("track_number",track_number.getText().toString())
                        .build()
                        .execute(new StringCallback() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(String response, int id) {

                            }
                        });
                Toast.makeText(SendOrderDetailsActivity.this, "订单添加成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SendOrderDetailsActivity.this,SchoolCourierActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        express_company = findViewById(R.id.express_company);
        track_number = findViewById(R.id.track_number);
        address = findViewById(R.id.address);
        reach_time = findViewById(R.id.reach_time);
        add_to_my_order = findViewById(R.id.add_to_my_order);
    }

}
