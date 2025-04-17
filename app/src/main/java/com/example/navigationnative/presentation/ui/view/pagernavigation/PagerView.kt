package com.example.navigationnative.presentation.ui.view.pagernavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PagerView(
    initialPage: Int? = 0,
    navigationItems: List<NavigationItem>
) {
    val pagerState =
        rememberPagerState(initialPage = initialPage ?: 0, pageCount = { navigationItems.size })
    BottomNavigationUtils.setPagerState(pagerState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            BottomNavigationBar(
                pagerState,
                initialPage ?: 0,
                navigationItems
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            HorizontalPager(state = pagerState) { page ->
                BottomNavigationUtils.handleSelectedNavigation(pagerState.currentPage)
                navigationItems[page].content.invoke()
            }
        }
    }
}