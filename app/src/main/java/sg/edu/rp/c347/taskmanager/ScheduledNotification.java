package sg.edu.rp.c347.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 15017612 on 26/5/2017.
 */

public class ScheduledNotification extends BroadcastReceiver {

    int reqCode = 12345;

    //
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode,
                i, PendingIntent.FLAG_CANCEL_CURRENT);

        String rbID = intent.getStringExtra("rbID");

        if (rbID == "rb1") {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(intent.getStringExtra("name"));
            builder.setContentText(intent.getStringExtra("desc"));
            builder.setSmallIcon(android.R.drawable.ic_dialog_info);
            builder.setContentIntent(pIntent);
            builder.setAutoCancel(true);
            Notification n = builder.build();
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(123, n);
        }else {
            Notification.BigTextStyle bigText = new
                    Notification.BigTextStyle();
            bigText.bigText("HI");
            bigText.setBigContentTitle(intent.getStringExtra("name"));
            bigText.setSummaryText(intent.getStringExtra("desc"));

            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(intent.getStringExtra("name"));
            builder.setContentText(intent.getStringExtra("desc"));
            builder.setSmallIcon(android.R.drawable.ic_dialog_info);
            builder.setContentIntent(pIntent);
            builder.setStyle(bigText);
            builder.setAutoCancel(true);
            Notification n = builder.build();
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(123, n);


       }
    }
}