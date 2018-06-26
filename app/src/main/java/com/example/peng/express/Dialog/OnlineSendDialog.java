package com.example.peng.express.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.peng.express.Activity.SendOnLineActivity;
import com.example.peng.express.R;

public class OnlineSendDialog extends Dialog implements View.OnClickListener{
    private TextView sf,zto,yto,sto,yunda,best;
    private Intent intent;

    public OnlineSendDialog(@NonNull Context context) {
        super(context);
    }

    public OnlineSendDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OnlineSendDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(),R.layout.diy_send_online,null);
        setContentView(view);

        initView(view);

        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.onWindowAttributesChanged(wl);
        wl.gravity = Gravity.BOTTOM;
    }

    private void initView(View view) {
        sf = view.findViewById(R.id.sf);
        sf.setOnClickListener(this);
        zto = view.findViewById(R.id.zto);
        zto.setOnClickListener(this);
        yto = view.findViewById(R.id.yto);
        yto.setOnClickListener(this);
        yunda = view.findViewById(R.id.yunda);
        yunda.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this.getContext(), SendOnLineActivity.class);
        switch (v.getId()){
            case R.id.sf:
                intent.putExtra("url","http://www.sf-express.com/mobile/cn/sc/dynamic_function/ship/ship.html");
                this.getContext().startActivity(intent);
                break;
            case R.id.zto:
                intent.putExtra("url","https://m.zto.com/Order/create\n");
                this.getContext().startActivity(intent);
                break;
            case R.id.yto:
                intent.putExtra("url","http://ec.yto.net.cn/order.htm?appcode=MOBILE");
                this.getContext().startActivity(intent);
                break;
            case R.id.yunda:
                intent.putExtra("url","http://mobile.yundasys.com:2137/mobileweb/pages/send/send_express.html");
                this.getContext().startActivity(intent);
                break;
        }
    }
}
