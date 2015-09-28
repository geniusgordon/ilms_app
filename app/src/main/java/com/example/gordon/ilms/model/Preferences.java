package com.example.gordon.ilms.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gordon on 9/28/15.
 */
public class Preferences {
    private static Preferences mInstance;
    private static Context mContext;
    private SharedPreferences settings;

    private Preferences(Context context) {
        mContext = context;
        settings = context.getSharedPreferences("ilms", 0);
    }

    public static synchronized Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context.getApplicationContext());
        }
        return mInstance;
    }

    public static synchronized Preferences getInstance() {
        return mInstance;
    }

    public void saveAccount(Account account) {
        settings.edit()
                .putString("studentId", account.getStudentId())
                .putString("email", account.getEmail())
                .apply();
        Log.d("Preferences", "save account " + account.getStudentId());
    }

    public Account getAccount() {
        Account account = new Account();
        account.setStudentId(settings.getString("studentId", ""));
        account.setEmail(settings.getString("email", ""));
        if (account.getStudentId().equals("")) {
            Log.d("Preferences", "get account null");
            return null;
        }
        Log.d("Preferences", "get account " + account.getStudentId());
        return account;
    }

    public void saveCookie(String cookie) {
        if (cookie == null)
            return;
        settings.edit()
                .putString("cookie", cookie)
                .apply();
    }

    public String getCookie() {
        String cookie = settings.getString("cookie", "");
        return cookie;
    }
}
