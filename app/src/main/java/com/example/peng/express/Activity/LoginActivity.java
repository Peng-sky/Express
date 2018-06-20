package com.example.peng.express.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.User;
import com.example.peng.express.Interface.HttpCallbackListener;
import com.example.peng.express.R;
import com.example.peng.express.Service.DemoIntentService;
import com.example.peng.express.Service.DemoPushService;
import com.example.peng.express.Util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igexin.sdk.PushManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().initialize(getApplicationContext(), DemoIntentService.class);

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

    public void login() {
        phone = et_phone_number.getText().toString();
        password = et_password.getText().toString();
        if(phone.equals("")||password.equals("")){
            Toast.makeText(LoginActivity.this, "用户名或密码为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.get()
                .url(IP+"UserManager?"+"phone_number="+phone+"&password="+password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onResponse(String response, int id) {
//                        String name=response.toString();
//                        try {
//                            JSONObject jsonObject = new JSONObject(name);
//                            String n = jsonObject.optString("phone_number");
//                            if (phone.equals(n)){
//                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                                intent.putExtra("phone_number",n);
//                                startActivity(intent);
//                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(LoginActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        Gson gson = new Gson();
                        User user = gson.fromJson(response,new TypeToken<User>(){}.getType());

                        if (user.getPhone_number().equals(phone)){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isInputValid() {
        //检查用户输入的合法性，这里暂且默认用户输入合法
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);//1.创建一个ProgressDialog的实例
                progressDialog.setTitle("这是一个 progressDialog");//2.设置标题
                progressDialog.setMessage("正在加载中，请稍等......");//3.设置显示内容
                progressDialog.setCancelable(true);//4.设置可否用back键关闭对话框
                progressDialog.show();//5.将ProgessDialog显示出来
                login();
                progressDialog.cancel();
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
//                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
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
