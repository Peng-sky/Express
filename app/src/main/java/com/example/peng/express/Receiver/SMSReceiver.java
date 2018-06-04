package com.example.peng.express.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] object = (Object[]) intent.getExtras().get("pdus");
        StringBuilder sb = new StringBuilder();
        for (Object pdu : object) {
            byte[] pdusMsg=(byte[]) pdu;
            SmsMessage sms=SmsMessage.createFromPdu(pdusMsg);
            String mobile=sms.getOriginatingAddress();//发送短信的手机号
            String content=sms.getMessageBody();//短信内容
            //下面是获取短信的发送时间
            Date date=new Date(sms.getTimestampMillis());
            String date_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            //追加到StringBuilder中
            sb.append("短信发送号码："+mobile+"\n短信内容："+content+"\n发送时间："+date_time+"\n\n");
            }
            System.out.println(sb);
    }
}
