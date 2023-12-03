package com.nnt.schedulemanagercompose.app

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nnt.schedulemanagercompose.app.bottombar.BottomBar

/**
 * Created by ThangNNT on 02/12/2023.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen(appViewModel: AppViewModel){
    val navController = rememberAnimatedNavController()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Transparent, true)
    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)){
        Scaffold(
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) {
            Graph(
                startDestination = Route.Home.route,
                navController = navController,
                systemUiController = systemUiController,
                viewModel = appViewModel
            )
        }
    }
}