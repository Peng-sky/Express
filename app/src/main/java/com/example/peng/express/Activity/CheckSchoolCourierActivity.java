package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.peng.express.R;

public class CheckSchoolCourierActivity extends AppCompatActivity{
    private TextView tv_contact_admin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_school_courier);
        tv_contact_admin = findViewById(R.id.tv_contact_admin);
        tv_contact_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckSchoolCourierActivity.this,SchoolCourierActivity.class);
            }
        });

    }
}
