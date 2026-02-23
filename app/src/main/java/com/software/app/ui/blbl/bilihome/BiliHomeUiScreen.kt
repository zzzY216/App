package com.software.app.ui.blbl.bilihome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BiliHomeUiScreen() {
    BiliHomeUiContent()
}

@Composable
fun BiliHomeUiContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "BiliHomeUiScreen")
    }
}