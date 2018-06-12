package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;

public class VipActivity extends AppCompatActivity{
    private EditText track_number,express_company,phone,address,sms,remark;
    private Button commit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        initView();


    }

    private void initView() {
        track_number = findViewById(R.id.track_number);
        express_company = findViewById(R.id.express_company);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        sms = findViewById(R.id.sms);
        commit = findViewById(R.id.commit);
        remark = findViewById(R.id.remark);
    }
    private String getData(){
        String number = track_number.getText().toString();
        String company = express_company.getText().toString();
        String phones = phone.getText().toString();
        String smss = sms.getText().toString();
        String commits = commit.getText().toString();
        String remarks = remark.getText().toString();
        SchoolOrder so = new SchoolOrder(number,company,phones,smss,commits,remarks);
        Gson gson = new Gson();
        String json = gson.toJson(so);
        return json;
    }
}
