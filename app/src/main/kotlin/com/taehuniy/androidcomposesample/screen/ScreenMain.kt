package com.taehuniy.androidcomposesample.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun ScreenMain(
    onNavigateToScreenVideo: () -> Unit
) {
    val context = LocalContext.current

    Column {
        Text(text = "Android Compose Sample")

        // ScreenVideo
        Button(onClick = onNavigateToScreenVideo) {
            Text("Go To Video")
        }
        
        // OssLicensesMenuActivity
        // TODO : Move to Setting Menu
        Button(onClick = { context.startActivity(Intent(context, OssLicensesMenuActivity::class.java)) }) {
            Text("Go To Licenses")
        }
    }
}