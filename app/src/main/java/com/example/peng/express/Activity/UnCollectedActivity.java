package com.example.peng.express.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.peng.express.Adapter.DirOrderAdapter;
import com.example.peng.express.Bean.DirOrder;
import com.example.peng.express.Fragment.DeliveringFragment;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;


public class UnCollectedActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageView img_back;
    private ListView list_delivering;
    private int type = 2;
    private SwipeRefreshLayout swipe;
    private DirOrderAdapter dirOrderAdapter;
    private boolean isRefresh = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivering);
        swipe = findViewById(R.id.swipe);
        swipe.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED);
// 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipe.setDistanceToTriggerSync(300);
        swipe.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        swipe.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        swipe.setSize(SwipeRefreshLayout.LARGE);
        swipe.measure(0,0);
        swipe.setRefreshing(true);
        //设置下拉刷新的监听
        swipe.setOnRefreshListener(this);

        img_back = findViewById(R.id.back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list_delivering = findViewById(R.id.lv_delivering);
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
                        dirOrderAdapter = new DirOrderAdapter(UnCollectedActivity.this,bodyList,type);
                        list_delivering.setAdapter(dirOrderAdapter);
                        list_delivering.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Intent intent = new Intent(getActivity())
                            }
                        });
                        if (swipe.isRefreshing()) {
                            swipe.setRefreshing(false);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        if (!isRefresh){
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //显示或隐藏刷新进度条
                    swipe.setRefreshing(true);
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
                                    dirOrderAdapter.refresh(bodyList);
                                    dirOrderAdapter.notifyDataSetChanged();
                                    isRefresh = false;
                                    if (swipe.isRefreshing()) {
                                        swipe.setRefreshing(false);
                                    }
                                }

                            });
                }
            },0);
        }
    }

}
