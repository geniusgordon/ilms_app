package com.android.volley.toolbox;

import org.apache.http.Header;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by gordon on 10/6/15.
 */
public class MultiHeaderBasicNetwork extends BasicNetwork {
    public MultiHeaderBasicNetwork(HttpStack stack) {
        super(stack);
    }

    public MultiHeaderBasicNetwork(HttpStack stack, ByteArrayPool pool) {
        super(stack, pool);
    }

    /**
     * Converts Headers[] to Map<String, String>.
     */
    protected static Map<String, String> convertHeaders(Header[] headers) {
        Map<String, String> result = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headers.length; i++) {
            result.put(headers[i].getName(), headers[i].getValue());
        }
        return result;
    }
}
