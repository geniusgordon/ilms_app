package com.example.gordon.ilms.http.main;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.http.BaseRequest;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.HomeItem;
import com.example.gordon.ilms.model.Preferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 9/30/15.
 */
public class HomeItemListRequest extends BaseRequest<List<HomeItem>> {

    final static String URL = "http://lms.nthu.edu.tw/home.php";
    final static String BASE_URL = "http://lms.nthu.edu.tw";
    final static String LOG_TAG = "HomeItemRequest";

    protected int[] types = {
        HomeItem.ANNOUNCE,
        HomeItem.FORUM,
        HomeItem.MATERIAL
    };

    protected String[] cssSelect = {
        "#right > div:nth-child(4) > div:nth-child(2) > div.BlockR",
        "#right > div:nth-child(4) > div:nth-child(1) > div.BlockL",
        "#right > div:nth-child(4) > div:nth-child(2) > div.BlockL",
    };

    public HomeItemListRequest(Response.Listener<List<HomeItem>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
    }

    @Override
    protected List<HomeItem> parseResponseHtml(String responseHtml) {
        List<HomeItem> homeItems = new ArrayList<HomeItem>();
        Document document = Jsoup.parse(responseHtml);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < cssSelect.length; i++) {
            Log.d(LOG_TAG, String.valueOf(types[i]));
            String select = cssSelect[i];
            Elements block = document.select(select);
            String title = block.select("div.em.itemRect").text();
            String more = block.select("div.em.itemRect span").text();
            title = title.replace(more, "");
            homeItems.add(new HomeItem(title));

            for (Element element: block.select("div.BlockItem")) {
                title = element.select("a").eq(0).text().trim();
                String course = element.select("a").eq(1).text().trim();
                String path = element.select("a").eq(0).attr("href");
                String url;
                if (path.startsWith("javascript")) {
                    long courseId = Preferences.getInstance().getCourseIdByName(Course.splitTitle(course)[0]);
                    String announcementId = path.split("\\(")[1].split("\\)")[0];
                    url = String.format("ilms://announcement/%s/%s?title=%s", courseId, announcementId, title);
                } else {
                    url = BASE_URL + path;
                }
                String dateStr = element.select(".hint").attr("title");
                Date date = null;
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Log.d(LOG_TAG, title);
                //Log.d(LOG_TAG, url);

                homeItems.add(new HomeItem(types[i], title, course, url, date));
            }
        }

        if (homeItems.size() == cssSelect.length)
            homeItems.clear();

        return homeItems;
    }
}
