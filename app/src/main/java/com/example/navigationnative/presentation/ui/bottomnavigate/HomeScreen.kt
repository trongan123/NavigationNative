package com.example.navigationnative.presentation.ui.bottomnavigate

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navigationnative.presentation.ui.navigation.ScreenOne
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.viewmodel.NavigationViewModel
import com.example.navigationnative.utils.FlutterEngineManager
import com.example.navigationnative.utils.NavigationUtils
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object HomeScreen {

    const val ENGINE_ID = "main"
    private const val CHANNEL = "com.navigation.flutter/main"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        title: String,
        viewModel: NavigationViewModel = hiltViewModel(),
        context: Context = LocalView.current.context
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
        )
        { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        var flutterEngine = FlutterEngineCache.getInstance().get(ENGINE_ID)

                        if (flutterEngine == null) {
                            val engineGroup = FlutterEngineManager.getEngineGroup(ctx)
                            flutterEngine = engineGroup.createAndRunEngine(
                                ctx,
                                DartExecutor.DartEntrypoint.createDefault()
                            )
                            FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
                        }

                        flutterEngine.also {
                            setupFlutterChannel(it) { event ->
                                when (event) {
                                    "Present" -> {
                                        NavigationUtils.navigate(PresentOne.ROUTE)
                                    }

                                    "Push" -> {
                                        NavigationUtils.savedStateHandle("number", 0)
                                        NavigationUtils.navigate(ScreenOne.ROUTE)
                                    }

                                    "BottomSheet" -> {
                                        MethodChannel(
                                            flutterEngine.dartExecutor.binaryMessenger,
                                            CHANNEL
                                        ).invokeMethod("showBottomSheet", null)
                                    }

                                    "Notification" -> {
                                        viewModel.showSimpleNotification(context)
                                    }

                                }
                            }
                        }

                        val flutterSurfaceView = FlutterSurfaceView(context)
                        val flutterView = FlutterView(context, flutterSurfaceView)
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
                    "navigation" -> {
                        val data = call.arguments as? String
                        onEvent(data ?: "")
                        result.success(null)
                    }

                    else -> result.notImplemented()
                }
            }
    }
}