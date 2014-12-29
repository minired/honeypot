package com.example.sj.todoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.sj.todoapp.R;
import com.skt.geofence.GeoConstData;

//	Check In / Out / Stay 이벤트를 받기 위해서는 BroadcastReceiver를 등록해야 합니다.
//BroadcastReceiver를 상속받아 클래스를 만들어 줍니다//하기와 같이!!!

public class GeoServiceReceiver extends BroadcastReceiver {
    private final String TAG = "GeoFenceReceiver";
    private static int Ticker_count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive:" + action);
        Log.d(TAG, "intent:package = " + intent.getPackage());

        if (intent.getAction().equals("com.example.sj.todoapp.GEOEVENT")) {//fence 내로 들어왔을때 하기와 같이 event 를 실행합니다//Ticker 도 발생시키고..
            int storeId = intent.getIntExtra(GeoConstData.STORE_ID, 0);
            int fenceId = intent.getIntExtra(GeoConstData.FENCE_ID, 0);
            String fenceName = intent.getStringExtra(GeoConstData.EVENT_NAME);
            String groupName = intent.getStringExtra(GeoConstData.GROUP_NAME);
            String groupDesc = intent.getStringExtra(GeoConstData.GROUP_DESC);
            long startDate = intent.getLongExtra(GeoConstData.START_DATE, 0);
            long endDate = intent.getLongExtra(GeoConstData.END_DATE, 0);
            String eventType = intent.getStringExtra(GeoConstData.EVENT_TYPE);

            String eventName = intent.getStringExtra(GeoConstData.EVENT_TYPE);
            Intent content = new Intent(context, MainActivity.class);
            content.putExtra(GeoConstData.FENCE_ID, fenceId);
            content.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0,  content, 0);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext());
            mBuilder.setSmallIcon(R.drawable.ic_launcher);
            mBuilder.setContentTitle(eventName);
            mBuilder.setContentText(String.valueOf(fenceId) + "(" + fenceName + ")");
            mBuilder.setTicker("testTicker" + String.valueOf(fenceId));
            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mBuilder.setAutoCancel(false);
            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
            mBuilder.setContentIntent(resultPendingIntent);

            mNotificationManager.notify(Ticker_count, mBuilder.build());
            Ticker_count = Ticker_count+1;//

        }
    }
}
