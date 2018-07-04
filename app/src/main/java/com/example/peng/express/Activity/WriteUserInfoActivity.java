package com.example.peng.express.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.peng.express.Bean.Order;
import com.example.peng.express.Bean.User;
import com.example.peng.express.R;
import com.example.peng.express.Util.CityPick;
import com.example.peng.express.Util.getLocation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.peng.express.Activity.LoginActivity.IP;
import static com.igexin.push.config.SDKUrlConfig.getLocation;

public class WriteUserInfoActivity extends AppCompatActivity {
    private EditText et_info_phone_num, et_info_username, et_info_birthday, et_info_email, et_info_province, et_info_address;
    private Button btn_info_confirm;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Intent intent;
    private User user;
    private ProgressDialog dlg;
    private ImageView headImage,img_get_location;
    private Calendar cal;
    private int year,month,day;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_user_info);
        initView();
        initData(et_info_phone_num.getText().toString());
        getDate();
        btn_info_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = getUserInfo();
                postJson(info);

            }
        });
    }


    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }


    private void initView() {
        img_get_location = findViewById(R.id.img_get_location);
        img_get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getLocation(WriteUserInfoActivity.this,et_info_province,et_info_address);
            }
        });
        headImage = findViewById(R.id.headImage);
        //设置电话号码不可输入
        et_info_phone_num = findViewById(R.id.et_info_phone_num);
        et_info_phone_num.setFocusable(false);
        et_info_phone_num.clearFocus();
        //用户名
        et_info_username = findViewById(R.id.et_info_username);
        //生日
        et_info_birthday = findViewById(R.id.et_info_birthday);
        et_info_birthday.setFocusable(false);
        et_info_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        et_info_birthday.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(WriteUserInfoActivity.this, android.R.style.Theme_Holo_Light_Dialog,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
        et_info_email = findViewById(R.id.et_info_email);
        et_info_province = findViewById(R.id.et_info_province);
        et_info_province.setFocusable(false);
        et_info_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CityPick(WriteUserInfoActivity.this,et_info_province);
            }
        });
        et_info_address = findViewById(R.id.et_info_address);
        btn_info_confirm = findViewById(R.id.btn_info_confirm);
        radioGroup = findViewById(R.id.rg);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone",null);
        et_info_phone_num.setText(phone);

    }

    private void initData(String phone){
        OkHttpUtils.get()
                .url(IP+"findUserInfo")
                .addParams("phone",phone)
                .addHeader("Connection", "close")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                User user = gson.fromJson(response,new TypeToken<User>(){}.getType());
                et_info_username.setText(user.getUsername());

                if (user.getSex().equals("男")){
                   radioGroup.check(R.id.male);
                }else{
                    radioGroup.check(R.id.female);
                }
                et_info_birthday.setText(user.getBirthday());
                et_info_email.setText(user.getEmail());
                et_info_province.setText(user.getAddressPUA());
                et_info_address.setText(user.getAddress_details());

            }
        });
    }

    private String getUserInfo() {
        intent = getIntent();
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonId);
        String phone = et_info_phone_num.getText().toString();
        String password = "不做修改，数据库不执行获取密码操作";
        String username = et_info_username.getText().toString();
        String birthday = et_info_birthday.getText().toString();
        String email = et_info_email.getText().toString();
        String province = et_info_province.getText().toString();
        String address = et_info_address.getText().toString();
        String sex = radioButton.getText().toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//将Bitmap转成Byte[]
        Bitmap bitmap = ((BitmapDrawable)headImage.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);//压缩
        String image = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);//加密转换成String;
        String user_type = "0";
        Gson gson = new Gson();
        User user = new User(phone,password, username, sex, birthday, email, province, address,image,user_type);
        String info = gson.toJson(user);
        System.out.println(info);
        Log.i(TAG, "getUserInfo: userInfo is "+info);
        return info;
    }

    private void postJson(String json){
        dlg = new ProgressDialog(this);
        dlg.setIcon(R.mipmap.logo);
        dlg.setMessage("加载中请稍后");
        dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置水平进度条
        dlg.show();
        RequestBody body = RequestBody.create(JSON,json);
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(IP+"updateUser")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String result = jsonObject.optString("msg");
                    if (result.equals("1")){
                        //Toast 必须在主线程中进行
                        //Toast 显示需要出现在一个线程的消息队列中.... 很隐蔽
                        //因为toast的实现需要在activity的主线程才能正常工作，
                        // 所以传统的非主线程不能使toast显示在actvity上，通过Handler可以使自定义线程运行于Ui主线程
                        Looper.prepare();
                        Toast.makeText(WriteUserInfoActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                        dlg.dismiss();
                        finish();
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
