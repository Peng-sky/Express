package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.ImageView;

import com.example.peng.express.R;

public class AboutActivity extends AppCompatActivity {
    //声明对象
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //关联控件
        back = findViewById(R.id.back);

        //设置监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
