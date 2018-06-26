package com.example.peng.express.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.peng.express.Adapter.DirOrderAdapter;
import com.example.peng.express.Bean.DirOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class CompleteActivity extends AppCompatActivity {

    private ImageView img_back;
    private ListView lv_complete_delivery;
    private int type = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        img_back = findViewById(R.id.back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_complete_delivery = findViewById(R.id.lv_complete_delivery);
        lv_complete_delivery.setScrollbarFadingEnabled(true);
        OkHttpUtils.post()
                .url(IP+"findAllDir")
                .addParams("type",type+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        List<DirOrder.Body> bodyList = gson.fromJson(response,new TypeToken< List<DirOrder.Body>>(){}.getType());
                        lv_complete_delivery.setAdapter(new DirOrderAdapter(CompleteActivity.this,bodyList,type));
                        lv_complete_delivery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Intent intent = new Intent(getActivity())
                            }
                        });
                    }
                });
    }
}

