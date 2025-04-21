package com.example.navigationnative.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigationnative.presentation.di.NavigationStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationStore: NavigationStore
) : ViewModel() {
    fun showSimpleNotification(context: Context){
        navigationStore.getNavigationUseCase().showSimpleNotification(context)
    }
}