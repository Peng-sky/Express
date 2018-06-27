package com.example.peng.express.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peng.express.Activity.DirectionActivity;
import com.example.peng.express.Bean.DirOrder;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;

import java.util.List;

public class DirOrderAdapter extends BaseAdapter {

    private int type;
    private List<DirOrder.Body> bodyList;
    private Activity activity;

    public DirOrderAdapter(Activity activity, List<DirOrder.Body> bodyList,int type) {
        this.activity = activity;
        this.bodyList = bodyList;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (bodyList.size()==0){
            Log.i("refresh", "getCount: 为空");
        }
        return bodyList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (type == 0){
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_dir_order_list, null);
                holder.username = convertView.findViewById(R.id.username);
                holder.phone = convertView.findViewById(R.id.phone);
                holder.address = convertView.findViewById(R.id.address);
                holder.send_time = convertView.findViewById(R.id.send_time);
                holder.state = convertView.findViewById(R.id.state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.username.setText(bodyList.get(i).getDir_name());
            holder.phone.setText(bodyList.get(i).getDir_phone());
            holder.address.setText(bodyList.get(i).getDir_address());
            holder.send_time.setText(bodyList.get(i).getSend_time());
            if (bodyList.get(i).getState().equals("1")){
                holder.state.setText("已完成");
            }else{
                holder.state.setText("派送中");
            }

        }else if (type==1){
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_dir_order_list, null);
                holder.username = convertView.findViewById(R.id.username);
                holder.phone = convertView.findViewById(R.id.phone);
                holder.address = convertView.findViewById(R.id.address);
                holder.send_time = convertView.findViewById(R.id.send_time);
                holder.state = convertView.findViewById(R.id.state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.username.setText(bodyList.get(i).getDir_name());
            holder.phone.setText(bodyList.get(i).getDir_phone());
            holder.address.setText(bodyList.get(i).getDir_address());
            holder.send_time.setText(bodyList.get(i).getSend_time());
            holder.state.setText("已完成");
        }else {
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_dir_order_list, null);
                holder.username = convertView.findViewById(R.id.username);
                holder.phone = convertView.findViewById(R.id.phone);
                holder.address = convertView.findViewById(R.id.address);
                holder.send_time = convertView.findViewById(R.id.send_time);
                holder.state = convertView.findViewById(R.id.state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.username.setText(bodyList.get(i).getDir_name());
            holder.phone.setText(bodyList.get(i).getDir_phone());
            holder.address.setText(bodyList.get(i).getDir_address());
            holder.send_time.setText(bodyList.get(i).getSend_time());
            holder.state.setText("派送中");
        }
//        String state = bodyList.get(i).getState();
//        if (state.equals(Shipping)){
//            state = "发货中";
//
//        }else if (state.equals(InTransit)){
//            state = "运输中";
//        }else if (state.equals(Sending)){
//            state = "正在派送中请保持电话畅通";
//        }else {
//            state = "订单已完成";
//        }
        return convertView;
    }

    public void refresh(List<DirOrder.Body> bodies){
        bodyList.removeAll(bodyList);
        bodyList.addAll(bodies);
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView username, phone, address, send_time, state;
    }
}
