package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.HomeItem;

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
    final static String LOG_TAG = "HomeItemRequest";

    final static String[] cssSelect = {
        "#right > div:nth-child(4) > div:nth-child(1) > div.BlockL",
        "#right > div:nth-child(4) > div:nth-child(1) > div.BlockR",
        "#right > div:nth-child(4) > div:nth-child(2) > div.BlockL",
        "#right > div:nth-child(4) > div:nth-child(2) > div.BlockR"
    };

    public HomeItemListRequest(Response.Listener<List<HomeItem>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
    }

    @Override
    protected Response<List<HomeItem>> parseNetworkResponse(NetworkResponse response) {
        List<HomeItem> homeItems = new ArrayList<HomeItem>();
        String resonseHtml = new String(response.data);
        Document document = Jsoup.parse(resonseHtml);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (String select: cssSelect) {
            Elements block = document.select(select);
            String title = block.select("div.em.itemRect").text();
            String more = block.select("div.em.itemRect span").text();
            title = title.replace(more, "");
            homeItems.add(new HomeItem(title));

            for (Element element: block.select("div.BlockItem")) {
                title = element.select("a").eq(0).text().trim();
                String url = URL + element.select("a").eq(0).attr("href");
                String course = element.select("a").eq(1).text().trim();
                String dateStr = element.select(".hint").attr("title");
                Date date = null;
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                homeItems.add(new HomeItem(title, course, url, date));
            }
        }

        for (HomeItem item: homeItems) {
            Log.d(LOG_TAG, item.getTitle());
        }

        return Response.success(homeItems, HttpHeaderParser.parseCacheHeaders(response));
    }
}
