package com.darrenthiores.movbybwa.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.darrenthiores.movbybwa.Container.MainContainerActivity
import com.darrenthiores.movbybwa.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("TOKEN", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        p0.notification?.apply {
            showNotification(this)
        }
    }

    private fun showNotification(remoteMessage: RemoteMessage.Notification) {

        val intent = Intent(this, MainContainerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_mov)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.mov_icon
                ))
                .setContentIntent(pendingIntent)
                .setContentTitle(remoteMessage.title)
                .setContentText(remoteMessage.body)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

    companion object{
        private const val NOTIFICATION_CHANNEL_ID = "channel_02"
        private const val CHANNEL_NAME = "push_channel"
        private const val NOTIFICATION_ID = 10
    }

}