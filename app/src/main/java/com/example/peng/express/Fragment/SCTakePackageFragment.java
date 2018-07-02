package com.example.peng.express.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.example.peng.express.Activity.ScOrderDetialActivity;
import com.example.peng.express.Bean.SchoolOrder;
import com.example.peng.express.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private EditText username,track_number,express_company,phone,take_add,address,time;
    private Button commit;
    private ProgressDialog dlg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_take_package,null);
        initView(view);

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScOrderDetialActivity.class);
                intent.putExtra("details",getData());
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        username = view.findViewById(R.id.username);
        track_number = view.findViewById(R.id.track_number);
        express_company = view.findViewById(R.id.express_company);
        phone = view.findViewById(R.id.phone);
        take_add = view.findViewById(R.id.take_add);
        address = view.findViewById(R.id.address);
        commit = view.findViewById(R.id.commit);
        time = view.findViewById(R.id.time);
    }

    private String getData(){
        String user = username.getText().toString();
        String number = track_number.getText().toString();
        String company = express_company.getText().toString();
        String phones = phone.getText().toString();
        String take = take_add.getText().toString();
        String add = address.getText().toString();
        String remarks = time.getText().toString();

        SchoolOrder.Body so = new SchoolOrder.Body(user,phones,number,company,take,add,remarks);
        List<SchoolOrder.Body> bodyList = new ArrayList<>();
        bodyList.add(so);

        Gson gson = new Gson();
        String json = gson.toJson(bodyList);
        return json;
    }
}
