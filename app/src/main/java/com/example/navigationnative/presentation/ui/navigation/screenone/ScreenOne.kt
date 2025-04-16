package com.example.navigationnative.presentation.ui.navigation.screenone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navigationnative.presentation.ui.navigation.ScreenTwo
import com.example.navigationnative.presentation.ui.view.ToolBarView
import com.example.navigationnative.presentation.viewmodel.NavigationViewModel
import com.example.navigationnative.utils.NavigationUtils

object ScreenOne {

    const val ROUTE = "ScreenOne"

    @Composable
    fun Screen(
        title: String,
        viewModel: NavigationViewModel = hiltViewModel(),
    ) {
        val text = viewModel.getText().collectAsState()
        var number = NavigationUtils.getSavedStateHandle()?.get<Int>("number")

        viewModel.setText()
        Scaffold(
            modifier = Modifier.Companion.fillMaxSize(),
            topBar = {
                ToolBarView(title)
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier.Companion
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.Companion.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Column(
                    modifier = Modifier.Companion.fillMaxSize(),
                    horizontalAlignment = Alignment.Companion.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    number = number?.plus(1)
                    Text(
                        modifier = Modifier.Companion.padding(bottom = 10.dp),
                        fontSize = 18.sp,
                        text = text.value
                    )

                    Button(onClick = {
                        NavigationUtils.popBackMain()
                    }) {
                        Text("Present", color = Color.Companion.White)
                    }
                    Button(onClick = {
                        NavigationUtils.savedStateHandle("number", number)
                        NavigationUtils.navigate(ScreenTwo.ROUTE)
                    }) {
                        Text("Push", color = Color.Companion.White)
                    }
                    Button(onClick = {
                        NavigationUtils.popBackStack()
                    }) {
                        Text("Back", color = Color.Companion.White)
                    }
                    Button(onClick = {

                    }) {
                        Text("Clear Stack", color = Color.Companion.White)
                    }
                }
            }
        }
    }
}