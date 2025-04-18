package com.example.navigationnative.presentation.ui.present

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationnative.utils.NavigationUtils

object PresentOne {
    const val ROUTE = "PresentOne"

    @Composable
    fun Show() {
        FullScreenDialog(
            onDismissRequest = {
                NavigationUtils.popBackStack()
            },
        ) {
            Screen("Present One", onBackPressed = { NavigationUtils.popBackStack() })
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(title: String, onBackPressed: (() -> Unit)? = null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(title) }
                )
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

                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        fontSize = 18.sp,
                        text = "Present One"
                    )

                    Button(onClick = {
                      NavigationUtils.navigate(PresentTwo.ROUTE)
                    }) {
                        Text("Present", color = Color.White)
                    }
                    Button(onClick = {
                        onBackPressed?.invoke()
                    }) {
                        Text("Back", color = Color.White)
                    }

                }
            }
        }
    }
}