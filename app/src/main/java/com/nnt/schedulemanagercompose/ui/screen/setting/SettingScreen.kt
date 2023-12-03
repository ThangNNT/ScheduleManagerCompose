package com.nnt.schedulemanagercompose.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.extension.statusBarHeight
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.VerticalSpacer
import com.nnt.schedulemanagercompose.ui.screen.setting.view.SettingButton
import com.nnt.schedulemanagercompose.ui.screen.setting.view.SettingHeader

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Composable
fun SettingScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = ColorProvider.appColors.windowBackground)
        .padding(top = statusBarHeight() + 16.dp)
        .padding(horizontal = 16.dp)) {
        SettingHeader(text = "Hello")
        VerticalSpacer(height = 8.dp)
        SettingButton(text = "setting1", icon = R.drawable.ic_arrow_down) {

        }
        VerticalSpacer(height = 8.dp)
        SettingButton(text = "setting1", icon = R.drawable.ic_arrow_down) {

        }
        VerticalSpacer(height = 8.dp)
        SettingButton(text = "setting1", icon = R.drawable.ic_arrow_down) {

        }
        VerticalSpacer(height = 8.dp)
        SettingButton(text = "setting1", icon = R.drawable.ic_arrow_down) {

        }
        VerticalSpacer(height = 8.dp)
        SettingButton(text = "setting1", icon = R.drawable.ic_arrow_down) {

        }
    }
}