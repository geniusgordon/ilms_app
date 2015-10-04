package com.geniusgordon.ilms.app;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.course.AnnouncementDetailActivity;
import com.geniusgordon.ilms.app.course.CourseActivity;
import com.geniusgordon.ilms.app.course.HomeworkDetailActivity;
import com.geniusgordon.ilms.app.course.MaterialDetailActivity;
import com.geniusgordon.ilms.app.forum.ForumActivity;
import com.geniusgordon.ilms.app.forum.PostDetailActivity;
import com.geniusgordon.ilms.app.main.MainActivity;
import com.geniusgordon.ilms.http.DownloadTask;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gordon on 9/30/15.
 */
public class ActivityDispatcher extends Activity {
    final static String LOG_TAG = "ActivityDispatcher";

    private static int downloadId = 0;

    private static Class[] activitiesToOpen = {
            MainActivity.class,
            CourseActivity.class,
            AnnouncementDetailActivity.class,
            HomeworkDetailActivity.class,
            MaterialDetailActivity.class,
            ForumActivity.class,
            PostDetailActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Log.d(LOG_TAG, uri.toString());

        if (uri.getEncodedPath().startsWith("/sys/read_attach")) {
            final NotificationManager mNotifyManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setContentTitle("Downloading")
                    .setSmallIcon(R.drawable.ic_file_download_white_48dp);

            final int id = downloadId;
            downloadId++;

            DownloadTask task = new DownloadTask() {
                @Override
                protected void onProgressUpdate(Integer... values) {
                    mBuilder.setContentInfo(values[0]+"%");
                    mBuilder.setProgress(100, values[0], false);
                    mNotifyManager.notify(id, mBuilder.build());
                }

                @Override
                protected void onPostExecute(String filePath) {
                    if (filePath != null) {
                        int c = filePath.lastIndexOf('.');
                        String fileEx = c > 0 ? filePath.substring(c+1) : "";
                        String[] tmp = filePath.split("/");
                        String fileName = tmp[tmp.length-1];
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(filePath)),
                                MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileEx));

                        PendingIntent pendingIntent = PendingIntent.
                                getActivity(ActivityDispatcher.this, 0, intent, 0);

                        mBuilder.setContentTitle("Download complete")
                                .setContentText(fileName)
                                .setProgress(0, 0, false)
                                .setContentIntent(pendingIntent);
                    } else {
                        mBuilder.setContentTitle("Download failed")
                                .setSmallIcon(R.drawable.ic_warning_white_48dp)
                                .setProgress(0, 0, false);
                    }
                    mNotifyManager.notify(id, mBuilder.build());
                }
            };

            task.execute(uri.toString(),
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            finish();
            return;
        }

        for (Class activityClass: activitiesToOpen) {
            try {
                Object obj = activityClass.newInstance();
                Intent intent = (Intent) activityClass.getMethod("isIntentUri",
                        Uri.class, ActivityDispatcher.this.getClass())
                        .invoke(obj, uri, ActivityDispatcher.this);
                if (intent != null) {
                    finish();
                    startActivity(intent);
                    break;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
