package com.nnt.schedulemanagercompose.app.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nnt.schedulemanagercompose.extension.popUpToAndClearTop

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Composable
fun BottomBar(
    navController: NavController
) {
    val items = remember {
        listOf(BottomBarItem.Home, BottomBarItem.Calendar, BottomBarItem.Setting)
    }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route?:""
    if (currentRoute in items.map { it.route }){
        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEach { item ->
                ItemView(item = item, currentRoute = currentRoute, onSelected = {
                    if (item == BottomBarItem.Home) {
                        navController.popUpToAndClearTop(item.route)
                    } else {
                        navController.navigate(item.route)
                    }
                })
            }
        }
    }
}

@Composable
private fun RowScope.ItemView(item: BottomBarItem, currentRoute: String, onSelected: (BottomBarItem) -> Unit){
    Column(modifier = Modifier
        .weight(1f, true)
        .clickable {
            if (item.route == currentRoute) return@clickable
            onSelected.invoke(item)
        }
        .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val icon =
            if (currentRoute == item.route) item.activeIcon else item.inActiveIcon
        Image(
            painter = painterResource(id = icon),
            contentDescription = "navigation icon"
        )
        Text(text = item.label)
    }
}