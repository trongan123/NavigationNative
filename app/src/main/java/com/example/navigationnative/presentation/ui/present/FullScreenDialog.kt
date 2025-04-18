package com.example.navigationnative.presentation.ui.present

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class FullScreen() {

    private val _isOpen = MutableStateFlow(false)
    val isOpen: StateFlow<Boolean> = _isOpen

    fun onDismiss() {
        _isOpen.value = false
    }

    fun onOpen() {
        _isOpen.value = true
    }

    @Composable
    fun FullScreenDialog(
        onDismissRequest: () -> Unit,
        onBackPressed: (() -> Unit)? = null,
        content: @Composable () -> Unit
    ) {
        val isOpen = isOpen.collectAsState()

        if (isOpen.value) {
            Dialog(
                onDismissRequest = {
                    _isOpen.value = false
                },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    BackHandler {
                        onBackPressed?.invoke() ?: onDismissRequest()
                    }

                    content()
                }
            }
        }
    }
}