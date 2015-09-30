package com.example.gordon.ilms.http;

import com.android.volley.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gordon on 10/1/15.
 */
public class NewPostRequest extends MultipartRequest {

    final static String URL = "http://lms.nthu.edu.tw/post_insert.php";

    public NewPostRequest(Map<String, String> params, Map<String, File> files, Response.Listener listener, Response.ErrorListener errorListener) {
        super(URL, params, files, listener, errorListener);
        params.put("MAX_FILE_SIZE", "104857600");
        params.put("hint", "0");
        params.put("fmSubmit", "yes");
        params.put("action", "post");
    }
}
