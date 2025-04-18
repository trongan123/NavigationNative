package com.example.navigationnative.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationnative.presentation.ui.MainScreen
import com.example.navigationnative.presentation.ui.navigation.ScreenThree
import com.example.navigationnative.presentation.ui.navigation.ScreenTwo
import com.example.navigationnative.presentation.ui.navigation.screenone.ScreenOne
import com.example.navigationnative.presentation.ui.present.BottomSheetScreen
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.present.PresentTwo
import com.example.navigationnative.utils.NavigationUtils


@Composable
fun AppNavigation() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    NavigationUtils.setNavController(navController)
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator
    ) {
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
            addAnimatedComposable(
                ScreenTwo.ROUTE + "{number}",
                arguments = listOf(navArgument("number") { type = NavType.IntType })
            ) { backStackEntry ->
                ScreenTwo.Screen("ScreenTwo", backStackEntry = backStackEntry)
            }
            addAnimatedComposable(
                ScreenThree.ROUTE + "{number}",
                arguments = listOf(navArgument("number") { type = NavType.IntType })
            ) { backStackEntry ->
                ScreenThree.Screen("ScreenThree", backStackEntry = backStackEntry)
            }
            bottomSheet(BottomSheetScreen.ROUTE) {
                BottomSheetScreen.Screen()
            }
            dialog(PresentOne.ROUTE) {
                PresentOne.Show()
            }
            dialog(PresentTwo.ROUTE) {
                PresentTwo.Show()
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addAnimatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
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