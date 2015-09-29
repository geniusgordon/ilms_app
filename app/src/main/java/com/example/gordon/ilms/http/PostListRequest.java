package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Homework;
import com.example.gordon.ilms.model.Post;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/29/15.
 */
public class PostListRequest extends BaseRequest<List<Post>> {

    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=forumlist&page=%d";
    final static String LOG_TAG = "PostListRequest";

    private Course course;

    public PostListRequest(Course course, int page, Response.Listener<List<Post>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, course.getId(), page), listener, errorListener);
        this.course = course;
    }

    @Override
    protected Response<List<Post>> parseNetworkResponse(NetworkResponse response) {
        List<Post> posts = new ArrayList<Post>();
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);

        Elements tr = document.select("tr");
        for (int i = 1; i < tr.size(); i++) {
            if (i%2 == 0)
                continue;

            Elements td = tr.eq(i).select("td");
            if (tr.size() == 2 && td.size() == 1)
                return Response.success(posts, HttpHeaderParser.parseCacheHeaders(response));

            DateFormat df = new SimpleDateFormat("MM-dd hh:mm");

            Post post = new Post();
            post.setId(Long.parseLong(td.eq(0).text()));
            post.setTitle(td.eq(1).select("a").eq(0).text().trim());
            post.setCount(Long.parseLong(td.eq(2).select("span").eq(0).text()));

            String last = td.eq(3).text().trim();
            try {
                post.setLastTime(df.parse(last.substring(1, 12)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            post.setLastName(last.substring(17));

//            Log.d(LOG_TAG, String.valueOf(post.getCount()));
//            Log.d(LOG_TAG, post.getTitle());
//            Log.d(LOG_TAG, String.valueOf(post.getId()));
//            Log.d(LOG_TAG, df.format(post.getLastTime()));
//            Log.d(LOG_TAG, post.getLastName());

            posts.add(post);
        }

        return Response.success(posts, HttpHeaderParser.parseCacheHeaders(response));
    }
}
