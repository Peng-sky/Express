package com.example.peng.express.Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.peng.express.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;


public class TestSocket extends AppCompatActivity implements View.OnClickListener {
    private EditText et_message;
    private TextView textView;
    private Button btn_conn, btn_send, btn_disconnect;
    private static final String TAG = "TAG";
    //    "192.168.23.1";
    private static final String HOST = "172.17.171.9";
    private static final int PORT = 9999;
    private BufferedSink mSink;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private BufferedSource mSource;
    private String receiveMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket);

        initView();
    }

    private void initView() {
        et_message = findViewById(R.id.et_message);
        textView = findViewById(R.id.tv_msg);
        btn_conn = findViewById(R.id.btn_conn);
        btn_send = findViewById(R.id.btn_send);
        btn_disconnect = findViewById(R.id.btn_disconnect);
        btn_conn.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_disconnect.setOnClickListener(this);
        mExecutorService = Executors.newCachedThreadPool();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_conn:
                mExecutorService.execute(new connectService());
                break;
            case R.id.btn_send:
                String sendMsg = et_message.getText().toString();
                mExecutorService.execute(new sendService(sendMsg));
                break;
            case R.id.btn_disconnect:
                mExecutorService.execute(new sendService("0"));
                break;
        }
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                mSink.writeUtf8(this.msg + "\n");
                System.out.println(msg);
                mSink.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(HOST, PORT);
                mSink = Okio.buffer(Okio.sink(socket));
                mSource = Okio.buffer(Okio.source(socket));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {
                for (String receiveMsg; (receiveMsg = mSource.readUtf8Line()) != null; ) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    final String finalReceiveMsg = receiveMsg;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(finalReceiveMsg + "\n\n" + textView.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}

