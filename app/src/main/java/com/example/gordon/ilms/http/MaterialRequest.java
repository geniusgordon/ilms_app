package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Material;

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
public class MaterialRequest extends BaseRequest<Material> {

    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=doc&cid=%s";
    final static String LOG_TAG = "MaterialRequest";

    private Course course;
    private Material material;

    public MaterialRequest(Course course, Material material, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, course.getId(), material.getId()), listener, errorListener);
        this.course = course;
        this.material = material;
    }

    @Override
    protected Response<Material> parseNetworkResponse(NetworkResponse response) {
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);

        material.setTitle(document.select("#doc > div.title").text().trim());
        String[] info = document.select("#doc > div.toolarea > div.poster").html().split(",");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            material.setAuthor(info[0].replace("by ", "").trim());

            try {
                material.setTime(df.parse(info[1].trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, material.getTitle());
        Log.d(LOG_TAG, material.getTimeStr(df));

        material.setContent(document.select(".doc .article").html());

        Elements elements = document.select("div.attach div.block");
        for (Element element: elements) {
            Attachment attachment = new Attachment();

            attachment.setId(Long.parseLong(element.select("a:first-child").attr("href").split("=")[1]));
            attachment.setTitle(element.select("a:first-child").attr("title"));
            attachment.setSize(element.select("span").html());

            material.addAttachment(attachment);
        }

        return Response.success(material, HttpHeaderParser.parseCacheHeaders(response));
    }
}
