package com.example.peng.express.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.peng.express.Fragment.OrderSendFragment;
import com.example.peng.express.Fragment.SCTakePackageFragment;
import com.example.peng.express.R;

public class SchoolActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView send,take;
    private Fragment send_fragment,take_fragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initView();

    }

    private void initView() {
        send = findViewById(R.id.send_package);
        send.setOnClickListener(this);
        take = findViewById(R.id.take_package);
        take.setOnClickListener(this);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        take_fragment = new SCTakePackageFragment();
        transaction.replace(R.id.fragment_frame,take_fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        manager =getSupportFragmentManager();
        transaction = manager.beginTransaction();

        send.setTextColor(getResources().getColor(R.color.sc_text));
        take.setTextColor(getResources().getColor(R.color.sc_text));

        switch (v.getId()){
            case R.id.send_package:
                send.setTextColor(getResources().getColor(R.color.title));
                /**
                 * 为了防止重叠，需要点击之前先移除其他Fragment
                 */
                hideFragment(transaction);
                send_fragment = new OrderSendFragment();
                transaction.replace(R.id.fragment_frame,send_fragment);
                transaction.commit();
                break;

            case R.id.take_package:
                take.setTextColor(getResources().getColor(R.color.title));
                hideFragment(transaction);
                take_fragment = new SCTakePackageFragment();
                transaction.replace(R.id.fragment_frame,take_fragment);
                transaction.commit();
                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        manager =getSupportFragmentManager();
        transaction = manager.beginTransaction();

        if (send_fragment != null) {
            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
            transaction.remove(send_fragment);
        }
        if (take_fragment != null) {
            //transaction.hide(f2);
            transaction.remove(take_fragment);
        }
    }
}
