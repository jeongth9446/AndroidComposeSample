package com.taehuniy.androidcomposesample.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.taehuniy.common.util.HawkUtil

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

        var text by remember { mutableStateOf("") }
        TextField(value = text, onValueChange = { text = it })

        Button(onClick = { HawkUtil.set("test", text) }) {
            Text("Add")
        }

        Button(onClick = { Toast.makeText(context, HawkUtil.get<String>("test"), Toast.LENGTH_SHORT).show()}) {
            Text("Show")
        }
    }
}