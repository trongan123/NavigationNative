package com.example.navigationnative.presentation.di

import android.util.Log
import com.example.navigationnative.domain.usecase.NotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationStore @Inject constructor(
    private val navigationUseCase: NotificationUseCase
) {

    fun getNavigationUseCase() : NotificationUseCase = navigationUseCase

    private var _text = MutableStateFlow<String>("")
    var text: StateFlow<String> = _text

    init {
        Log.e("TAG", "NavigationStore init")
    }

    fun setText(text: String) {
        _text.value = text
    }

}