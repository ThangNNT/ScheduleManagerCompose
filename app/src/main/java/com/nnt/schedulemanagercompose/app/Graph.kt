package com.nnt.schedulemanagercompose.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.nnt.schedulemanagercompose.extension.pxToDp
import com.nnt.schedulemanagercompose.ui.screen.CalendarScreen
import com.nnt.schedulemanagercompose.ui.screen.home.HomeScreen
import com.nnt.schedulemanagercompose.ui.screen.SettingScreen

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
        composable(Route.Home.route) {
            HomeScreen()
        }
        composable(Route.Calendar.route) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = statusBarHeight)) {
                CalendarScreen()
            }
        }
        composable(Route.Setting.route) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = statusBarHeight)) {
                SettingScreen()
            }
        }
    }
}