package com.example.peng.express.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.peng.express.Adapter.VpAdapter;
import com.example.peng.express.Fragment.CompleteDeliveryFragment;
import com.example.peng.express.Fragment.DeliveringFragment;
import com.example.peng.express.R;

import java.util.ArrayList;
import java.util.List;

public class PackageActivity extends AppCompatActivity {
    public TabLayout tab_package;
    private ViewPager vp_package;
    private List<Fragment> fragmentList;
    private List<String> list_title;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        initView();
    }

    private void initView() {
        tab_package = findViewById(R.id.tab_package);
        vp_package = findViewById(R.id.vp_package);

        CompleteDeliveryFragment completeDeliveryFragment = new CompleteDeliveryFragment();
        DeliveringFragment deliveringFragment = new DeliveringFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(deliveringFragment);
        fragmentList.add(completeDeliveryFragment);

        list_title = new ArrayList<>();
        list_title.add("未完成");
        list_title.add("完成");
        tab_package.setTabMode(TabLayout.MODE_FIXED);
        tab_package.addTab(tab_package.newTab().setText(list_title.get(0)));
        tab_package.addTab(tab_package.newTab().setText(list_title.get(1)));
//        int page = intent.getIntExtra("page",0);
//        tab_package.getTabAt(page);

        vp_package.setAdapter(new VpAdapter(this.getSupportFragmentManager(),fragmentList,list_title));
//        vp_package.setCurrentItem(page);
        tab_package.setupWithViewPager(vp_package);
    }
}
