package com.nnt.schedulemanagercompose.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.nnt.schedulemanagercompose.extension.pxToDp

/**
 * Created by ThangNNT on 02/12/2023.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Graph(
    startDestination: String,
    navController: NavHostController,
    systemUiController: SystemUiController,
    viewModel: AppViewModel
){
    val statusBarHeight = WindowInsets.statusBars.getTop(LocalDensity.current).pxToDp()
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ){
        composable(Route.Home.route){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Yellow)
                    .padding(top = statusBarHeight)
            ) {
                Text(text = "This is home", modifier = Modifier.align(Alignment.Center))
            }
        }
        composable(Route.Calendar.route){
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "This is calendar", modifier = Modifier.align(Alignment.Center))
            }
        }
        composable(Route.Setting.route){
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "This is setting", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}