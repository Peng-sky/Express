package com.example.peng.express.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Activity.CSActivity;
import com.example.peng.express.Activity.CompleteActivity;
import com.example.peng.express.Activity.FreightActivity;
import com.example.peng.express.Activity.MainActivity;
import com.example.peng.express.Activity.SchoolActivity;
import com.example.peng.express.Activity.SearchActivity;
import com.example.peng.express.Activity.UnCollectedActivity;
import com.example.peng.express.Activity.VipsActivity;
import com.example.peng.express.Adapter.MainPageOrderListAdapter;
import com.example.peng.express.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import geogle.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

public class MainPageFragment extends Fragment implements View.OnClickListener{
    private LinearLayout linear_order_send,linear_freight,linear_vip,linear_contact_CS;
    private TextView tv_uncollected_package,tv_finish_send;
    private ImageView img_scan,img_search;
    private EditText et_search;
    private ListView listView;
    private int[] qianshouimg = new int[]{R.mipmap.qianshou,R.mipmap.weiqianshou,R.mipmap.qianshou};
    private String[] track_number= new String[]{"1111111111111","2222222222222","3333333333333"};
    private String[] state= new String[]{"欢迎为你服务","正在为你服务","期待下次为你服务"};
    private String[] time= new String[]{"2016-08-11 21：26：56","2016-08-12 21：26：56","2016-08-13 21：26：56"};
    private ViewPager banner;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    private int oldPosition = 0;
    private ViewPagerAdapter pagerAdapter;
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    private ScheduledExecutorService scheduledExecutorService;

    private int[] imageIds = new int[]{
            R.mipmap.banner1,
            R.mipmap.banner2,
            R.mipmap.banner3
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page,null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        linear_order_send = view.findViewById(R.id.linear_order_send);
        linear_order_send.setOnClickListener(this);
        linear_freight = view.findViewById(R.id.linear_freight);
        linear_freight.setOnClickListener(this);
        linear_vip = view.findViewById(R.id.linear_vip);
        linear_vip.setOnClickListener(this);
        linear_contact_CS = view.findViewById(R.id.linear_contact_CS);
        linear_contact_CS.setOnClickListener(this);
        tv_uncollected_package = view.findViewById(R.id.tv_uncollected_package);
        tv_uncollected_package.setOnClickListener(this);
        tv_finish_send = view.findViewById(R.id.tv_finish_send);
        tv_finish_send.setOnClickListener(this);
        img_scan = view.findViewById(R.id.img_scan);
        img_scan.setOnClickListener(this);
        img_search = view.findViewById(R.id.img_search);
        img_search.setOnClickListener(this);
        et_search = view.findViewById(R.id.et_search);
        et_search.setOnClickListener(this);
        et_search.setFocusable(false);
        et_search.setClickable(true);
        listView = view.findViewById(R.id.main_page_list_view);
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter(new MainPageOrderListAdapter(getActivity(),qianshouimg,track_number,state,time));
        images = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        dots = new ArrayList<>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));

        banner = view.findViewById(R.id.banner);
        pagerAdapter = new ViewPagerAdapter();
        banner.setAdapter(pagerAdapter);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.mipmap.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.mipmap.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
     */
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(),2,2, TimeUnit.SECONDS);
    }

    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            banner.setCurrentItem(currentItem);
        };
    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_order_send:
                startActivity(new Intent(getActivity(), SchoolActivity.class));
                break;
            case R.id.linear_freight:
                startActivity(new Intent(getActivity(), FreightActivity.class));
                break;
            case R.id.linear_contact_CS:
                startActivity(new Intent(getActivity(), CSActivity.class));
                break;
            case R.id.linear_vip:
                startActivity(new Intent(getActivity(), VipsActivity.class));
                break;
            case R.id.tv_uncollected_package:
                startActivity(new Intent(getActivity(), UnCollectedActivity.class));
                break;
            case R.id.tv_finish_send:
                startActivity(new Intent(getActivity(), CompleteActivity.class));
                break;
            case R.id.img_scan:
                startQrCode();
                break;
            case R.id.img_search:

                break;
            case R.id.et_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivity(intent);
    }

}
