package com.example.peng.express.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class CompleteDeliveryFragment extends Fragment {
    private ListView lv_complete_delivery;
    private int type = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_delivery,null);
        lv_complete_delivery = view.findViewById(R.id.lv_complete_delivery);
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
                    }
                });
        return view;
    }
}
