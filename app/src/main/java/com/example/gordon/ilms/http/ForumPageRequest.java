package com.example.gordon.ilms.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by gordon on 9/30/15.
 */
public class ForumPageRequest extends BaseRequest<Integer> {

    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=forumlist";

    public ForumPageRequest(Course course, Response.Listener<Integer> listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, course.getId()), listener, errorListener);
    }

    @Override
    protected Response<Integer> parseNetworkResponse(NetworkResponse response) {
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);

        Elements elements = document.select(".page span");
        int page = elements.size() - 2;
        if (page <= 0)
            page = 1;

        return Response.success(page, HttpHeaderParser.parseCacheHeaders(response));
    }
}
