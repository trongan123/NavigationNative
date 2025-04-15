package com.example.navigationnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationnative.presentation.navigation.AppNavigation
import com.example.navigationnative.presentation.theme.NavigationNativeTheme
import com.example.navigationnative.utils.NavigationUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationNativeTheme {
                AppNavigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NavigationUtils.clearNavController()
    }
}