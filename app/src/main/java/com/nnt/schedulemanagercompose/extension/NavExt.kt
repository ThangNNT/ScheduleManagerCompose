package com.nnt.schedulemanagercompose.extension

import androidx.navigation.NavController

/**
 * Created by ThangNNT on 02/12/2023.
 */
fun NavController.popUpToAndClearTop(route: String) {
    this.navigate(route) {
        popUpTo(route) {
            saveState
        }
        launchSingleTop = true
        restoreState = true
    }
}