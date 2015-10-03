package com.example.gordon.ilms.http;

import android.widget.BaseAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Preferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */
public abstract class BaseRequest<T> extends Request<T> {

    protected Response.Listener<T> mListener;
    protected String url;

    public BaseRequest(int method, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.url = url;
    }

    abstract protected T parseResponseHtml(String responseHtml);

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        T parsed = parseResponseHtml(new String(response.data));
        if (parsed == null)
            return Response.error(new VolleyError());
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        Preferences prefs = Preferences.getInstance();
        if (prefs != null) {
            String cookie = prefs.getCookie();
            if (!cookie.equals(""))
                headers.put("cookie", cookie);
        }
        return headers;
    }

    public String getOpenUrl() {
        return this.url;
    }
}
