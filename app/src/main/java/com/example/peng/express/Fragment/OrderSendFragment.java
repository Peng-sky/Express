package com.example.peng.express.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Activity.DirectionActivity;
import com.example.peng.express.Activity.SenderAddressActivity;
import com.example.peng.express.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderSendFragment extends Fragment implements View.OnClickListener {
        private TextView tv_send_address,tv_addresses,tv_sender_name,tv_phone_num,tv_address,
    tv_direction,tv_direction_phone,tv_direction_add;
    private ImageView img_sender_info,img_addressee_info,img_open_option,
            img_minus,img_add,img_package_minus,img_package_add;
    private EditText et_option,et_estimate_weight,et_amount,et_package_amount;
    private CheckBox cb_agree;
    private Button btn_send_confirm;
    private LinearLayout linear_address,linear_direction;

    private String appkey = "";
    private String appsecret = "";
    private String appid = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_send,null);
        initView(view);
        parseManifests();
        return view;
    }

    private void parseManifests() {
        String packageName = getActivity().getApplicationContext().getPackageName();
        try {
            ApplicationInfo appInfo = getActivity().getApplicationContext().getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                appid = appInfo.metaData.getString("PUSH_APPID");
                appsecret = appInfo.metaData.getString("PUSH_APPSECRET");
                appkey = appInfo.metaData.getString("PUSH_APPKEY");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
                tv_send_address =view.findViewById(R.id.tv_send_address);
        tv_addresses = view.findViewById(R.id.tv_addresses);
        img_sender_info = view.findViewById(R.id.img_sender_info);
        //跳转到寄件人地址
        img_sender_info.setOnClickListener(this);
        img_addressee_info = view.findViewById(R.id.img_addressee_info);
        //跳转到收件人地址
        img_addressee_info.setOnClickListener(this);
        img_open_option = view.findViewById(R.id.img_open_option);
        img_open_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        img_minus = view.findViewById(R.id.img_minus);
        img_add = view.findViewById(R.id.img_add);
        img_package_minus = view.findViewById(R.id.img_package_minus);
        img_package_add = view.findViewById(R.id.img_package_add);
        et_option = view.findViewById(R.id.et_option);
        et_option.setFocusable(false);
        et_estimate_weight = view.findViewById(R.id.et_estimate_weight);
        et_estimate_weight.setFocusable(false);
        et_amount = view.findViewById(R.id.et_amount);
        et_package_amount = view.findViewById(R.id.et_package_amount);
        et_package_amount.setFocusable(false);
        cb_agree = view.findViewById(R.id.cb_agree);
        btn_send_confirm = view.findViewById(R.id.btn_send_confirm);
        btn_send_confirm.setOnClickListener(this);
        linear_direction = view.findViewById(R.id.linear_direction);
        linear_address  =view.findViewById(R.id.linear_address);

        tv_sender_name = view.findViewById(R.id.tv_sender_name);
        tv_phone_num = view.findViewById(R.id.tv_phone_num);
        tv_address = view.findViewById(R.id.tv_address);
        tv_direction = view.findViewById(R.id.tv_direction);
        tv_direction_phone = view.findViewById(R.id.tv_direction_phone);
        tv_direction_add = view.findViewById(R.id.tv_direction_add);

        img_minus.setOnClickListener(this);
        img_add.setOnClickListener(this);
        img_package_add.setOnClickListener(this);
        img_package_minus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_sender_info:
                Intent intent = new Intent(getActivity(),SenderAddressActivity.class);
                startActivityForResult(intent,0x001);
                break;
            case R.id.img_addressee_info:
                Intent intent1 = new Intent(getActivity(),DirectionActivity.class);
                startActivityForResult(intent1,0x002);
                break;
            case R.id.img_minus:
                int count = Integer.parseInt(et_estimate_weight.getText().toString());
                if (count==1){
                    return;
                }else {
                    count-=1;
                    et_estimate_weight.setText(count+"");
                }
                break;
            case R.id.img_add:
                int count1 = Integer.parseInt(et_estimate_weight.getText().toString());
                count1+=1;
                et_estimate_weight.setText(count1+"");
                break;
            case R.id.img_package_minus:
                int count2 = Integer.parseInt(et_package_amount.getText().toString());
                if (count2==1){
                    return;
                }else {
                    count2-=1;
                    et_package_amount.setText(count2+"");
                }
                break;
            case R.id.img_package_add:
                int count3 = Integer.parseInt(et_package_amount.getText().toString());
                    count3+=1;
                    et_package_amount.setText(count3+"");
                break;
            case R.id.btn_send_confirm:

                break;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ni : info) {
                    if (ni.getState() == NetworkInfo.State.CONNECTED) {
                        Log.d("getui", "type = " + (ni.getType() == 0 ? "mobile" : ((ni.getType() == 1) ? "wifi" : "none")));
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 展示Dialog
     */
    private void openDialog(){
        final Button tv_file,tv_DP,tv_life,tv_cloth,tv_food,tv_other,btn_dialog_confirm;

        View dialog_item = getLayoutInflater().inflate(R.layout.diy_dialog_item,null);
        tv_file = dialog_item.findViewById(R.id.tv_file);
        tv_DP = dialog_item.findViewById(R.id.tv_DP);
        tv_life = dialog_item.findViewById(R.id.tv_life);
        tv_cloth = dialog_item.findViewById(R.id.tv_cloth);
        tv_food = dialog_item.findViewById(R.id.tv_food);
        tv_other = dialog_item.findViewById(R.id.tv_other);
        tv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_file.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_file.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_file.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_file.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        tv_DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_DP.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_DP.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_DP.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_DP.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        tv_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_life.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_life.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_life.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_life.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        tv_cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_cloth.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_cloth.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_cloth.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_cloth.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        tv_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_food.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_food.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_food.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_food.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getActivity().getBaseContext().getResources();
                if (tv_other.getBackground().getConstantState().equals( res.getDrawable(R.drawable.text_shape).getConstantState())){
                    tv_other.setBackgroundResource(R.drawable.text_selected_shape);
                    String type = tv_other.getText().toString();
                    et_option.setText(type);
                }
                else{
                    tv_other.setBackgroundResource(R.drawable.text_shape);
                }
            }
        });
        btn_dialog_confirm = dialog_item.findViewById(R.id.btn_dialog_confirm);
        final Dialog dialog = new Dialog(this.getActivity(),R.style.transparentFrameWindowStyle);
        dialog.setContentView(dialog_item,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.x = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        wl.gravity = Gravity.BOTTOM;
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        btn_dialog_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String address = data.getStringExtra("sender_address");
            switch (requestCode){
                case 0x001:
                        tv_send_address.setVisibility(View.GONE);
                        linear_address.setVisibility(View.VISIBLE);
                        tv_sender_name.setText(name);
                        tv_phone_num.setText(phone);
                        tv_address.setText(address);
                    break;
                case 0x002:
                    tv_addresses.setVisibility(View.GONE);
                    linear_direction.setVisibility(View.VISIBLE);
                    tv_direction.setText(name);
                    tv_direction_phone.setText(phone);
                    tv_direction_add.setText(address);
                    break;
            }
        }
        Log.i("NullPointerException","intent 为空");
    }
}
