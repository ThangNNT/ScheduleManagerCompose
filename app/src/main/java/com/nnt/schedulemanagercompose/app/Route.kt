package com.nnt.schedulemanagercompose.app

/**
 * Created by ThangNNT on 02/12/2023.
 */
sealed class Route(val route: String) {
    object Home: Route(route = "home")
    object Calendar: Route(route = "calendar")
    object Setting: Route(route = " setting")
}