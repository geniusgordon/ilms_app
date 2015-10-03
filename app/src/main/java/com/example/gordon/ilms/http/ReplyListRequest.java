package com.example.gordon.ilms.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Post;
import com.example.gordon.ilms.model.Reply;
import com.example.gordon.ilms.model.ReplyList;

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
public class ReplyListRequest extends BaseRequest<ReplyList> {
    final static String URL = "http://lms.nthu.edu.tw/sys/lib/ajax/post.php";
    final static String POST_URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=forum&tid=%s";
    final static String LOG_TAG = "ReplyListRequest";

    private Post post;
    private Course course;

    public ReplyListRequest(Course course, Post post, Response.Listener<ReplyList> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        this.post = post;
        this.course = course;
    }

    @Override
    protected ReplyList parseResponseHtml(String responseHtml) {
        ReplyList replyList = new ReplyList();
        List<Reply> replies = new ArrayList<Reply>();

        try {
            JSONObject postJson = new JSONObject(responseHtml).getJSONObject("posts");
            replyList.setTitle(postJson.getString("title"));

            JSONArray itemsJson = postJson.getJSONArray("items");
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
            return null;
        }

        replyList.setReplies(replies);

        return replyList;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(post.getId()));
        return params;
    }

    @Override
    public String getOpenUrl() {
        return String.format(POST_URL, course.getId(), post.getId());
    }
}
