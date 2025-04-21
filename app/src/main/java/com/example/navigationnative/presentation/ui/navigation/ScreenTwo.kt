package com.example.navigationnative.presentation.ui.navigation

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.view.ToolBarView
import com.example.navigationnative.utils.NavigationUtils

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

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    number = number?.plus(1)
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        fontSize = 18.sp,
                        text = number.toString()
                    )
                    Button(onClick = {
                        NavigationUtils.navigate(PresentOne.ROUTE)
                    }) {
                        Text("Present", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.savedStateHandle("number", number)
                        NavigationUtils.navigate(ScreenThree.ROUTE)
                    }) {
                        Text("Push", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.popBackStack()
                    }) {
                        Text("Back", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.navigate(ScreenThree.getRouteWithParam(number), ROUTE, true)
                    }) {
                        Text("Push and Clear Stack", color = Color.White)
                    }

                }
            }
        }
    }
}