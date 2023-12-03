package com.nnt.schedulemanagercompose.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.nnt.schedulemanagercompose.ui.screen.calendar.CalendarScreen
import com.nnt.schedulemanagercompose.ui.screen.home.HomeScreen
import com.nnt.schedulemanagercompose.ui.screen.setting.SettingScreen

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
            CalendarScreen()
        }
        composable(Route.Setting.route) {
            SettingScreen()
        }
    }
}