package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM messages here.
        Log.d(TAG, "From: ${remoteMessage.from}")

        remoteMessage.data.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // Check if message contains a data payload.
        remoteMessage.data.isNotEmpty().let {
            //sendNotification(remoteMessage.data["message"])
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body)
        }
    }

    private fun sendRegistrationToServer(token: String){

    }

    private fun sendNotification(messageBody: String?) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager ::class.java)
        messageBody?.let { notificationManager?.sendNotification(it, applicationContext) }

    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
        // Optionally send the token to your server
    }

    companion object {
        private const val TAG = "" +
                ""
    }
}
