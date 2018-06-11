package com.example.peng.express.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.Activity.MainActivity;
import com.example.peng.express.R;

public class MainPageFragment extends Fragment implements View.OnClickListener{
    private LinearLayout linear_order_send,linear_freight,linear_vip,linear_contact_CS;
    private TextView tv_uncollected_package,tv_finish_send;
    private ImageView img_scan,img_search;
    private EditText et_search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page,null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        linear_order_send = view.findViewById(R.id.linear_order_send);
        linear_order_send.setOnClickListener(this);
        linear_freight = view.findViewById(R.id.linear_freight);
        linear_freight.setOnClickListener(this);
        linear_vip = view.findViewById(R.id.linear_vip);
        linear_vip.setOnClickListener(this);
        linear_contact_CS = view.findViewById(R.id.linear_contact_CS);
        linear_contact_CS.setOnClickListener(this);
        tv_uncollected_package = view.findViewById(R.id.tv_uncollected_package);
        tv_uncollected_package.setOnClickListener(this);
        tv_finish_send = view.findViewById(R.id.tv_finish_send);
        tv_finish_send.setOnClickListener(this);
        img_scan = view.findViewById(R.id.img_scan);
        img_scan.setOnClickListener(this);
        img_search = view.findViewById(R.id.img_search);
        img_search.setOnClickListener(this);
        et_search = view.findViewById(R.id.et_search);
        et_search.setOnClickListener(this);
        et_search.setFocusable(false);
        et_search.setClickable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_order_send:

                break;
            case R.id.linear_freight:break;
            case R.id.linear_vip:break;
            case R.id.linear_contact_CS:break;
            case R.id.tv_uncollected_package:break;
            case R.id.tv_finish_send:break;
            case R.id.img_scan:
                customScan();
                break;
            case R.id.img_search:break;
            case R.id.et_search:break;
        }
    }

    public void customScan(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0x0001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
