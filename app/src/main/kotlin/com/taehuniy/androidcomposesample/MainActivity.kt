package com.taehuniy.androidcomposesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.taehuniy.androidcomposesample.navhost.AndroidComposeSampleNavHost
import com.taehuniy.androidcomposesample.ui.theme.AndroidComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                AndroidComposeSampleNavHost()
            }
        }
    }
}