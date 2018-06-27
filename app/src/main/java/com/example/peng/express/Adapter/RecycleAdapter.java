package com.example.peng.express.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Activity activity;
    private List<SchoolOrder.Body> bodyList;

    public RecycleAdapter(Activity activity,List<SchoolOrder.Body> bodyList){
        this.activity = activity;
        this.bodyList = bodyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(activity,R.layout.item_sc_order_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SchoolOrder.Body body = bodyList.get(position);
        holder.username.setText(body.getUsername());
        holder.phone.setText(body.getPhone_number());
        holder.express_company.setText(body.getExpress_company());
        holder.track_number.setText(body.getTrack_number());
        holder.address.setText(body.getAddress());
        holder.reach_time.setText(body.getState());

    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }

    public void add(List<SchoolOrder.Body> bodyList1){

        int position = bodyList.size();
        bodyList.add(position, (SchoolOrder.Body) bodyList1);
    }

    public void refresh(List<SchoolOrder.Body> bodies){
        bodyList.removeAll(bodyList);
        bodyList.addAll(bodies);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
            TextView username,phone,express_company,track_number,address,reach_time;

        public ViewHolder(View convertView) {
            super(convertView);
            username = convertView.findViewById(R.id.sc_username);
            phone = convertView.findViewById(R.id.phone);
            express_company = convertView.findViewById(R.id.express_company);
            track_number = convertView.findViewById(R.id.track_number);
            address = convertView.findViewById(R.id.address);
            reach_time = convertView.findViewById(R.id.reach_time);
        }
    }



}
