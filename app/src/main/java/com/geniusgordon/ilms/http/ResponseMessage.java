package com.geniusgordon.ilms.http;

/**
 * Created by gordon on 10/4/15.
 */
public class ResponseMessage {
    final static String TIMEOUT_MSG = "無法連線，請稍後再試";
    final static String NO_PERMISSION_MSG = "權限不足";
    final static String NOT_LOGIN_MSG = "尚未登入";

    public final static int OK = 0;
    public final static int TIMEOUT = 1;
    public final static int NO_PERMISSION = 2;
    public final static int NOT_LOGIN = 3;

    public static String getMessage(int t) {
        switch (t) {
            case OK:
                return "";
            case TIMEOUT:
                return TIMEOUT_MSG;
            case NO_PERMISSION:
                return NO_PERMISSION_MSG;
            case NOT_LOGIN:
                return NOT_LOGIN_MSG;
        }
        return "";
    }

}
