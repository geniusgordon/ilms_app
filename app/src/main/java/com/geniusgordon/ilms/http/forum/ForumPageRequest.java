package com.geniusgordon.ilms.http.forum;

import com.android.volley.Response;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.model.Course;

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
    protected Integer parseResponseHtml(String responseHtml) {
        Document document = Jsoup.parse(responseHtml);

        Elements elements = document.select(".page span");
        int page = elements.size() - 2;
        if (page <= 0)
            page = 1;

        return page;
    }
}
