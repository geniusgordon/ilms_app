package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by gordon on 10/1/15.
 */
public class MultipartRequest extends BaseRequest<String> {

//    final static String URL = "http://httpbin.org/post";
    final static String LOG_TAG = "MultipartRequest";

    /** Useful values for writing multipart/form-data */
    private static char[] boundaryChoices = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY".toCharArray();
    private static String CRLF = "\r\n";
    private static String TWO_HYPHENS = "--";
    private String boundary;

    protected Map<String, String> params = null;
    protected Map<String, File> files = null;

    /**
     *
     * @param params mapping of string values of the form to send
     * @param files mapping of files of the form to send
     * @param listener Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public MultipartRequest(String url, Map<String, String> params, Map<String, File> files,
                            Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        this.boundary = randomBoundary();
        this.params = params;
        this.files = files;
    }

    /**
     * Generate a random boundary.
     *
     * @return the generated boundary
     */
    private static String randomBoundary() {
        Random random = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char choice;
        for (int i=0; i<10; i++) {
            choice = boundaryChoices[random.nextInt(boundaryChoices.length)];
            randomStringBuilder.append(choice);
        }

        return "--androidMultipartBoundary" + randomStringBuilder.toString();
    }

    /**
     * @deprecated Use {@link #getBodyContentType()}.
     */
    @Override
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    /**
     * @deprecated Use {@link #getBody()}.
     */
    @Override
    public byte[] getPostBody() {
        return getBody();
    }

    @Override
    public String getBodyContentType() {
        return String.format("multipart/form-data;boundary=%s", boundary);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        headers.put("Referer", "http://lms.nthu.edu.tw/post_insert.php?courseID=19025&action=post");
        return headers;
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bodyOs = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bodyOs);
        byte[] body = null;

        try {
            if (params != null) {
                for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                    writeData(dos, paramEntry.getKey(), paramEntry.getValue());
                }
            }

            if (files != null) {
                for (Map.Entry<String, File> fileEntry : files.entrySet()) {
                    writeFile(dos, fileEntry.getKey(), fileEntry.getValue());
                }
            }

            if (bodyOs.size() > 0) {
                dos.writeBytes(TWO_HYPHENS + boundary + TWO_HYPHENS + CRLF);
            }

            dos.flush();
            body = bodyOs.toByteArray();
            dos.close();
            bodyOs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, new String(body));
        return (body.length == 0) ? null : body;
    }

    @Override
    protected String parseResponseHtml(String responseHtml) {
        return responseHtml;
    }

    private void writeData(DataOutputStream os, String field, String value) throws IOException {
        value = value==null ? "" : value;
        os.writeBytes(TWO_HYPHENS + boundary + CRLF);
        os.writeBytes("Content-Disposition: form-data; name=\"" + field + "\"" + CRLF);
        //os.writeBytes("content-type: text/plain; charset=utf-8" + CRLF);
        os.writeBytes(CRLF);
        os.write(value.getBytes());
        os.writeBytes(CRLF);
    }

    private void writeFile(DataOutputStream os, String field, File file) throws IOException {
        String filename = file == null ? "" : file.getName();

        os.writeBytes(TWO_HYPHENS + boundary + CRLF);
        os.writeBytes("Content-Disposition: form-data; name=\"" +
                field + "\";filename=\"" + filename + "\"" + CRLF);
        os.writeBytes("Content-Type: application/octet-stream" + CRLF); // TODO: content-type
        os.writeBytes(CRLF);

        if (file != null) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            while ((bytesRead = bis.read(buffer)) > 0) {
                os.write(buffer, 0, bytesRead);
            }

            bis.close();
        }
        os.writeBytes(CRLF);
    }
}
