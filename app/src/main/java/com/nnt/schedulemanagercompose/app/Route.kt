package com.nnt.schedulemanagercompose.app

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.nnt.schedulemanagercompose.provider.JsonProvider
import com.nnt.schedulemanagercompose.ui.screen.create.NewTaskScreenData
import kotlinx.serialization.encodeToString

/**
 * Created by ThangNNT on 02/12/2023.
 */
sealed class Route(val route: String) {
    object Home: Route(route = "home")
    object Calendar: Route(route = "calendar")
    object Setting: Route(route = "setting")

    object NewTask: Route("new-task?data={data}"){
        private const val dataArg = "data"
        fun navigate(navController: NavController, data: NewTaskScreenData) {
            val dataString = JsonProvider.json.encodeToString(data).replace(" ", "").replace(
                "\n",
                ""
            )
            val route = route.replace("{data}", dataString, ignoreCase = true)
            navController.navigate(route)
        }

        fun getData(
            navBackStackEntry: NavBackStackEntry,
        ): NewTaskScreenData? {
            val dataString = navBackStackEntry.arguments?.getString(dataArg)
            if (dataString.isNullOrBlank()) return null
            return JsonProvider.json.decodeFromString(dataString)
        }
    }
}