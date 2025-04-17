package com.example.navigationnative.presentation.ui.view.pagernavigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.navigationnative.presentation.ui.view.IconView
import kotlinx.coroutines.launch

@Composable
fun BottomNavigationBar(
    pagerState: PagerState,
    initialPage: Int,
    navigationItems: List<NavigationItem>
) {
    val coroutineScope = rememberCoroutineScope()
    BottomNavigationUtils.setSelectedNavigationIndex(rememberSaveable {
        mutableIntStateOf(
            initialPage
        )
    })
    NavigationBar(
        modifier = Modifier.height(70.dp),
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = BottomNavigationUtils.isSelectedNavigation(pagerState.currentPage),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                        BottomNavigationUtils.handleSelectedNavigation(index)
                    }
                },
                icon = {
                    IconView(
                        if (BottomNavigationUtils.isSelectedNavigation(index)) item.iconSelected else item.iconDefault,
                        size = 26
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

