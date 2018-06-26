package com.example.peng.express.Fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;
import static com.example.peng.express.Activity.WriteUserInfoActivity.JSON;

public class SCTakePackageFragment extends Fragment {
    private EditText username,track_number,express_company,phone,address,time;
    private Button commit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_take_package,null);
        initView(view);

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrderToService(getData());
            }
        });

        return view;
    }

    private void sendOrderToService(final String json) {
        RequestBody body = RequestBody.create(JSON,json);
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(IP+"insetSC")
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
                        Looper.prepare();
                        Toast.makeText(getActivity(),"订单创建成功",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView(View view) {
        username = view.findViewById(R.id.username);
        track_number = view.findViewById(R.id.track_number);
        express_company = view.findViewById(R.id.express_company);
        phone = view.findViewById(R.id.phone);
        address = view.findViewById(R.id.address);
        commit = view.findViewById(R.id.commit);
        time = view.findViewById(R.id.time);
    }

    private String getData(){
        String user = username.getText().toString();
        String number = track_number.getText().toString();
        String company = express_company.getText().toString();
        String phones = phone.getText().toString();
        String commits = commit.getText().toString();
        String remarks = time.getText().toString();
        SchoolOrder.Body so = new SchoolOrder.Body(user,phones,number,company,commits,remarks);
        Gson gson = new Gson();
        String json = gson.toJson(so);
        return json;
    }
}
