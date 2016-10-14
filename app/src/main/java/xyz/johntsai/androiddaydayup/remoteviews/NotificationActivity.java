package xyz.johntsai.androiddaydayup.remoteviews;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import xyz.johntsai.androiddaydayup.MainActivity;
import xyz.johntsai.androiddaydayup.R;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 2016/10/7.
 * Notification的基本使用
 */

public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notification);
    }

    public void showNotification(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Title")
                .setContentText("Content Text");

        Intent resultIntent = new Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager manager
                = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(),builder.build());
    }
}
