package com.example.peng.express.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.peng.express.R;
import com.example.peng.express.Util.CityPick;
import com.example.peng.express.Util.getLocation;
import com.lljjcoder.citypickerview.widget.CityPicker;

public class DirectionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back,img_get_location;
    private EditText et_region,et_direction_name,et_direction_phone,et_detail_address;
    private Button btn_commit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        initView();
    }

    private void initView(){
        btn_commit = findViewById(R.id.btn_commit);
        img_get_location = findViewById(R.id.img_get_location);
        img_back = findViewById(R.id.img_back);
        et_direction_name = findViewById(R.id.et_direction_name);
        et_direction_phone = findViewById(R.id.et_direction_phone);
        et_detail_address = findViewById(R.id.et_detail_address);
        et_region = findViewById(R.id.et_region);
        et_region.setClickable(true);
        et_region.setFocusable(false);

        img_back.setOnClickListener(this);
        img_get_location.setOnClickListener(this);
        et_region.setOnClickListener(this);
        btn_commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.et_region:
                new CityPick(DirectionActivity.this,et_region);//调用CityPicker选取区域
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.img_get_location:
                new getLocation(DirectionActivity.this,et_region,et_detail_address);
                break;
            case R.id.btn_commit:
                String name = et_direction_name.getText().toString();
                String phone = et_direction_phone.getText().toString();
                String address =et_region.getText().toString()+et_detail_address.getText().toString();
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("sender_address",address);
                this.setResult(0x002,intent);
                finish();
                break;
        }
    }

//    public void chooseArea(View view) {
//        //判断输入法的隐藏状态
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm.isActive()) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
//
//        }
//    }

}
