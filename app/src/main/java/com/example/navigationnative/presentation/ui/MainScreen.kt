package com.example.navigationnative.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigationnative.presentation.ui.bottomnavigate.CallScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.FriendScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.HomeScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.ProfileScreen
import com.example.navigationnative.presentation.ui.bottomnavigate.SearchScreen
import com.example.navigationnative.presentation.ui.view.BackExit
import com.example.navigationnative.presentation.ui.view.BottomNavigationBar
import com.example.navigationnative.utils.BottomNavigationUtils

object MainScreen {
    const val ROUTE = "mainScreen"

    @Composable
    fun Screen() {
        BackExit()
        val pagerState = rememberPagerState(initialPage = 2, pageCount = { 5 })
        BottomNavigationUtils.setPagerState(pagerState)

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            bottomBar = { BottomNavigationBar(pagerState) }) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                HorizontalPager(state = pagerState) { page ->
                    BottomNavigationUtils.handleSelectedNavigation(pagerState.currentPage)
                    when (page) {
                        0 -> SearchScreen.Screen("Search Screen")
                        1 -> CallScreen.Screen("Call Screen")
                        2 -> HomeScreen.Screen("Home Screen")
                        3 -> FriendScreen.Screen("Friend Screen")
                        4 -> ProfileScreen.Screen("Profile Screen")
                    }
                }
            }
        }
    }
}