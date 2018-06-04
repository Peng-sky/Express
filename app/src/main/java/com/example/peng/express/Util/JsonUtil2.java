package com.example.peng.express.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil2 {

    /**
     * 解析Json数据
     * @param key
     * @param json
     * @return
     */
    public static int getResult(String key,String json){

        try {
            JSONObject jsonObject=new JSONObject(json);
            int result=jsonObject.getInt(key);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean isExist(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String result = jsonObject.optString("error");
            if (result!=null){
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

}

