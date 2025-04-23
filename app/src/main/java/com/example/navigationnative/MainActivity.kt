package com.example.navigationnative

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.navigationnative.presentation.navigation.AppNavigation
import com.example.navigationnative.presentation.theme.NavigationNativeTheme
import com.example.navigationnative.utils.NavigationUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val CHANNEL_ID = "demo_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationNativeTheme {
                AppNavigation()
                LaunchedEffect(Unit) {
                    handleDataFromIntent(intent)
                }
            }
        }

        createNotificationChannel()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleDataFromIntent(intent)
    }

    private fun handleDataFromIntent(intent: Intent) {
        val uri = intent.data
        if (uri != null) {
            return
        }

        val route = intent.getStringExtra("ROUTE")

        if (route?.isEmpty() != false) return
        NavigationUtils.savedStateHandle("number", 2)
        NavigationUtils.navigate(route)
    }

    private fun createNotificationChannel() {
        val name = "Demo Channel"
        val descriptionText = "Channel for demo notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}