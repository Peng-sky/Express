package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.peng.express.R;

public class MyselfActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_about;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);

        initView();
    }

    private void initView() {
        tv_about = findViewById(R.id.tv_modify_info);

        tv_about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_modify_info:
                startActivity(new Intent(MyselfActivity.this,ModifyActivity.class));
                break;
        }
    }
}
