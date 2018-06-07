package com.example.peng.express.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.Adapter.viewPagerAdapter;
import com.example.peng.express.Fragment.MainPageFragment;
import com.example.peng.express.Fragment.MainSelfFragment;
import com.example.peng.express.Fragment.OrderSendFragment;
import com.example.peng.express.Fragment.PackageFragment;
import com.example.peng.express.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private LinearLayout linear_homepage,linear_post_express,linear_package,linear_myself;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        linear_homepage = findViewById(R.id.linear_homepage);
        linear_post_express = findViewById(R.id.linear_post_express);
        linear_package = findViewById(R.id.linear_package);
        linear_myself = findViewById(R.id.linear_myself);
        /**/

        MainPageFragment mainPageFragment = new MainPageFragment();
        MainSelfFragment selfFragment= new MainSelfFragment();
        PackageFragment packageFragment = new PackageFragment();
        OrderSendFragment orderSendFragment = new OrderSendFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(mainPageFragment);
        fragmentList.add(orderSendFragment);
        fragmentList.add(packageFragment);
        fragmentList.add(selfFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new viewPagerAdapter(fragmentManager,fragmentList));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
