package com.example.peng.express.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peng.express.Activity.AboutActivity;
import com.example.peng.express.Activity.CheckSchoolCourierActivity;
import com.example.peng.express.Activity.SchoolCourierActivity;
import com.example.peng.express.Bean.User;
import com.example.peng.express.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

import static com.example.peng.express.Activity.LoginActivity.IP;

public class MainSelfFragment extends Fragment implements View.OnClickListener{
    private TextView school_courier,username;
    private Intent intent;
    private LinearLayout line_about;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myself,null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        line_about = view.findViewById(R.id.line_about);
        line_about.setOnClickListener(this);
        intent = new Intent();
        school_courier = view.findViewById(R.id.school_courier);
        school_courier.setOnClickListener(this);
        username = view.findViewById(R.id.username);
        Bundle users = getArguments();
        User user = (User) users.getSerializable("user");
        username.setText(user.getUsername());
        if (isSC(user.getUser_type())){
            intent.setClass(getActivity(),SchoolCourierActivity.class);
        }else{
            intent.setClass(getActivity(),CheckSchoolCourierActivity.class);
        }
    }

    private boolean isSC(String user_type) {
        if (user_type.equals("0")){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.school_courier:
                startActivity(intent);
                break;
        }
        switch (v.getId()){
            case R.id.line_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }
}
