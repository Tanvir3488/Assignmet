package com.example.test.Extra

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.test.DB.UserDB
import com.example.test.Dao.UserDao
import com.example.test.table.User
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigInteger
import javax.inject.Inject


/**
 * Created by Tanvir3488 on 2/16/2023.
 */


class NotificationService () : OSRemoteNotificationReceivedHandler {

    override fun remoteNotificationReceived(
        context: Context?,
        notificationReceivedEvent: OSNotificationReceivedEvent
    ) {

        var userDao:UserDao=UserDB.getInstance(context!!).userDao
        val notification = notificationReceivedEvent.notification

        // Example of modifying the notification's accent color
        val mutableNotification = notification.mutableCopy()
        mutableNotification.setExtender { builder: NotificationCompat.Builder ->
            // Sets the accent color to Green on Android 5+ devices.
            // Accent color controls icon and action buttons on Android 5+. Accent color does not change app title on Android 10+
            builder.color = BigInteger("FF00FF00", 16).toInt()
            // Sets the notification Title to Red
            val spannableTitle: Spannable = SpannableString(notification.title)
            spannableTitle.setSpan(
                ForegroundColorSpan(Color.RED),
                0,
                notification.title.length,
                0
            )
            builder.setContentTitle(spannableTitle)
            // Sets the notification Body to Blue
            val spannableBody: Spannable = SpannableString(notification.body)
            spannableBody.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0,
                notification.body.length,
                0
            )
            builder.setContentText(spannableBody)
            //Force remove push from Notification Center after 30 seconds
            builder.setTimeoutAfter(30000)
            builder
        }
        val data = notification.additionalData
        Log.e("OneSignalExample", "Received Notification Data: ${notification.body}")
        GlobalScope.launch {
            userDao.addUser(User(0,notification.body,notification.title,"from notification","from notificatio"))
        }

        // If complete isn't call within a time period of 25 seconds, OneSignal internal logic will show the original notification
        // To omit displaying a notification, pass `null` to complete()
        notificationReceivedEvent.complete(mutableNotification)
    }
}