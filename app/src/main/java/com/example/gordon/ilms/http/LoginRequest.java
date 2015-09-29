package com.example.gordon.ilms.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.LoginStatus;
import com.example.gordon.ilms.model.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */
public class LoginRequest extends Request<LoginStatus> {
    final static String URL = "http://lms.nthu.edu.tw/sys/lib/ajax/login_submit.php";

    private Response.Listener mListener;
    private String username;
    private String password;

    public LoginRequest(String username, String password, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, errorListener);
        this.username = username;
        this.password = password;
        this.mListener = listener;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("account", username);
        params.put("password", password);
        params.put("stay", "1");
        return params;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    protected void deliverResponse(LoginStatus response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<LoginStatus> parseNetworkResponse(NetworkResponse response) {
        Map headers = response.headers;
        String cookie = (String) headers.get("Set-Cookie");
        Preferences prefs = Preferences.getInstance();
        if (prefs != null)
            prefs.saveCookie(cookie);

        String parsed;
        LoginStatus status = new LoginStatus();
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        try {
            JSONObject ret = new JSONObject(parsed).getJSONObject("ret");
            status.setStatus(ret.getBoolean("status"));
            if (status.isStatus()) {
                status.setEmail(ret.getString("email"));
            } else {
                status.setMsg(ret.getString("msg"));
                return Response.error(new VolleyError(status.getMsg()));
            }
        } catch (JSONException e) {
            return Response.error(new ParseError());
        }
        return Response.success(status, HttpHeaderParser.parseCacheHeaders(response));
    }

}
