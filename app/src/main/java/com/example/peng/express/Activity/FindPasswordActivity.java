package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peng.express.R;

public class FindPasswordActivity extends AppCompatActivity {
    private Button btn_confirm,btn_get_code;
    private EditText et_phone_num,et_identifying_code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        btn_confirm = findViewById(R.id.btn_confirm);
        btn_get_code = findViewById(R.id.btn_get_code);
        et_phone_num = findViewById(R.id.et_phone_num);
        et_identifying_code = findViewById(R.id.et_identifying_code);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindPasswordActivity.this,ModifyPasswordActivity.class));
            }
        });

    }
}
