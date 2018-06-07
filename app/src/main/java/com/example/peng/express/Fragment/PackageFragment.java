package com.example.peng.express.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peng.express.Adapter.VpAdapter;
import com.example.peng.express.R;

import java.util.ArrayList;
import java.util.List;

public class PackageFragment extends Fragment{
    private List<Fragment> fragmentList;
    private List<String> list_title;
    private TabLayout tab_package;
    private ViewPager vp_package;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab_package = view.findViewById(R.id.tab_package);
        vp_package = view.findViewById(R.id.vp_package);

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

        vp_package.setAdapter(new VpAdapter(this.getFragmentManager(),fragmentList,list_title));
//        vp_package.setCurrentItem(page);
        tab_package.setupWithViewPager(vp_package);
    }
}
