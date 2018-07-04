package com.example.peng.express.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peng.express.Activity.AboutActivity;
import com.example.peng.express.Activity.CheckSchoolCourierActivity;
import com.example.peng.express.Activity.SchoolCourierActivity;
import com.example.peng.express.Activity.SetActivity;
import com.example.peng.express.Activity.WriteUserInfoActivity;
import com.example.peng.express.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.example.peng.express.Activity.LoginActivity.IP;
import static com.example.peng.express.Activity.WriteUserInfoActivity.JSON;

public class MySelfFragment extends Fragment implements View.OnClickListener {
    private TextView school_courier;
    private Intent intent;
    private LinearLayout line_about, line_set, line_modify;
    private ImageView headImage;
    private String headPicture;
    private String phone;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, null);
        initView(view);
        getImageBitmap();
        return view;

    }

    private void initView(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String userType = sharedPreferences.getString("userType", "0");
        phone = sharedPreferences.getString("phone",null);
        headImage = view.findViewById(R.id.headImage);
        headImage.setOnClickListener(this);
        line_modify = view.findViewById(R.id.line_modify);
        line_modify.setOnClickListener(this);
        line_about = view.findViewById(R.id.line_about);
        line_about.setOnClickListener(this);
        line_set = view.findViewById(R.id.line_setting);
        line_set.setOnClickListener(this);
        intent = new Intent();
        school_courier = view.findViewById(R.id.school_courier);
        school_courier.setOnClickListener(this);

        if (isSC(userType)) {
            intent.setClass(getActivity(), SchoolCourierActivity.class);
        } else {
            intent.setClass(getActivity(), CheckSchoolCourierActivity.class);
        }
    }

    private void getImageBitmap(){
        Log.i(TAG, "getImageBitmap: phone=  "+phone);
        OkHttpUtils.get()
                .url(IP+"findUserImg")
                .addParams("phone",phone)
                .addHeader("Connection", "close")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String base64 = jsonObject.optString("head");
                        byte[] bytes = Base64.decode(base64,Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        headImage.setImageBitmap(bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isSC(String user_type) {
        if (user_type.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.school_courier:
                startActivity(intent);
                break;
            case R.id.line_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.line_setting:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
            case R.id.line_modify:
                startActivity(new Intent(getActivity(), WriteUserInfoActivity.class));
                break;
            case R.id.headImage:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 0x004);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        switch (requestCode) {
            case 0x004:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    new Thread(){
                                @Override
                                public void run() {
                                    //通过uri的方式返回，部分手机uri可能为空
                                    if (uri != null) {
                                        try {
                                            //通过uri获取到bitmap对象
                                            final Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                                            headImage.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    headImage.setImageBitmap(bitmap);
                                                }
                                            });
                                            edit_headPicture(phone,bitmap);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        //部分手机可能直接存放在bundle中
                                        Bundle bundleExtras = data.getExtras();
                                        if (bundleExtras != null) {
                                            Bitmap bitmaps = bundleExtras.getParcelable("data");
                                            headImage.setImageBitmap(bitmaps);
                                        }
                                    }
                                }
                            }.start();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void edit_headPicture(String phone,Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//将Bitmap转成Byte[]
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);//压缩
        Log.i(TAG, "edit_headPicture: size "+bitmap.getByteCount());
        headPicture = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);//加密转换成String;
        int n = headPicture.length();
        Log.i(TAG, "edit_headPicture: the length of the headPicture"+n);
        try {
            JSONObject json = new JSONObject();
            json.put("headPicture",headPicture);
            postToServer(phone,json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void postToServer(String phone,Object object) {
        RequestBody body = RequestBody.create(JSON, String.valueOf(object));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(IP + "updateUserImage?phone="+phone)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String result = jsonObject.optString("msg");
                    if (result.equals("1")) {
                        //Toast 必须在主线程中进行
                        //Toast 显示需要出现在一个线程的消息队列中.... 很隐蔽
                        //因为toast的实现需要在activity的主线程才能正常工作，
                        // 所以传统的非主线程不能使toast显示在actvity上，通过Handler可以使自定义线程运行于Ui主线程
//                        Looper.prepare();
////                        Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_LONG).show();
//                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
