package com.example.peng.express.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.peng.express.Bean.DirOrder;
import com.example.peng.express.Bean.JsonAna;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AllOrderAdapter extends BaseAdapter {
    private Activity activity;
    private List<SchoolOrder.Body> bodyList;


    public AllOrderAdapter(Activity activity, List<SchoolOrder.Body> bodyList){
        this.activity = activity;
        this.bodyList = bodyList;
    }
    @Override
    public int getCount() {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
//        ViewHolderSend holderSend = null;
//        ViewHolderTake holderTake = null;
//
//        int type = getItemViewType(i);
//
//        if (convertView ==null){
//            holderSend = new ViewHolderSend();
//            holderTake = new ViewHolderTake();
//
//            switch (type){
//                case TYPE_SEND:
//                    convertView = View.inflate(activity,R.layout.item_send_order_list,null);
//                    holderSend.username = convertView.findViewById(R.id.username);
//                    holderSend.phone= convertView.findViewById(R.id.phone);
//                    holderSend.take_add = convertView.findViewById(R.id.take_add);
//                    holderSend.reach_time = convertView.findViewById(R.id.reach_time);
//                    break;
//                case TYPE_TAKE:
//                    convertView = View.inflate(activity,R.layout.item_sc_order_list,null);
//                    holderTake.username = convertView.findViewById(R.id.sc_username);
//                    holderTake.phone = convertView.findViewById(R.id.phone);
//                    holderTake.express_company = convertView.findViewById(R.id.express_company);
//                    holderTake.track_number = convertView.findViewById(R.id.track_number);
//                    holderTake.take_add = convertView.findViewById(R.id.take_add);
//                    holderTake.address = convertView.findViewById(R.id.address);
//                    holderTake.reach_time = convertView.findViewById(R.id.reach_time);
//                    break;
//            }
//        }else{
//            switch (type){
//                case TYPE_SEND:
//                    holderSend.username.setText(jsonAna.getOrderList().get(i).getSender());
//                    holderSend.phone.setText(jsonAna.getOrderList().get(i).getSender_phone());
//                    holderSend.take_add.setText(jsonAna.getOrderList().get(i).getSender_phone());
//                    holderSend.reach_time.setText(jsonAna.getOrderList().get(i).getSender_phone());
//                    break;
//                case TYPE_TAKE:
//                    holderTake.username.setText(jsonAna.getBodyList().get(i).getUsername());
//                    holderTake.phone.setText(jsonAna.getBodyList().get(i).getPhone_number());
//                    holderTake.express_company.setText(jsonAna.getBodyList().get(i).getExpress_company());
//                    holderTake.track_number.setText(jsonAna.getBodyList().get(i).getTrack_number());
//                    holderTake.take_add.setText(jsonAna.getBodyList().get(i).getTake_add());
//                    holderTake.address.setText(jsonAna.getBodyList().get(i).getAddress());
//                    holderTake.reach_time.setText(jsonAna.getBodyList().get(i).getState());
//                    break;
//            }
//        }

//        return convertView;
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sc_order_list,null);
            holder.username = convertView.findViewById(R.id.sc_username);
            holder.phone = convertView.findViewById(R.id.phone);
            holder.express_company = convertView.findViewById(R.id.express_company);
            holder.track_number = convertView.findViewById(R.id.track_number);
//            holder.take_add = convertView.findViewById(R.id.take_add);
            holder.address = convertView.findViewById(R.id.address);
            holder.reach_time = convertView.findViewById(R.id.reach_time);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.username.setText(bodyList.get(i).getUsername());
        holder.phone.setText(bodyList.get(i).getPhone_number());
        holder.express_company.setText(bodyList.get(i).getExpress_company());
        holder.track_number.setText(bodyList.get(i).getTrack_number());
//        holder.take_add.setText(bodyList.get(i).getTake_add());
        holder.address.setText(bodyList.get(i).getAddress());
        holder.reach_time.setText(bodyList.get(i).getState());
        return convertView;
    }

    public void refresh(List<SchoolOrder.Body> bodies){
        bodyList.removeAll(bodyList);
        bodyList.addAll(bodies);
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView username,phone,express_company,track_number,take_add,address,reach_time;
    }

//    class ViewHolderTake{
//        TextView username,phone,express_company,track_number,take_add,address,reach_time;
//    }
//
//    class ViewHolderSend{
//        TextView username,phone,take_add,reach_time;
//    }
}
