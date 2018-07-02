package com.example.peng.express.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peng.express.Activity.AcceptScOrderActivity;
import com.example.peng.express.Adapter.AllOrderAdapter;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class MyOrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ListView list_my_order;
    private List<SchoolOrder.Body> bodyList;
    private AllOrderAdapter allOrderAdapter;
    private SwipeRefreshLayout swipe;
    private boolean isRefresh = false;
    private String phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order,null);
        list_my_order = view.findViewById(R.id.list_my_order);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone","Null");
        swipe = view.findViewById(R.id.swipe);
        swipe.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
// 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipe.setDistanceToTriggerSync(300);
        swipe.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        swipe.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        swipe.setSize(SwipeRefreshLayout.LARGE);
        swipe.measure(0, 0);
        swipe.setRefreshing(true);
        //设置下拉刷新的监听
        swipe.setOnRefreshListener(this);
        Log.i("phone", "onCreateView: "+phone);
        OkHttpUtils.get()
                .url(IP+"findMySc?phone_number="+phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("internet", "onError: "+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        bodyList = gson.fromJson(response,new TypeToken<List<SchoolOrder.Body>>(){}.getType());
                        list_my_order.setAdapter(new AllOrderAdapter(getActivity(),bodyList));
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
                    swipe.setRefreshing(false);
                    OkHttpUtils.get()
                            .url(IP+"findMySc?phone_number="+phone)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.i("internet", "onError: " + e);
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println(response);
                                    Gson gson = new Gson();
                                    bodyList = gson.fromJson(response, new TypeToken<List<SchoolOrder.Body>>() {}.getType());
                                    allOrderAdapter = new AllOrderAdapter(getActivity(), bodyList);
                                    list_my_order.setAdapter(allOrderAdapter);
                                    list_my_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(getActivity(), AcceptScOrderActivity.class);
                                            intent.putExtra("body", bodyList.get(position));
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    });
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

