package com.example.navigationnative.domain.usecase

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.navigationnative.MainActivity
import com.example.navigationnative.R
import com.example.navigationnative.presentation.ui.navigation.ScreenThree
import javax.inject.Inject

class NotificationUseCase @Inject constructor() {
    private val CHANNEL_ID = "demo_channel"

    @SuppressLint("MissingPermission")
    fun showSimpleNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("ROUTE", ScreenThree.ROUTE)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home_selected) // dùng icon trong drawable
            .setContentTitle("Navigation DEMO")
            .setContentText("Navigation to Screen")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(1001, builder.build())
        }
    }
}