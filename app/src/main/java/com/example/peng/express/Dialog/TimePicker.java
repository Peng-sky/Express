package com.example.peng.express.Dialog;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class TimePicker{
    public static void showTimePickerDialog(Activity activity,int themeResId, final EditText tv, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog( activity,themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {

                        tv.setText(hourOfDay + "时" + minute  + "分");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }
}
