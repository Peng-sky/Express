package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.peng.express.Fragment.AllOrderFragment;
import com.example.peng.express.Fragment.OrderSendFragment;
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
        send_fragment = new OrderSendFragment();
        transaction.replace(R.id.fragment_frame,send_fragment);
        transaction.commit();
    }

    private void initView() {
        send = findViewById(R.id.send_package);
        send.setOnClickListener(this);
        take = findViewById(R.id.take_package);
        take.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        manager =getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.allOrder:
                /**
                 * 为了防止重叠，需要点击之前先移除其他Fragment
                 */
                hideFragment(transaction);

                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
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
