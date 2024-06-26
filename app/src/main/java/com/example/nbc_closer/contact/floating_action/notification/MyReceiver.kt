package com.example.nbc_closer.contact.floating_action.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.nbc_closer.contact.MainActivity
import com.example.nbc_closer.R
import com.example.nbc_closer.contact.floating_action.notification.Constant.Companion.CHANNEL_ID
import com.example.nbc_closer.contact.floating_action.notification.Constant.Companion.NOTIFICATION_ID
import com.example.nbc_closer.contact.floating_action.notification.Constant.Companion.notiTitle

class MyReceiver : BroadcastReceiver() {

    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        createNotificationChannel()
        deliverNotification(context)
    }
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Channel name",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "채널의 상세정보"
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
    }
    private fun deliverNotification(context:Context){
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_MUTABLE
        )
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ringing)
            .setContentTitle("Notification")
            .setContentText(notiTitle)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        builder.setContentIntent(contentPendingIntent)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

}