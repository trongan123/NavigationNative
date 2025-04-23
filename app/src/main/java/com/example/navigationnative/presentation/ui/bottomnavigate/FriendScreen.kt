package com.example.navigationnative.presentation.ui.bottomnavigate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object FriendScreen {
    const val ENGINE_ID = "screen_search"
    private const val CHANNEL = "com.navigation.flutter/search"
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(title: String) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(title) }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        var flutterEngine =
                            FlutterEngineCache.getInstance().get(ENGINE_ID)

                        if (flutterEngine == null) {
                            flutterEngine = FlutterEngine(ctx).apply {
                                dartExecutor.executeDartEntrypoint(
                                    DartExecutor.DartEntrypoint.createDefault()
                                )
                            }
                            FlutterEngineCache.getInstance()
                                .put(ENGINE_ID, flutterEngine)
                        }
                        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,
                            CHANNEL
                        )
                            .invokeMethod("setData", mapOf( "title" to title))

                        val flutterSurfaceView = FlutterSurfaceView(ctx)
                        val flutterView = FlutterView(ctx, flutterSurfaceView)
                        flutterView.attachToFlutterEngine(flutterEngine)
                        flutterEngine.lifecycleChannel.appIsResumed()

                        flutterView
                    }
                )
            }
        }
    }
}

