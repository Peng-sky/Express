package com.example.peng.express.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Interface.HttpCallbackListener;
import com.example.peng.express.R;
import com.example.peng.express.Util.HttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_forget_password,tv_register;
    private EditText et_phone_number,et_password;
    private Button btn_login;
    private String  phone= "";
    private String  password= "";
    public static final String IP = "http://192.168.1.244:8080/servlet/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_login.setOnClickListener(this);
    }

    private void initView() {
        et_phone_number = findViewById(R.id.et_phone_number);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        tv_register = findViewById(R.id.tv_register);

        tv_forget_password.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

//    public void login(){
//        if (!isInputValid()){
//            return;
//        }
//        //构造HashMap
//        HashMap<String,String> params = new HashMap<String,String>();
//        params.put(User.PASSWORD,et_password.getText().toString());
//        params.put(User.USERNAME,et_username.getText().toString());
//        try {
//            //构造完整URL
//            String completedURL = HttpUtil.getURLWithParams(originAddress,params);
//            System.out.println(completedURL);
////            username = et_username.getText().toString();
////            password = et_password.getText().toString();
////            String completedURL = "request_flag=login&username="+username+"&password="+password;
//            //发送请求
//            HttpUtil.sendHttpRequest(completedURL, new HttpCallbackListener() {
//
//                @Override
//                public void onFinish(String response) {
//                    Message message = new Message();
//                    message.obj = response;
//                    handler.sendMessage(message);
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Message message = new Message();
//                    message.obj = e.toString();
//                    handler.sendMessage(message);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void login(){
        phone = et_phone_number.getText().toString();
        password = et_password.getText().toString();
        if(phone.equals("")||password.equals("")){
            Toast.makeText(LoginActivity.this, "用户名或密码为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        //用于处理消息的handler
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String name = msg.obj.toString();
                try {
                    JSONObject jsonObject = new JSONObject(name);
                    String n = jsonObject.optString("phone_number");
                    if (phone.equals(n)){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //请求的参数
        final String params="UserManager?phone_number="+phone+"&password="+password;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String completeURL = IP+params;
                System.out.println(completeURL);
                //得到json字符串
                HttpUtil.sendHttpRequest(completeURL, new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.obj = response;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        Message message = new Message();
                        message.obj = e.toString();
                        handler.sendMessage(message);
                    }
                });
            }
        }).start();
    }

    private boolean isInputValid() {
        //检查用户输入的合法性，这里暂且默认用户输入合法
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
}
