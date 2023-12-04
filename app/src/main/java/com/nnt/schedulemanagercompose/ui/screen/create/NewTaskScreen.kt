package com.nnt.schedulemanagercompose.ui.screen.create

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nnt.schedulemanagercompose.extension.fromStandardToLocalDate

/**
 * Created by ThangNNT on 04/12/2023.
 */
@kotlinx.serialization.Serializable
data class NewTaskScreenData(val localDateString: String)

@Composable
fun NewTaskScreen(data: NewTaskScreenData) {
    Log.d("AAAAAAAAA"," ${data.localDateString.fromStandardToLocalDate()}")
    Column(modifier = Modifier.fillMaxSize()) {

    }
}