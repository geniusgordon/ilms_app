package com.geniusgordon.ilms.http.main;

import com.android.volley.Response;
import com.geniusgordon.ilms.model.HomeItem;

import java.util.List;

/**
 * Created by gordon on 10/1/15.
 */
public class NewestForumRequest extends HomeItemListRequest {

    public NewestForumRequest(Response.Listener<List<HomeItem>> listener, Response.ErrorListener errorListener) {
        super(listener, errorListener);
        String[] cssSelect = {
            "#right > div:nth-child(4) > div:nth-child(1) > div.BlockL"
        };
        int[] types = {
            HomeItem.FORUM
        };
        this.cssSelect = cssSelect;
        this.types = types;
    }
}
