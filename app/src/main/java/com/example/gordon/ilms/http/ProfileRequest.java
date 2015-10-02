package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Account;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by gordon on 10/2/15.
 */
public class ProfileRequest extends BaseRequest<Account> {

    final static String URL = "http://lms.nthu.edu.tw/home.php";
    final static String BASE_URL = "http://lms.nthu.edu.tw";
    final static String LOG_TAG = "ProfileRequest";

    public ProfileRequest(Response.Listener<Account> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
    }

    @Override
    protected Response<Account> parseNetworkResponse(NetworkResponse response) {
        Account account = new Account();

        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);
        Element profile = document.select("#profile").first();

        String avatarUrl = BASE_URL + profile.select("img").attr("src");
        account.setAvatarUrl(avatarUrl);

        int c;
        String info;

        info = profile.select("div:nth-child(2) > div:nth-child(1)").text();
        c = info.indexOf(":");
        account.setName(info.substring(c+1));

        info = profile.select("div:nth-child(2) > div:nth-child(2)").text();
        c = info.indexOf(":");
        account.setLastLogin(info.substring(c+1));

        info = profile.select("div:nth-child(2) > div:nth-child(3)").text();
        c = info.indexOf(":");
        account.setLoginCount(info.substring(c + 1));

        Log.d(LOG_TAG, account.getName());
        Log.d(LOG_TAG, account.getAvatarUrl());
        Log.d(LOG_TAG, account.getLoginCount());

        return Response.success(account, HttpHeaderParser.parseCacheHeaders(response));
    }
}
