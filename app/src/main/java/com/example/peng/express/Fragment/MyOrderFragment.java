package com.example.peng.express.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.peng.express.Adapter.AllOrderAdapter;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.Bean.User;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;
import static com.example.peng.express.Activity.LoginActivity.IP;

public class MyOrderFragment extends Fragment {
    private ListView list_my_order;
    private List<SchoolOrder.Body> bodyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order,null);
        list_my_order = view.findViewById(R.id.list_my_order);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone","Null");
//        Bundle users = getArguments();
//        if (users==null){
//            Log.i("data", "onCreateView: 数据传递失败");
//        }
//        User user = (User) users.getSerializable("phone_num");
//        String phone = user.getPhone_number();
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
                    }
                });
        return view;
    }
}
