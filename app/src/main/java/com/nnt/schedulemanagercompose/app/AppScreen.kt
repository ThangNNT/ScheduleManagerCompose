package com.nnt.schedulemanagercompose.app

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nnt.schedulemanagercompose.AppState
import com.nnt.schedulemanagercompose.app.bottombar.BottomBar
import com.nnt.schedulemanagercompose.ui.component.NewEventButtonSheet
import kotlinx.coroutines.launch

/**
 * Created by ThangNNT on 02/12/2023.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AppScreen(appViewModel: AppViewModel){
    val navController = rememberAnimatedNavController()
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()
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
                viewModel = appViewModel
            )
        }
    }
    NewEventButtonSheet(state = AppState.newEventSheetState, onSelectNewTask = {

    }, onSelectAnniversary = {

    }, {

    })
    BackHandler(enabled = AppState.newEventSheetState.isVisible) {
        coroutineScope.launch {
            AppState.newEventSheetState.hide()
        }
    }
}