package com.taehuniy.androidcomposesample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.taehuniy.androidcomposesample.ui.theme.AndroidComposeSampleTheme
import com.taehuniy.common.compose.ExoPlayerCompose
import com.taehuniy.common.util.GsonUtil.toDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.lang.Integer.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var videoResource by remember { mutableStateOf(SampleVideoListModel()) }
                    var loadingCompleteFlag by remember { mutableStateOf(false) }
                    var maxVideoIndex by remember { mutableStateOf(0) }
                    var minVideoIndex by remember { mutableStateOf(0) }
                    var currentVideoIndex by remember { mutableStateOf(0) }
                    LaunchedEffect(Unit) {
                        withContext(Dispatchers.IO) {
                            val bufferReader =
                                BufferedReader(InputStreamReader(assets.open("jsons/video.json")))
                            val buffer = StringBuffer()
                            var line = bufferReader.readLine()

                            while (line != null) {
                                buffer.append(line + "\n")
                                line = bufferReader.readLine()
                            }
                            videoResource = buffer.toString().toDataClass()
                            loadingCompleteFlag= true
                            maxVideoIndex = (videoResource.categories?.get(0)?.videos?.size ?: 1) - 1
                        }
                    }

                    Box(modifier = Modifier.fillMaxSize()) {

                        Column() {
                            // Video Player
                            if (loadingCompleteFlag) {
                                val videoResourceUri = remember(currentVideoIndex) {
                                    videoResource.categories?.get(0)?.videos?.get(currentVideoIndex)?.sources?.get(
                                        0
                                    ).toString()
                                }
                                ExoPlayerCompose(videoResourceUri)
                            }


                            Text("${currentVideoIndex + 1} / ${maxVideoIndex}")
                        }
                        Button(
                            enabled = currentVideoIndex != minVideoIndex,
                            modifier = Modifier.align(
                                Alignment.BottomStart
                            ),
                            onClick = {
                                currentVideoIndex = max(currentVideoIndex - 1, minVideoIndex)
                            }
                        ) {
                            Text("Prev")
                        }

                        Button(
                            enabled = currentVideoIndex != maxVideoIndex-1,
                            modifier = Modifier.align(
                                Alignment.BottomEnd
                            ),
                            onClick = {
                                currentVideoIndex = min(currentVideoIndex + 1, maxVideoIndex-1)
                            }
                        ) {
                            Text("Next")
                        }
                    }
                }
            }
        }
    }
}


data class SampleVideoListModel(
    val categories: ArrayList<SampleVideoCategoryModel>? = null,
)

data class SampleVideoCategoryModel(
    val name: String? = null,
    val videos: ArrayList<SampleVideoModel>? = null
)
data class SampleVideoModel(
    val description: String? = null,
    val sources: List<String>? = null,
    val subtitle: String? = null,
    val thumb: String? = null,
    val title: String? = null,
)