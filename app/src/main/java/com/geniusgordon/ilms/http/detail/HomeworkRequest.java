package com.geniusgordon.ilms.http.detail;

import com.android.volley.Response;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.model.Attachment;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.Homework;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/29/15.
 */
public class HomeworkRequest extends BaseRequest<Homework> {

    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=hw&hw=%s";
    final static String LOG_TAG = "HomeworkRequest";

    private Course course;
    private Homework homework;

    public HomeworkRequest(Course course, Homework homework, Response.Listener<Homework> listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, course.getId(), homework.getId()), listener, errorListener);
        this.course = course;
        this.homework = homework;
    }

    @Override
    protected Homework parseResponseHtml(String responseHtml) {
        Document document = Jsoup.parse(responseHtml);

        homework.setTitle(document.select("#main > div.infoPath > span.curr").text());

        Elements tr = document.select("tr");
        String timeStr = tr.eq(5).select("td").eq(1).text();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            homework.setTime(df.parse(timeStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        homework.setDescription(tr.eq(6).select("td").eq(1).html());

        homework.getAttachments().clear();
        Elements links = tr.eq(7).select("td").eq(1).select("a");
        for (int i = 0; i < links.size(); i++) {
            Element a = links.get(i);
            Attachment attachment = new Attachment();
            attachment.setId(Long.parseLong(a.attr("href").split("=")[1]));
            attachment.setTitle(a.html());
            attachment.setSize(tr.eq(7).select("td").eq(1).select("span").eq(i).html());

            homework.addAttachment(attachment);
        }

        return homework;
    }
}
