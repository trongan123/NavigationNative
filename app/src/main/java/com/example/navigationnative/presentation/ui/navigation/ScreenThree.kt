package com.example.navigationnative.presentation.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationnative.presentation.ui.MainScreen
import com.example.navigationnative.presentation.ui.navigation.screenone.ScreenOne
import com.example.navigationnative.presentation.ui.view.ToolBarView
import com.example.navigationnative.utils.NavigationUtils

object ScreenThree {

    const val ROUTE = "ScreenThree"

    @Composable
    fun Screen(title: String) {
        var number = NavigationUtils.getSavedStateHandle()?.get<Int>("number")
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
                        NavigationUtils.navigate(ScreenOne.ROUTE, ScreenTwo.ROUTE, true)
                    }) {
                        Text("Present", color = Color.White)
                    }
                    Button(onClick = {

                    }) {
                        Text("Push", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.popBackMain()
                    }) {
                        Text("Back", color = Color.White)
                    }
                    Button(onClick = {

                    }) {
                        Text("Clear Stack", color = Color.White)
                    }
                }
            }
        }
    }
}