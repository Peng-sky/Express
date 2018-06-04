package com.example.peng.express.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> list_title;

    public VpAdapter(FragmentManager fm, List<Fragment> fragmentList,List<String> list_title) {
        super(fm);
        this.fragmentList = fragmentList;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position%list_title.size());
    }
}
