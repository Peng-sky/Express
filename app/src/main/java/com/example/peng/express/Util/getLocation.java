package com.example.peng.express.Util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peng.express.Activity.SenderAddressActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class getLocation {
    public getLocation(Context context,EditText et_region,EditText et_detail_address){
//        if (ActivityCompat.checkSelfPermission(SenderAddressActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(SenderAddressActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(SenderAddressActivity.this, "权限不够", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                System.out.println(location)
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "权限不够", Toast.LENGTH_LONG).show();
            return;
        }

        //设置位置查询条件，通过criteria返回符合条件的provider,有可能是wifi provider,也有可能是gps provider
        Criteria criteria =new Criteria(); //创建一个Criteria对象
        criteria.setAccuracy(Criteria.ACCURACY_COARSE); //设置精度,模糊模式,对于DTV地区定位足够了；ACCURACY_FINE,精确模式
        criteria.setAltitudeRequired(false); //设置是否需要返回海拔信息,不要求海拔
        criteria.setBearingRequired(false); //设置是否需要返回方位信息，不要求方位
        criteria.setCostAllowed(true); //设置是否允许付费服
        criteria.setPowerRequirement(Criteria.POWER_LOW); //设置电量消耗等级
        criteria.setSpeedRequired(false); //设置是否需要返回速度信息
        //根据设置的Criteria对象，获取最符合此标准的provider对象
        String provider = locationManager.getBestProvider(criteria, true);

        Log.d("Location", "provider: "+ provider);
        //根据当前provider对象获取最后一次位置信息
        Location currentLocation = locationManager.getLastKnownLocation(provider);
        //如果位置信息为null，则请求更新位置信息
        if(currentLocation ==null){
            locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        }
        //直到获得最后一次位置信息为止，如果未获得最后一次位置信息，则显示默认经纬度
        //每隔10秒获取一次位置信息
        while(true){
            currentLocation = locationManager.getLastKnownLocation(provider);
            if(currentLocation !=null){
                Log.d("Location", "Latitude: "+ currentLocation.getLatitude());
                Log.d("Location", "location: "+ currentLocation.getLongitude());
                //长时间的监听位置更新可能导致耗电量急剧上升,一旦获取到位置后，就停止监听
                locationManager.removeUpdates(locationListener);
                break;
            }else{
                Log.d("Location", "Latitude: "+0);
                Log.d("Location", "location: "+0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e("Location", e.getMessage());
            }
        }
        //解析地址并显示
        Geocoder geoCoder =new Geocoder(context, Locale.getDefault());
        List<Address> list = null;
        try {
            double latitude = currentLocation.getLatitude();
            double longitude = currentLocation.getLongitude();
            list = geoCoder.getFromLocation(latitude, longitude, 1);
            if(list!=null && !list.isEmpty()){
                //取第一个地址就可以
                Address address = list.get(0);
                //getCountryName 国家
                //getAdminArea 省份
                //getLocality 城市
                //getSubLocality 区
                //getFeatureName 街道
                //Toast.makeText(SenderAddressActivity.this, address.getCountryName() + address.getAdminArea() + address.getLocality()  + address.getSubLocality() + address.getFeatureName(), Toast.LENGTH_LONG).show();
                String Feat = address.getAddressLine(0)+address.getAddressLine(1)+address.getAddressLine(2);
                System.out.println("Feat="+Feat);
                String APU= Feat.substring(0,Feat.indexOf("区")+1);
                String details = Feat.substring(Feat.indexOf("区")+1,Feat.length());
                System.out.println("AUP"+APU);
                System.out.println(details);
                et_region.setText(APU);
                et_detail_address.setText(details);
            }
        }
        catch (IOException e) {
            Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private LocationListener locationListener =new LocationListener(){
        //位置发生改变时调用
        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location", "onLocationChanged");
            Log.d("Location", "onLocationChanged Latitude"+ location.getLatitude());
            Log.d("Location", "onLocationChanged location"+ location.getLongitude());
        }

        //provider失效时调用
        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Location", "onProviderDisabled");
        }

        //provider启用时调用
        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Location", "onProviderEnabled");
        }

        //状态改变时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Location", "onStatusChanged");
        }
    };
}
