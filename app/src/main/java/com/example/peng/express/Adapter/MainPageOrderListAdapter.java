package com.example.peng.express.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peng.express.R;

public class MainPageOrderListAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private int[] qianshouimgs;
    private String[] track_number;
    private String[] states;
    private String[] times;
    private Activity activity;

    public MainPageOrderListAdapter(Activity activity,int[] qianshouimg,String[] track_number,String[] state,String[] time ){
        this.activity=activity;
        this.qianshouimgs = qianshouimg;
        this.track_number = track_number;
        this.states = state;
        this.times = time;
    }
    @Override
    public int getCount() {
        return track_number.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView qianshouimg,tracknumber,state,time;

            convertView = LayoutInflater.from(activity).inflate(R.layout.item_order_list_main,null);
            qianshouimg = convertView.findViewById(R.id.qianshouimg);
            tracknumber = convertView.findViewById(R.id.track_number);
            state = convertView.findViewById(R.id.state);
            time = convertView.findViewById(R.id.time);

          qianshouimg.setBackgroundResource(qianshouimgs[position]);
          tracknumber.setText(track_number[position]+"");
          state.setText(states[position]+"");
          time.setText(times[position]+"");


        return convertView;
    }

}
