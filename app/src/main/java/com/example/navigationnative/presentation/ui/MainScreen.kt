package com.example.navigationnative.presentation.ui

import androidx.compose.runtime.Composable
import com.example.navigationnative.presentation.ui.view.BackExit
import com.example.navigationnative.presentation.ui.view.pagernavigation.NavigationItem
import com.example.navigationnative.presentation.ui.view.pagernavigation.PagerView

object MainScreen {
    const val ROUTE = "mainScreen"

    @Composable
    fun Screen() {
        BackExit()
        PagerView(2, NavigationItem.navigationItems)
    }
}
