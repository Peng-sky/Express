package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.peng.express.Bean.User;
import com.example.peng.express.R;
import com.example.peng.express.Util.JsonUtil2;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class WriteUserInfoActivity extends AppCompatActivity {
    private EditText et_info_phone_num, et_info_username, et_info_birthday, et_info_email, et_info_province, et_info_address;
    private Button btn_info_confirm;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Intent intent;
    private User user;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_user_info);
        initView();
        btn_info_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = getUserInfo();
                try {
                    postJson(info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        //设置电话号码不可输入
        et_info_phone_num = findViewById(R.id.et_info_phone_num);
        et_info_phone_num.setFocusable(false);
        et_info_phone_num.clearFocus();
        //用户名
        et_info_username = findViewById(R.id.et_info_username);
        //生日
        et_info_birthday = findViewById(R.id.et_info_birthday);
        et_info_email = findViewById(R.id.et_info_email);
        et_info_province = findViewById(R.id.et_info_province);
        et_info_address = findViewById(R.id.et_info_address);
        btn_info_confirm = findViewById(R.id.btn_info_confirm);
        radioGroup = findViewById(R.id.rg);
        intent = getIntent();
        et_info_phone_num.setText(intent.getStringExtra("phone"));
    }

    private String getUserInfo() {
        intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String password = intent.getStringExtra("password");
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonId);
        String username = et_info_username.getText().toString();
        String birthday = et_info_birthday.getText().toString();
        String email = et_info_email.getText().toString();
        String province = et_info_province.getText().toString();
        String address = et_info_address.getText().toString();
        String sex = radioButton.getText().toString();
        String user_type = "0";
        Gson gson = new Gson();
        User user = new User(phone, username, password, sex, birthday, email, province, address,user_type);
        String info = gson.toJson(user);
        System.out.println(info);
        return info;
    }

    private void postJson(final String info) throws IOException {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String name = msg.obj.toString();
                try {
                    JSONObject jsonObject = new JSONObject(name);
                    String n = jsonObject.optString("msg");
                    if ("1".equals(n)) {
                        Intent intent = new Intent(WriteUserInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(WriteUserInfoActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WriteUserInfoActivity.this, "注册失败，手机号已存在", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread() {
            @Override
            public void run() {
                super.run();
                //申明给服务端传递一个json串
                //创建一个OkHttpClient对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
                RequestBody requestBody = RequestBody.create(JSON, info);
                //创建一个请求对象
                Request request = new Request.Builder()
                        .url(IP+"/Register")
                        .post(requestBody)
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Message message = new Message();
                        message.obj = response.body().string();
                        System.out.println(message.obj);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
