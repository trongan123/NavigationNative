package com.example.navigationnative.presentation.ui.navigation.screenone

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigationnative.presentation.di.NavigationStore
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ScreenOneViewModel @Inject constructor(
    private val navigationStore: NavigationStore
) : ViewModel() {

    init {
        Log.e("TAG", "NavigationViewModel init")
    }

    fun getText(): StateFlow<String> = navigationStore.text

    fun setText() {
        navigationStore.setText("Zxczxcxzcxzczxczxc")
    }

}