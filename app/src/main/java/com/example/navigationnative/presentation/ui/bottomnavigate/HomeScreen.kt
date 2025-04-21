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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.navigationnative.presentation.ui.navigation.ScreenOne
import com.example.navigationnative.presentation.ui.present.BottomSheetScreen
import com.example.navigationnative.presentation.ui.present.PresentOne
import com.example.navigationnative.presentation.ui.view.BackExit
import com.example.navigationnative.presentation.viewmodel.NavigationViewModel
import com.example.navigationnative.utils.NavigationUtils

object HomeScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        title: String,
        viewModel: NavigationViewModel = hiltViewModel(),
        context: Context = LocalView.current.context
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
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        fontSize = 18.sp,
                        text = "Jetpack Compose Navigation"
                    )
                    Button(onClick = {
                        NavigationUtils.navigate(PresentOne.ROUTE)
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
                        NavigationUtils.navigate(BottomSheetScreen.ROUTE)
                    }) {
                        Text("Bottom Sheet", color = Color.White)
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