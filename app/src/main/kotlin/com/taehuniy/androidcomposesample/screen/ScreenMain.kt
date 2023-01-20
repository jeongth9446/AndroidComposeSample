package com.taehuniy.androidcomposesample.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ScreenMain(
    onNavigateToScreenVideo: () -> Unit
) {
    Column {
        Text(text = "Android Compose Sample")

        // ScreenVideo
        Button(onClick = onNavigateToScreenVideo) {
            Text("Go To Video")
        }
    }
}