package com.example.peng.express.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;

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
        if (bodyList.size()==0){
            return 0;
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
        if (convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sc_order_list,null);
            holder.username = convertView.findViewById(R.id.username);
            holder.phone = convertView.findViewById(R.id.phone);
            holder.express_company = convertView.findViewById(R.id.express_company);
            holder.track_number = convertView.findViewById(R.id.track_number);
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
        holder.address.setText(bodyList.get(i).getAddress());
        holder.reach_time.setText(bodyList.get(i).getState());

        return convertView;

    }

    class ViewHolder{
        TextView username,phone,express_company,track_number,address,reach_time;
    }
}
