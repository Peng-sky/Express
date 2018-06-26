package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.peng.express.R;

public class ModifyMyDataActivity extends AppCompatActivity {
    private EditText et_info_phone_num, et_info_username, et_info_birthday, et_info_email, et_info_province, et_info_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_data);

        initView();
    }

    private void initView() {

    }
}
