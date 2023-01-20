package com.taehuniy.androidcomposesample.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taehuniy.androidcomposesample.screen.ScreenMain
import com.taehuniy.androidcomposesample.screen.ScreenVideo

@Composable
fun AndroidComposeSampleNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteScreenMain
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RouteScreenMain) {
            ScreenMain(onNavigateToScreenVideo = { navController.navigate(RouteScreenVideo)})
        }
        composable(RouteScreenVideo) { ScreenVideo() }
    }
}