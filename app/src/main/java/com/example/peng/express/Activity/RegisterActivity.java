package com.example.peng.express.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peng.express.R;
import com.example.peng.express.Receiver.SMSReceiver;

public class RegisterActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private SMSReceiver smsReceiver;
    private Button btn_register_confirm;
    private EditText et_register_phone_num,et_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_register_phone_num = findViewById(R.id.et_register_phone_num);
        btn_register_confirm = findViewById(R.id.btn_register_confirm);
        et_password = findViewById(R.id.et_rg_password);
        btn_register_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_register_phone_num.getText().toString();
                String password = et_password.getText().toString();
                Intent intent = new Intent(RegisterActivity.this,WriteUserInfoActivity.class);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            }
        });
    }
//    private void initBroadCast() {
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        smsReceiver = new SMSReceiver();
//        //动态注册广播
//        registerReceiver(smsReceiver,intentFilter);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //取消注册广播
//        unregisterReceiver(smsReceiver);
//    }
}
