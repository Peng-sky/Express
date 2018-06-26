package com.example.peng.express.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.peng.express.Dialog.DiscountDialog;
import com.example.peng.express.Dialog.VolumeDialog;
import com.example.peng.express.Dialog.WalletDialog;
import com.example.peng.express.R;

public class VipsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView wallet,volume,discount,record;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vips);
        initView();
    }

    private void initView() {
        wallet = findViewById(R.id.wallet);
        wallet.setOnClickListener(this);
        volume = findViewById(R.id.volume);
        volume.setOnClickListener(this);
        discount = findViewById(R.id.discount);
        discount.setOnClickListener(this);
        record = findViewById(R.id.record);
        record.setOnClickListener(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wallet:
                WalletDialog walletDialog = new WalletDialog(VipsActivity.this);
                walletDialog.show();
                break;
            case R.id.volume:
                VolumeDialog volumeDialog = new VolumeDialog(VipsActivity.this);
                volumeDialog.show();
                break;
            case R.id.discount:
                DiscountDialog discountDialog = new DiscountDialog(VipsActivity.this);
                discountDialog.show();
                break;
            case R.id.record:

                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
