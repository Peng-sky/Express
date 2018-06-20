package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;
import static com.example.peng.express.Activity.WriteUserInfoActivity.JSON;

public class VipActivity extends AppCompatActivity{
    private EditText track_number,express_company,phone,address,sms,remark;
    private Button commit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrderToService(getData());
            }
        });
    }
    private void sendOrderToService(final String json) {
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
                        Toast.makeText(VipActivity.this,"订单创建成功",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        track_number = findViewById(R.id.track_number);
        express_company = findViewById(R.id.express_company);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        sms = findViewById(R.id.sms);
        commit = findViewById(R.id.commit);
        remark = findViewById(R.id.remark);
    }

    private String getData(){
        String number = track_number.getText().toString();
        String company = express_company.getText().toString();
        String phones = phone.getText().toString();
        String smss = sms.getText().toString();
        String commits = commit.getText().toString();
        String remarks = remark.getText().toString();
        SchoolOrder.Body so = new SchoolOrder.Body(number,company,phones,smss,commits,remarks);
        Gson gson = new Gson();
        String json = gson.toJson(so);
        return json;
    }
}