package com.nnt.schedulemanagercompose.app.bottombar

import androidx.annotation.DrawableRes
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.app.Route

/**
 * Created by ThangNNT on 02/12/2023.
 */
sealed class BottomBarItem(
    val route: String,
    val label: String,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inActiveIcon: Int
){
    object Home: BottomBarItem(
        route = Route.Home.route,
        label = "Home",
        activeIcon = R.drawable.ic_home_active,
        inActiveIcon = R.drawable.ic_home_inactive)

    object Calendar: BottomBarItem(
        route = Route.Calendar.route,
        label = "Calendar",
        activeIcon = R.drawable.ic_home_active,
        inActiveIcon = R.drawable.ic_home_inactive)

    object Setting: BottomBarItem(
        route = Route.Setting.route,
        label = "Setting",
        activeIcon = R.drawable.ic_home_active,
        inActiveIcon = R.drawable.ic_home_inactive)
}
