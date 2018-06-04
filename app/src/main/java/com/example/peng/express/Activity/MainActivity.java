package com.example.peng.express.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_search;
    private ImageView img_scan,img_search,img_order_send,img_vip;
    private TextView tv_uncollected_package,tv_finish_send,tv_freight,tv_contact_CS;
    private LinearLayout linear_homepage,linear_post_express,linear_package,linear_myself;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img_order_send = findViewById(R.id.img_order_send);
        img_scan = findViewById(R.id.img_scan);
        img_search = findViewById(R.id.img_search);
        img_vip = findViewById(R.id.img_vip);
        tv_uncollected_package = findViewById(R.id.tv_uncollected_package);
        tv_finish_send = findViewById(R.id.tv_finish_send);
        tv_freight = findViewById(R.id.tv_freight);
        tv_contact_CS = findViewById(R.id.tv_contact_CS);
        et_search = findViewById(R.id.et_search);
        linear_myself = findViewById(R.id.linear_myself);
        linear_homepage = findViewById(R.id.linear_homepage);
        linear_package = findViewById(R.id.linear_package);
        linear_post_express = findViewById(R.id.linear_post_express);

        et_search.setOnClickListener(this);
        linear_myself.setOnClickListener(this);
        img_order_send.setOnClickListener(this);
        img_vip.setOnClickListener(this);
        img_scan.setOnClickListener(this);
        img_search.setOnClickListener(this);
        linear_post_express.setOnClickListener(this);
        tv_uncollected_package.setOnClickListener(this);
        tv_finish_send.setOnClickListener(this);
        tv_freight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_order_send:
                Intent intent = new Intent(MainActivity.this,OrderSendActivity.class);
                startActivity(intent);
                break;
            case R.id.et_search:
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                break;
            case R.id.linear_myself:
                startActivity(new Intent(MainActivity.this,MyselfActivity.class));
                break;
            case R.id.linear_post_express:
                startActivity(new Intent(MainActivity.this,OrderSendActivity.class));
                break;
            case R.id.tv_uncollected_package:
                startActivity(new Intent(MainActivity.this,PackageActivity.class));
                break;
            case R.id.tv_finish_send:
                Intent intent1 = new Intent(MainActivity.this,PackageActivity.class);
                intent1.putExtra("page",1);
                startActivity(intent1);
                break;
            case R.id.tv_freight:
                startActivity(new Intent(MainActivity.this,FreightActivity.class));
                break;
        }
    }
}
