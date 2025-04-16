package com.example.navigationnative.presentation.ui.bottomnavigate

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navigationnative.presentation.ui.navigation.screenone.ScreenOne
import com.example.navigationnative.presentation.ui.view.BackExit
import com.example.navigationnative.presentation.viewmodel.NavigationViewModel
import com.example.navigationnative.utils.NavigationUtils

object HomeScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        title: String,
        viewModel: NavigationViewModel = hiltViewModel(),
        context: Context = LocalContext.current
    ) {
        BackExit()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = { Text(title) }
                )
            }
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
                        NavigationUtils.savedStateHandle("number", 0)
                        NavigationUtils.navigate(ScreenOne.ROUTE)
                    }) {
                        Text("Present", color = Color.White)
                    }
                    Button(onClick = {
                        NavigationUtils.savedStateHandle("number", 0)
                        NavigationUtils.navigate(ScreenOne.ROUTE)
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
                    Button(onClick = {
                        viewModel.showSimpleNotification(context)
                    }) {
                        Text("Show Notification", color = Color.White)
                    }
                }
            }
        }
    }
}