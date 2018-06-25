package com.example.peng.express.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.peng.express.R;
import com.example.peng.express.Util.CityPick;
import com.example.peng.express.Util.getLocation;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import static com.igexin.push.config.SDKUrlConfig.getLocation;

public class SenderAddressActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back, img_get_location;
    private CheckBox cb_default_sender,cb_default_direction;
    private EditText et_sender_name,et_region,et_detail_address,et_phone;
    private Button btn_commit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender_address);

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
        img_get_location = findViewById(R.id.img_get_location);
        cb_default_sender = findViewById(R.id.cb_default_sender);
        cb_default_direction = findViewById(R.id.cb_default_direction);
        et_sender_name = findViewById(R.id.et_sender_name);
        et_region = findViewById(R.id.et_region);
        et_detail_address = findViewById(R.id.et_detail_address);
        et_phone = findViewById(R.id.et_phone);
        et_region.setFocusable(false);
        et_region.setClickable(true);
        btn_commit = findViewById(R.id.btn_commit);
        img_get_location.setOnClickListener(this);
        cb_default_sender.setOnClickListener(this);
        cb_default_direction.setOnClickListener(this);
        et_region.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.img_get_location:
                new getLocation(SenderAddressActivity.this,et_region,et_detail_address);
                break;
            case R.id.cb_default_sender:
                SharedPreferences senderAdd = getSharedPreferences("senderAdd",0);
                senderAdd.edit()
                        .putString("senderName",et_sender_name.getText().toString())
                        .putString("phone",et_phone.getText().toString())
                        .putString("region",et_region.getText().toString())
                        .putString("detail",et_detail_address.getText().toString()).apply();
                break;
            case R.id.cb_default_direction:
                SharedPreferences direction = getSharedPreferences("direction",0);
                direction.edit()
                        .putString("senderName",et_sender_name.getText().toString())
                        .putString("phone",et_phone.getText().toString())
                        .putString("region",et_region.getText().toString())
                        .putString("detail",et_detail_address.getText().toString()).apply();
                break;
            case R.id.et_region:
                new CityPick(SenderAddressActivity.this,et_region);
                break;
            case R.id.btn_commit:
                String name = et_sender_name.getText().toString();
                String phone = et_phone.getText().toString();
                String address =et_region.getText().toString()+et_detail_address.getText().toString();
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("sender_address",address);
                this.setResult(0x001,intent);
                finish();
                break;
        }
    }
}
