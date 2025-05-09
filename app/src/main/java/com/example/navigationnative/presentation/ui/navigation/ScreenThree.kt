package com.example.navigationnative.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavBackStackEntry
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.view.ToolBarView
import com.example.navigationnative.utils.FlutterEngineManager
import com.example.navigationnative.utils.NavigationUtils
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object ScreenThree {

    const val ROUTE = "ScreenThree"
    const val ROUTE_WITH_PARAMS = "$ROUTE?name={name}&number={number}"
    const val ENGINE_ID = "screen_three"
    private const val CHANNEL = "com.navigation.flutter/screen.three"

    fun getRouteWithParam(number: Int?): String {
        return "${ROUTE}?number=$number"
    }

    @Composable
    fun Screen(title: String, backStackEntry: NavBackStackEntry) {
        var number: Int? = 0

        number = NavigationUtils.getSavedStateHandle()?.get<Int>("number")

        if (number == 0) {
            number = backStackEntry.arguments?.getInt("number")
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ToolBarView(title)
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
                            val engineGroup = FlutterEngineManager.getEngineGroup(ctx)
                            flutterEngine = engineGroup.createAndRunEngine(
                                ctx,
                                DartExecutor.DartEntrypoint.createDefault(),
                                "/${ENGINE_ID}"
                            )
                            FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
                        }

                        flutterEngine.also {
                            setupFlutterChannel(it) { event ->
                                when (event) {
                                    "Present" -> {
                                        NavigationUtils.navigate(PresentOne.ROUTE)
                                    }

                                    "Back" -> {
                                        NavigationUtils.popBackStack()
                                    }

                                    "ClearStack" -> {
                                        NavigationUtils.popBackMain()
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