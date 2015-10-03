package com.example.gordon.ilms.http.main;

import com.android.volley.Response;
import com.example.gordon.ilms.model.HomeItem;

import java.util.List;

/**
 * Created by gordon on 10/1/15.
 */
public class NewestMaterialRequest extends HomeItemListRequest {

    public NewestMaterialRequest(Response.Listener<List<HomeItem>> listener, Response.ErrorListener errorListener) {
        super(listener, errorListener);
        String[] cssSelect = {
                "#right > div:nth-child(4) > div:nth-child(2) > div.BlockL",
        };
        int[] types = {
                HomeItem.MATERIAL,
        };
        this.cssSelect = cssSelect;
        this.types = types;
    }
}
