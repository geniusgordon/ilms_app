package com.example.gordon.ilms;

import android.text.Spannable;
import android.text.Spanned;
import android.text.style.URLSpan;

/**
 * Created by gordon on 9/30/15.
 */
public class HtmlFix {
    public static String DOMAIN = "http://lms.nthu.edu.tw";
    public static Spanned correctLinkPaths (Spanned spantext) {
        Object[] spans = spantext.getSpans(0, spantext.length(), Object.class);
        for (Object span : spans) {
            int start = spantext.getSpanStart(span);
            int end = spantext.getSpanEnd(span);
            int flags = spantext.getSpanFlags(span);
            if (span instanceof URLSpan) {
                URLSpan urlSpan = (URLSpan) span;
                if (!urlSpan.getURL().startsWith("http")) {
                    if (urlSpan.getURL().startsWith("/")) {
                        urlSpan = new URLSpan(DOMAIN + urlSpan.getURL());
                    } else {
                        urlSpan = new URLSpan(DOMAIN + "/" + urlSpan.getURL());
                    }
                }
                ((Spannable) spantext).removeSpan(span);
                ((Spannable) spantext).setSpan(urlSpan, start, end, flags);
            }
        }
        return spantext;
    }
}
