package com.example.navigationnative.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationnative.presentation.ui.MainScreen
import com.example.navigationnative.presentation.ui.navigation.screenone.ScreenOne
import com.example.navigationnative.presentation.ui.navigation.ScreenThree
import com.example.navigationnative.presentation.ui.navigation.ScreenTwo
import com.example.navigationnative.utils.NavigationUtils

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavigationUtils.setNavController(navController)

    NavHost(
        navController = navController,
        startDestination = MainScreen.ROUTE
    ) {
        addAnimatedComposable(MainScreen.ROUTE) {
            MainScreen.Screen()
        }
        addAnimatedComposable(ScreenOne.ROUTE) {
            ScreenOne.Screen("ScreenOne")
        }
        addAnimatedComposable(ScreenTwo.ROUTE) {
            ScreenTwo.Screen("ScreenTwo")
        }
        addAnimatedComposable(ScreenThree.ROUTE) {
            ScreenThree.Screen("ScreenThree")
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addAnimatedComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally { it } + fadeIn() },
        exitTransition = { slideOutHorizontally { -it } + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() },
        content = content
    )
}

