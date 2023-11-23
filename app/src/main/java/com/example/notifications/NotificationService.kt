package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("FCM", "FCM TOKEN: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showMessage(createNotification(message.notification?.title, message.notification?.body))

    }
    private fun showMessage(notification: Notification) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(6535, notification)
    }

    private fun createNotification(title: String?, message: String?, subTitle: String? = null): Notification {
        return NotificationCompat.Builder(baseContext, getChannelId())
            .setSmallIcon(R.drawable.ic_clock)
            .setContentTitle(title)
            .setContentText(message)
            .setSubText(subTitle)
            .setOngoing(true)
            .setVibrate(LongArray(250))
            //.setLargeIcon(context.createBitmap(R.drawable.ic_large))
            .setColor(ContextCompat.getColor(baseContext, R.color.red))
            .build()
    }

    private fun getChannelId(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "JustCodeNotification"
            val channelName = "JustCodeName"
            val channelDescription = "Channel for just code notifications"
            val channelImportance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, channelImportance)
            channel.description = channelDescription

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

            channelId
        } else ""
    }

}