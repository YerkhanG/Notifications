package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationManaging(context : Context) {
    private val notification = NotificationCompat.Builder(context, getChannelId(context))
        .setSmallIcon(android.R.drawable.ic_dialog_email)
        .setContentTitle("My custom title")
        .setContentText("My notification , only 1 line tho")
        .build()




    fun showMessage(context: Context) {
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(6535, notification)
    }


    private fun getChannelId(context : Context) : String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "JustCodeNotification"
            val channelName = "JustCodeName"
            val channelDescription = "Channel for just code notifications"
            val channelImportance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, channelImportance)
            channel.description = channelDescription

            val manager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

            channelId
        } else ""
    }
}