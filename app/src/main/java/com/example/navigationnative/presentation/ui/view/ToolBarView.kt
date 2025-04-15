package com.example.navigationnative.presentation.ui.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.navigationnative.R
import com.example.navigationnative.utils.NavigationUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarView(title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {NavigationUtils.popBackStack()}) {
                IconView(
                    R.drawable.ic_back_previous,
                    size = 24
                )
            }
        }
    )
}