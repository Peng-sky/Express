package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.peng.express.R;
import com.example.peng.express.Service.DemoIntentService;
import com.igexin.sdk.PushManager;

import static geogle.zxing.activity.CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back;
    private Intent intent;
    private EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        img_back = findViewById(R.id.back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        String searchText = intent.getStringExtra(INTENT_EXTRA_KEY_QR_SCAN);
        editText = findViewById(R.id.et_search);
        editText.setOnClickListener(this);
        editText.setText(searchText) ;
    }

    @Override
    public void onClick(View v) {

    }
}
