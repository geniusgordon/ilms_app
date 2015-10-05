package com.geniusgordon.ilms.http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.geniusgordon.ilms.model.LoginStatus;
import com.geniusgordon.ilms.model.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gordon on 10/6/15.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, LoginStatus> {

    static String urlString = "http://lms.nthu.edu.tw/sys/lib/ajax/login_submit.php";
    static String LOG_TAG = "LoginAsyncTask";

    @Override
    protected LoginStatus doInBackground(String... params) {
        try {
            HashMap<String, String> postParams = new HashMap<String, String>();
            postParams.put("account", params[0]);
            postParams.put("password", params[1]);
            Set set = postParams.entrySet();
            Iterator i = set.iterator();
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : postParams.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                builder.append(line).append("\n");
            }
            reader.close();
            conn.disconnect();

            Map<String, List<String>> map = conn.getHeaderFields();

            StringBuilder cookieBuilder = new StringBuilder();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                if (entry.getKey() == null)
                    continue;
                if (entry.getKey().equals("Set-Cookie")) {
                    for (String s: entry.getValue()) {
                        cookieBuilder.append(s);
                        cookieBuilder.append(';');
                    }

//                    Preferences.getInstance().saveCookie();
                }
            }
//            Log.d(LOG_TAG, cookieBuilder.toString());
            Preferences.getInstance().saveCookie(cookieBuilder.toString());

            LoginStatus status = new LoginStatus();
            String parsed = builder.toString();

            Log.d(LOG_TAG, parsed);

            JSONObject ret = new JSONObject(parsed).getJSONObject("ret");
            status.setStatus(ret.getBoolean("status"));
            if (status.isStatus()) {
                status.setEmail(ret.getString("email"));
            } else {
                status.setMsg(ret.getString("msg"));
            }
            return status;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
