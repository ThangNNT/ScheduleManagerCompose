package com.nnt.schedulemanagercompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.extension.statusBarHeight
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.VerticalSpacer
import com.nnt.schedulemanagercompose.ui.component.HeaderView
import com.nnt.schedulemanagercompose.ui.component.TaskData
import com.nnt.schedulemanagercompose.ui.component.TaskItemView

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Composable
fun HomeScreen(){
    var isHeaderExpand by remember {
        mutableStateOf(true)
    }
    val tasks = remember {
        mutableStateListOf<TaskData>()
    }
    LaunchedEffect(key1 = true, block = {
        for (i in 0..10) {
            tasks.add(
                TaskData(
                    id = i,
                    "Task ${i + 1}",
                    isComplete = false,
                    time = System.currentTimeMillis(),
                    enableNotification = i % 2 == 0
                )
            )
        }
    })
    LazyColumn(modifier = Modifier
        .background(color = ColorProvider.appColors.windowBackground)
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        item {
            VerticalSpacer(height = statusBarHeight() + 16.dp)
        }
        item {
            HeaderView(header = "header1", isExpand = isHeaderExpand, onExpandChanged = {
                isHeaderExpand = it
            })
        }
        if (isHeaderExpand){
            items(count = tasks.size){
                Column {
                    VerticalSpacer(height = 8.dp)
                    TaskItemView(
                        data = tasks[it],
                        onCheckedChange = { task, isComplete ->
                            tasks[it] = task.copy(isComplete = isComplete)
                        },
                        onEdit = {

                        },
                        onDelete = {

                        })
                }
            }
        }
        item { 
            VerticalSpacer(height = 120.dp)
        }
    }
}