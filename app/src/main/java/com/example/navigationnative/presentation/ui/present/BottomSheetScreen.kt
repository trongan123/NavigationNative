package com.example.navigationnative.presentation.ui.present

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationnative.utils.NavigationUtils


object BottomSheetScreen {

    const val ROUTE = "BottomSheetScreen"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen() {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { NavigationUtils.popBackStack() },
            sheetState = sheetState
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Bottom Sheet")
                Spacer(Modifier.height(16.dp))
                Button(onClick = { NavigationUtils.popBackStack() }) {
                    Text("Đóng")
                }
            }
        }
    }
}