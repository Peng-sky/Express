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
import com.example.peng.express.Util.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private NoScrollViewPager viewPager;
    private LinearLayout linear_homepage,linear_post_express,linear_package,linear_myself;
    private ImageView img_home,img_send,img_package,img_myself;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setNoScroll(true);
        linear_homepage = findViewById(R.id.linear_homepage);
        linear_homepage.setOnClickListener(this);
        linear_post_express = findViewById(R.id.linear_post_express);
        linear_post_express.setOnClickListener(this);
        linear_package = findViewById(R.id.linear_package);
        linear_package.setOnClickListener(this);
        linear_myself = findViewById(R.id.linear_myself);
        linear_myself.setOnClickListener(this);
        img_home = findViewById(R.id.img_home);
        img_send = findViewById(R.id.img_send);
        img_package = findViewById(R.id.img_package);
        img_myself = findViewById(R.id.img_myself);

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
    private void resetImg(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_homepage:
                viewPager.setCurrentItem(0);
                break;
            case R.id.linear_post_express:
                viewPager.setCurrentItem(1);
                break;
            case R.id.linear_package:
                viewPager.setCurrentItem(2);
                break;
            case R.id.linear_myself:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
