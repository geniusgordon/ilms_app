package com.example.gordon.ilms.http;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gordon on 10/1/15.
 */
public class DownloadTask extends AsyncTask<String, Integer, Boolean> {

    final static String LOG_TAG = "DownloadTask";

    @Override
    protected Boolean doInBackground(String... params) {
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

                if (headerName == null && headerValue == null) {
                    break;
                }

                if (headerName != null && headerName.equals("Content-Disposition")) {
                    String[] tmp = headerValue.split("\'");
                    String fileName = tmp[tmp.length-1];
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
                } else if (headerName != null && headerName.equals("Content-Length")) {
                    total = Integer.parseInt(headerValue);
                }

            }

            BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
            FileOutputStream outputStream = new FileOutputStream(new File(filePath));

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
