package com.example.peng.express.Test;

import com.example.peng.express.Bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckLogin {
    public static User checkLogin (String username,String password){
        User user = null;

        try {
            URL url = new URL("");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",username);
            jsonObject.put("password",password);
            jsonArray.put(jsonObject);

            bufferedWriter.write(jsonArray.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String userJson = br.readLine();

            if (!userJson.equals("error")) {
                JSONArray userJsonArray = new JSONArray(userJson);
                JSONObject userJsonObject = userJsonArray.getJSONObject(0);
//                user = new User();
//                user.setId(userJsonObject.getInt("id"));
//                user.setName(userJsonObject.getString("username"));
//                user.setPassword(userJsonObject.getString("password"));
            }
            br.close();
            connection.disconnect();

        }  catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
