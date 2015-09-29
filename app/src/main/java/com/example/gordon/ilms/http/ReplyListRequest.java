package com.example.gordon.ilms.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Post;
import com.example.gordon.ilms.model.Reply;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/29/15.
 */
public class ReplyListRequest extends BaseRequest<List<Reply>> {
    final static String URL = "http://lms.nthu.edu.tw/sys/lib/ajax/post.php";
    final static String LOG_TAG = "ReplyListRequest";

    private Post post;

    public ReplyListRequest(Post post, Response.Listener<List<Reply>> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        this.post = post;
    }

    @Override
    protected Response<List<Reply>> parseNetworkResponse(NetworkResponse response) {
        List<Reply> replies = new ArrayList<Reply>();
        String responseHtml = new String(response.data);

        try {
            JSONArray itemsJson = new JSONObject(responseHtml).getJSONObject("posts").getJSONArray("items");
            for (int i = 0; i < itemsJson.length(); i++) {
                JSONObject replyJson = itemsJson.getJSONObject(i);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Reply reply = new Reply();
                reply.setId(replyJson.getLong("id"));
                reply.setName(replyJson.getString("name"));
                reply.setAccount(replyJson.getString("account"));
                reply.setEmail(replyJson.getString("email"));
                reply.setFloor(replyJson.getLong("floor"));
                try {
                    reply.setTime(df.parse(replyJson.getString("date")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                reply.setContent(replyJson.getString("note"));

                JSONArray attachJsonArray = replyJson.getJSONArray("attach");
                for (int j = 0; j < attachJsonArray.length(); j++) {
                    JSONObject attachJson = attachJsonArray.getJSONObject(j);
                    Attachment attachment = new Attachment();
                    attachment.setId(attachJson.getLong("id"));
                    attachment.setTitle(attachJson.getString("srcName"));
                    attachment.setSize(attachJson.getLong("fileSize"));

                    reply.addAttachment(attachment);
                }

                replies.add(reply);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new VolleyError("Json error"));
        }

        return Response.success(replies, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(post.getId()));
        return params;
    }
}
