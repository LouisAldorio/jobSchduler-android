package com.mindorks.framework.alarmmanagerandjobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class MyAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.w("Diana", "Diana")
        val Notifyid = 30103
        val Channel_id = "my_channel_01"
        val name = "ON/OFF"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(Channel_id,
            name,
            importance)
        val mBuilder = NotificationCompat.Builder(context!!,Channel_id)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText(intent?.getStringExtra(EXTRA_PESAN))
            .setContentTitle("Alarm Manager")
            .setNumber(3)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        var mNotificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        for(s in mNotificationManager.notificationChannels){
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        mNotificationManager.createNotificationChannel(nNotifyChannel)
        mNotificationManager.notify(Notifyid,mBuilder.build())
    }
}