package com.taehuniy.common.compose

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun ExoPlayerCompose(videoUri: String) {
    val context = LocalContext.current

    var exoPlayer by remember {
        mutableStateOf(
            createExoPlayer(context, videoUri)
        )
    }

    val playerView by remember {
        mutableStateOf(StyledPlayerView(context).apply {
            player = exoPlayer
        })
    }

    LaunchedEffect(videoUri) {
        exoPlayer.release()
        exoPlayer = createExoPlayer(context, videoUri)
        playerView.player = exoPlayer
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    AndroidView(factory = {
        playerView
    })

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { owner, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }
                else -> { /* No Op */ }
            }
        }
        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycle.removeObserver(observer)
        }
    }
}

private fun createExoPlayer(context: Context, videoUri: String) =
    ExoPlayer.Builder(context)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri)
                .build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()
        }