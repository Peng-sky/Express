package com.example.peng.express.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.peng.express.Activity.AboutActivity;
import com.example.peng.express.R;

public class MainSelfFragment extends Fragment implements View.OnClickListener{
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }
}
