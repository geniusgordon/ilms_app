package com.example.gordon.ilms.http;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gordon on 10/1/15.
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {

    final static String LOG_TAG = "DownloadTask";

    @Override
    protected String doInBackground(String... params) {
        String filePath = params[1];
        Log.d(LOG_TAG, filePath);
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            ((HttpURLConnection) conn).setRequestMethod("GET");

            int total = 0;

            for (int i = 0;; i++) {
                String headerName = conn.getHeaderFieldKey(i);
                String headerValue = conn.getHeaderField(i);

                Log.d(LOG_TAG, headerName==null ? "" : headerName);
                Log.d(LOG_TAG, headerValue==null ? "" : headerValue);

                if (headerName == null && headerValue == null) {
                    break;
                }

                if (headerName != null && headerName.toLowerCase().equals("content-disposition")) {
                    String[] tmp = headerValue.split("\'");
                    String fileName = tmp[tmp.length-1];

                    Log.d(LOG_TAG, headerValue);
                    Log.d(LOG_TAG, fileName);

                    int c = fileName.lastIndexOf('.');
                    c = c > 0 ? c : fileName.length();
                    String fileEx = c > 0 ? fileName.substring(c+1) : "";
                    fileName = c > 0 ? fileName.substring(0, c) : fileName;

                    String tmpPath = filePath + "/" + fileName + "." + fileEx;
                    if (new File(tmpPath).exists()) {
                        for (int t = 1; ; t++) {
                            tmpPath = filePath + "/" + fileName + "(" + t + ")." + fileEx;
                            if (!new File(tmpPath).exists()) {
                                break;
                            }
                        }
                    }
                    filePath = tmpPath;

                    Log.d(LOG_TAG, filePath);
                } else if (headerName != null && headerName.toLowerCase().equals("content-length")) {
                    total = Integer.parseInt(headerValue);
                }

            }

            BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
            FileOutputStream outputStream;

            try {
                outputStream = new FileOutputStream(new File(filePath));

                int count = 0;
                int read = 0;
                int num = 8096;
                byte[] buf = new byte[num];
                while (true) {
                    read = inputStream.read(buf, 0, num);
                    if (read == -1)
                        break;

                    outputStream.write(buf, 0, read);
                    count += read;
                    //Log.d(LOG_TAG, String.format("%d / %d", count, total));
                    publishProgress((int) ((double) count / total * 100));
                }

                inputStream.close();
                outputStream.close();
            } catch (FileNotFoundException e) {
                BufferedOutputStream stream = new BufferedOutputStream(System.out);
                int t = 0;
                while (t != -1) {
                    t = inputStream.read();
                    stream.write(t);
                }
                stream.close();
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
    }
}
