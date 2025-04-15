package com.example.navigationnative.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigationnative.presentation.ui.navigation.ScreenOne
import com.example.navigationnative.presentation.ui.view.BackExit
import com.example.navigationnative.utils.NavigationUtils

object MainScreen {
    const val ROUTE = "mainScreen"

    @Composable
    fun Screen() {
        BackExit()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        )
        { innerPadding ->
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
                    Button(onClick = {
                       NavigationUtils.navigate(ScreenOne.ROUTE)
                    }) {
                        Text("Present", color = Color.White)
                    }
                    Button(onClick = {

                    }) {
                        Text("Push", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.popBackStack()
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