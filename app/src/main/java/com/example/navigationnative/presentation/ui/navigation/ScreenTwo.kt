package com.example.navigationnative.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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

object ScreenTwo {

    const val ROUTE = "ScreenTwo"
    const val ROUTE_WITH_PARAMS = "$ROUTE?number={number}"
    const val ENGINE_ID = "screen_two"
    private const val CHANNEL = "com.navigation.flutter/screen.two"

    fun getRouteWithParam(number: Int?): String {
        return "${ROUTE}?number=$number"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        title: String,
        backStackEntry: NavBackStackEntry
    ) {
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
                        var flutterEngine = FlutterEngineCache.getInstance().get(ENGINE_ID)

                        if (flutterEngine == null) {
                            // Sử dụng FlutterEngineGroup để tạo engine mới
                            val engineGroup = FlutterEngineManager.getEngineGroup(ctx)
                            flutterEngine = engineGroup.createAndRunEngine(
                                ctx,
                                DartExecutor.DartEntrypoint.createDefault(),
                                "/${ENGINE_ID}" // initial route
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
                                        NavigationUtils.savedStateHandle("number", number)
                                        NavigationUtils.navigate(ScreenThree.ROUTE)
                                    }

                                    "Back" -> {
                                        NavigationUtils.popBackStack()
                                    }

                                    "ClearStack" -> {
                                        NavigationUtils.navigate(
                                            ScreenThree.getRouteWithParam(number),
                                            ROUTE,
                                            true
                                        )
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