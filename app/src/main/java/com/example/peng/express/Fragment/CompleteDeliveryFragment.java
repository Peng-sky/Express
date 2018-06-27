package com.example.peng.express.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class CompleteDeliveryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView lv_complete_delivery;
    private int type = 1;
    private List<DirOrder.Body> bodyList;
    private DirOrderAdapter dirOrderAdapter;
    private SwipeRefreshLayout swipe;
    private boolean isRefresh = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_delivery,null);
        lv_complete_delivery = view.findViewById(R.id.lv_complete_delivery);
        swipe = view.findViewById(R.id.swipe);
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
                        lv_complete_delivery.setAdapter(new DirOrderAdapter(getActivity(),bodyList,type));
                        lv_complete_delivery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        return view;
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
                                    dirOrderAdapter=new DirOrderAdapter(getActivity(),bodyList,type);
                                    dirOrderAdapter.refresh(bodyList);
                                    dirOrderAdapter.notifyDataSetChanged();
                                    dirOrderAdapter = new DirOrderAdapter(getActivity(),bodyList,type);
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