package com.example.peng.express.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.R;

import org.w3c.dom.Text;

import okhttp3.Response;

public class WalletDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private ImageView close;
    private TextView balance,fifth,hundred,t_hundred,recharge,one,three,six,yuan,yuany,yuanyy;
    private LinearLayout one_month,three_month,six_month;

    public WalletDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public WalletDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WalletDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context,R.layout.diy_wallet,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = 1000;
        lp.width =  1000;
        win.setAttributes(lp);
        initView(view);
    }

    private void initView(View view) {
        view.findViewById(R.id.close).setOnClickListener(this);
        balance = view.findViewById(R.id.balance);
        balance.setOnClickListener(this);
        fifth = view.findViewById(R.id.fifth);
        fifth.setOnClickListener(this);
        hundred = view.findViewById(R.id.hundred);
        hundred.setOnClickListener(this);
        t_hundred = view.findViewById(R.id.t_hundred);
        t_hundred.setOnClickListener(this);
        one_month = view.findViewById(R.id.line_one);
        one_month.setOnClickListener(this);
        three_month = view.findViewById(R.id.line_three);
        three_month.setOnClickListener(this);
        six_month = view.findViewById(R.id.line_six);
        six_month.setOnClickListener(this);
        recharge = view.findViewById(R.id.recharge);
        recharge.setOnClickListener(this);

        one = view.findViewById(R.id.one);
        three = view.findViewById(R.id.three);
        six = view.findViewById(R.id.six);
        yuan = view.findViewById(R.id.yuan);
        yuany = view.findViewById(R.id.yuany);
        yuanyy = view.findViewById(R.id.yuanyy);
    }

    private void reset(){

        one_month.setBackgroundResource(R.drawable.line_un);
        three_month.setBackgroundResource(R.drawable.line_un);
        six_month.setBackgroundResource(R.drawable.line_un);
    }

    @Override
    public void onClick(View v) {

        Resources res = context.getResources();
        switch (v.getId()){
            case R.id.close:
                dismiss();
                break;
            case R.id.balance:

                break;
            case R.id.fifth:

                break;
            case R.id.hundred:
                break;
            case R.id.t_hundred:
                break;

            case R.id.line_one:
                if (one_month.getBackground().getConstantState().equals( res.getDrawable(R.drawable.line_un).getConstantState())){
                    one_month.setBackgroundResource(R.drawable.line_select);
                }else {
                    one_month.setBackgroundResource(R.drawable.line_un);
                }
                break;

            case R.id.line_three:
                if (three_month.getBackground().getConstantState().equals( res.getDrawable(R.drawable.line_un).getConstantState())){
                    three_month.setBackgroundResource(R.drawable.line_select);
                }else {
                    three_month.setBackgroundResource(R.drawable.line_un);
                }
                break;
            case R.id.line_six:

                if (six_month.getBackground().getConstantState().equals( res.getDrawable(R.drawable.line_un).getConstantState())){
                    six_month.setBackgroundResource(R.drawable.line_select);
                }else {
                    six_month.setBackgroundResource(R.drawable.line_un);
                }
                break;
            case R.id.recharge:
                break;
        }
    }

}
