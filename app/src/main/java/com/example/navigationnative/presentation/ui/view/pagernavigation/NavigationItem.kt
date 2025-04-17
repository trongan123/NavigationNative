package com.example.navigationnative.presentation.ui.view.pagernavigation

import androidx.compose.runtime.Composable
import com.example.navigationnative.R
import com.example.navigationnative.presentation.ui.bottomnavigate.CallScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.FriendScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.HomeScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.ProfileScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.SearchScreen

data class NavigationItem(
    val iconDefault: Int,
    val iconSelected: Int,
    val content: @Composable (() -> Unit)
) {
    companion object {
        val navigationItems =
            listOf(
                NavigationItem(
                    R.drawable.ic_nav_search,
                    R.drawable.ic_nav_search_selected
                ) {
                    SearchScreen.Screen("Search Screen")
                },
                NavigationItem(
                    R.drawable.ic_home,
                    R.drawable.ic_home_selected
                ) {
                    CallScreen.Screen("Call Screen")
                },
                NavigationItem(
                    R.drawable.ic_home,
                    R.drawable.ic_home_selected
                ) {
                    HomeScreen.Screen("Home Screen")
                },
                NavigationItem(
                    R.drawable.ic_home,
                    R.drawable.ic_home_selected
                ) {
                    FriendScreen.Screen("Friend Screen")
                },
                NavigationItem(
                    R.drawable.ic_home,
                    R.drawable.ic_home_selected
                ) {
                    ProfileScreen.Screen("Profile Screen")
                }
            )
    }
}