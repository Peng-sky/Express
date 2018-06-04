package com.example.peng.express.Util;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;

import com.example.peng.express.Activity.DirectionActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

public class CityPick {

    public CityPick(Context context, final EditText editText) {
        CityPicker cityPicker = new CityPicker.Builder(context)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("四川省")
                .city("成都市")
                .district("武侯区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                editText.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }
}
