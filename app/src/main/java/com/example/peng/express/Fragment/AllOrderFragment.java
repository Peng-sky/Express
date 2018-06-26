package com.example.peng.express.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peng.express.Activity.SCOrderDetailsActivity;
import com.example.peng.express.Adapter.AllOrderAdapter;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class AllOrderFragment extends Fragment {
    private ListView list_all_order;
    private List<SchoolOrder.Body> bodyList;
    private AllOrderAdapter allOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order,null);
        list_all_order = view.findViewById(R.id.list_all_order);
        OkHttpUtils.get()
                .url(IP+"findAllSc")
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
                        allOrderAdapter = new AllOrderAdapter(getActivity(),bodyList);
                        list_all_order.setAdapter(allOrderAdapter);
                        list_all_order.invalidate();
                        allOrderAdapter.notifyDataSetChanged();
                        list_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(),SCOrderDetailsActivity.class);
                                intent.putExtra("body",bodyList.get(position));
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });
                    }
                });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
