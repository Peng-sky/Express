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
    private TabLayout tab_package;
    private ViewPager vp_package;
    private String[] title = new String[]{"全部","未完成","完成"};
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
        AllPackageFragment allPackageFragment = new AllPackageFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(allPackageFragment);
        fragmentList.add(deliveringFragment);
        fragmentList.add(completeDeliveryFragment);

        tab_package.setTabMode(TabLayout.GRAVITY_CENTER);

        tab_package.addTab(tab_package.newTab().setText(title[0]));
        tab_package.addTab(tab_package.newTab().setText(title[1]));

        vp_package.setAdapter(new VpAdapter(this.getFragmentManager(),fragmentList,title));
//        vp_package.setCurrentItem(page);
        tab_package.setupWithViewPager(vp_package);
    }
}
