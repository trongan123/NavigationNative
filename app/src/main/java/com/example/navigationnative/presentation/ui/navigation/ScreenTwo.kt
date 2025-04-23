package com.example.navigationnative.presentation.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavBackStackEntry
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.view.ToolBarView
import com.example.navigationnative.utils.NavigationUtils
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

object ScreenTwo {

    const val ROUTE = "ScreenTwo"
    const val ROUTE_WITH_PARAMS = "$ROUTE?number={number}"

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
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        val flutterEngineId = "my_engine_id"
                        var flutterEngine = FlutterEngineCache.getInstance().get(flutterEngineId)

                        if (flutterEngine == null) {
                            flutterEngine = FlutterEngine(ctx).apply {
                                dartExecutor.executeDartEntrypoint(
                                    DartExecutor.DartEntrypoint.createDefault()
                                )
                            }
                            FlutterEngineCache.getInstance().put(flutterEngineId, flutterEngine)
                        }

                        FlutterView(ctx).apply {
                            attachToFlutterEngine(flutterEngine)
                        }
                    }
                )
            }
        }
    }
}