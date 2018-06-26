package com.example.peng.express.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.peng.express.R;

public class DiscountDialog extends Dialog {


    public DiscountDialog(@NonNull Context context) {
        super(context);
    }

    public DiscountDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DiscountDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(),R.layout.diy_discount,null);
        setContentView(view);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = 1000;
        lp.width =  1000;
        win.setAttributes(lp);
    }
}
