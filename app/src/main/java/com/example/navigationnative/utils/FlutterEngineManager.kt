package com.example.navigationnative.utils

import android.content.Context
import com.example.navigationnative.presentation.ui.bottomnavigate.ProfileScreen
import com.example.navigationnative.presentation.ui.navigation.ScreenOne
import com.example.navigationnative.presentation.ui.navigation.ScreenThree
import com.example.navigationnative.presentation.ui.navigation.ScreenTwo
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.present.PresentTwo
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {
    private var engineGroup: FlutterEngineGroup? = null

    fun getEngineGroup(context: Context): FlutterEngineGroup {
        if (engineGroup == null) {
            engineGroup = FlutterEngineGroup(context)
        }
        return engineGroup!!
    }

    fun registerProvideFlutterEngines(context: Context) {
        val engineGroup = getEngineGroup(context)

        registerProvideFlutterEngine(context, engineGroup, ScreenOne.ENGINE_ID)
        registerProvideFlutterEngine(context, engineGroup, ScreenTwo.ENGINE_ID)
        registerProvideFlutterEngine(context, engineGroup, ScreenThree.ENGINE_ID)
        registerProvideFlutterEngine(context, engineGroup, PresentOne.ENGINE_ID)
        registerProvideFlutterEngine(context, engineGroup, PresentTwo.ENGINE_ID)
        registerProvideFlutterEngine(context, engineGroup, ProfileScreen.ENGINE_ID)
    }

    fun registerProvideFlutterEngine(
        context: Context,
        engineGroup: FlutterEngineGroup,
        engineId: String
    ) {
        val flutterEngine = engineGroup.createAndRunEngine(
            context,
            DartExecutor.DartEntrypoint.createDefault(),
            "/${engineId}"
        )

        FlutterEngineCache.getInstance().put(engineId, flutterEngine)
    }
}



