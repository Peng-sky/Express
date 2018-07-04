package com.example.peng.express.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Bean.User;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igexin.sdk.PushManager;
import com.mob.MobSDK;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_forget_password,tv_register;
    private EditText et_phone_number,et_password;
    private Button btn_login;
    private String  phone= "";
    private String  password= "";
    public static final String IP = "http://172.17.156.172:8080/servlet/";
    private ProgressDialog dlg;




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

    public void login() {
        phone = et_phone_number.getText().toString();
        password = et_password.getText().toString();
        if(phone.equals("")||password.equals("")){
            Toast.makeText(LoginActivity.this, "用户名或密码为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        dlg = new ProgressDialog(this);
        dlg.setIcon(R.mipmap.logo);
        dlg.setMessage("加载中请稍后");
        dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置水平进度条
        dlg.show();
        OkHttpUtils.get()
                .url(IP+"UserManager?"+"phone_number="+phone+"&password="+password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("internet", "onError: "+e);
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        User user = gson.fromJson(response,new TypeToken<User>(){}.getType());
                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("phone",user.getPhone_number());
                        editor.putString("userType",user.getUser_type());
                        editor.commit();
                        if (user.getPhone_number().equals(phone)){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            dlg.dismiss();
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_SHORT).show();
                            dlg.dismiss();
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
                login();
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,0x003);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 0x003){
            String phone =data.getStringExtra("phone");
            String password =data.getStringExtra("password");
                Log.i("12312312", "onActivityResult: "+phone+password);
            et_phone_number.setText(phone);
            et_password.setText(password);
            }
        }
    }
}
