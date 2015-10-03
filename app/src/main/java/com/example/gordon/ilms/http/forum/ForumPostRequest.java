package com.example.gordon.ilms.http.forum;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.example.gordon.ilms.http.MultipartRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gordon on 10/1/15.
 */
public class ForumPostRequest extends MultipartRequest {

    final static String URL = "http://lms.nthu.edu.tw/post_insert.php";
//    final static String URL = "http://httpbin.org/post";

    public ForumPostRequest(String action, String id, Map<String, String> params, Map<String, File> files, Response.Listener listener, Response.ErrorListener errorListener) {
        super(URL, params, files, listener, errorListener);
        params.put("MAX_FILE_SIZE", "104857600");
        params.put("hint", "0");
        params.put("fmSubmit", "yes");
        params.put("action", action);
        params.put("id", id);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        Log.d(LOG_TAG, new String(response.data));
        return super.parseNetworkResponse(response);
    }
}
