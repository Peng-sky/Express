package com.example.peng.express.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.Adapter.viewPagerAdapter;
import com.example.peng.express.Bean.User;
import com.example.peng.express.Dialog.OnlineSendDialog;
import com.example.peng.express.Fragment.MainPageFragment;
import com.example.peng.express.Fragment.MySelfFragment;
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
    private TextView tv_home,tv_send,tv_main,tv_package;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_home = findViewById(R.id.tv_home);
        tv_send = findViewById(R.id.tv_send);
        tv_main = findViewById(R.id.tv_main);
        tv_package = findViewById(R.id.tv_package);
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
        MySelfFragment selfFragment=new MySelfFragment();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        bundle.putSerializable("phone",user.getPhone_number());
        selfFragment.setArguments(bundle);
        PackageFragment packageFragment = new PackageFragment();
        OrderSendFragment orderSendFragment = new OrderSendFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(mainPageFragment);
        //fragmentList.add(orderSendFragment);
        fragmentList.add(packageFragment);
        fragmentList.add(selfFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new viewPagerAdapter(fragmentManager,fragmentList));
    }


    @Override
    public void onClick(View v) {
        resetImg();
        resetTextColor();
        switch (v.getId()){
            case R.id.linear_homepage:
                viewPager.setCurrentItem(0);
                tv_home.setTextColor(getResources().getColor(R.color.text_color));
                img_home.setImageResource(R.mipmap.home);
                break;
            case R.id.linear_post_express:
//                viewPager.setCurrentItem(1);
//                tv_send.setTextColor(getResources().getColor(R.color.text_color));
//                img_send.setImageResource(R.mipmap.send_se);
                OnlineSendDialog onlineSendDialog = new OnlineSendDialog(MainActivity.this);
                onlineSendDialog.show();
                break;
            case R.id.linear_package:
                viewPager.setCurrentItem(1);
                tv_package.setTextColor(getResources().getColor(R.color.text_color));
                img_package.setImageResource(R.mipmap.express_se);
                break;
            case R.id.linear_myself:
                viewPager.setCurrentItem(2);
                tv_main.setTextColor(getResources().getColor(R.color.text_color));
                img_myself.setImageResource(R.mipmap.main_se);
                break;
        }
    }

    private void resetImg() {
        img_home.setImageResource(R.mipmap.home_un);
        img_send.setImageResource(R.mipmap.send_package);
        img_package.setImageResource(R.mipmap.express);
        img_myself.setImageResource(R.mipmap.main);
    }

    private void resetTextColor() {
        tv_home.setTextColor(getResources().getColor(R.color.gray));
        tv_main.setTextColor(getResources().getColor(R.color.gray));
        tv_package.setTextColor(getResources().getColor(R.color.gray));
        tv_send.setTextColor(getResources().getColor(R.color.gray));
    }
    
}
