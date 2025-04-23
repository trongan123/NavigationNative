package com.example.navigationnative.presentation.ui.present

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.navigationnative.utils.NavigationUtils
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object PresentTwo {
    const val ROUTE = "PresentTwo"
    const val ENGINE_ID = "present_two"
    private const val CHANNEL = "com.navigation.flutter/present.two"

    @Composable
    fun Show() {
        FullScreenDialog(
            onDismissRequest = {
                NavigationUtils.popBackStack()
            },
        ) {
            Screen("Present Two")
        }
    }

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

                        flutterEngine.also {
                            setupFlutterChannel(it) { event ->
                                when (event) {
                                    "Back" -> {
                                        NavigationUtils.popBackStack()
                                    }
                                }
                            }
                        }

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

    private fun setupFlutterChannel(flutterEngine: FlutterEngine, onEvent: (String) -> Unit) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    "onFlutterEvent" -> {
                        val data = call.arguments as? String
                        onEvent(data ?: "")
                        result.success(null)
                    }

                    else -> result.notImplemented()
                }
            }
    }
}